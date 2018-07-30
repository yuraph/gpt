package com.gpengtao.aop;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author pengtao.geng on 2018/7/26 下午10:11.
 */
@Aspect
@Component
@Slf4j
public class AnnotatedParamCheckAspect {

	private static final String GPT_CLASS = "com.gpengtao";

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	private void getMappingPoint() {
	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	private void postMappingPoint() {
	}

	@Before("getMappingPoint()")
	public void checkGetMethod(JoinPoint joinPoint) {
		checkJoinPoint(joinPoint);
	}

	@Before("postMappingPoint()")
	public void checkPostMethod(JoinPoint joinPoint) {
		checkJoinPoint(joinPoint);
	}

	private void checkJoinPoint(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		try {
			for (Object arg : args) {
				checkArg(arg);
			}
		} catch (IllegalAccessException e) {
			log.error("aop异常", e);
		}
	}

	private void checkArg(Object arg) throws IllegalAccessException {
		if (arg == null) {
			return;
		}

		// 非idss类不处理
		if (!arg.getClass().getName().contains(GPT_CLASS)) {
			return;
		}

		// 获取所有的field，包括父类的
		List<Field> fields = getAllFields(arg);

		// 遍历check field
		for (Field field : fields) {
			checkEachField(arg, field);
		}
	}

	private List<Field> getAllFields(Object arg) {
		List<Field> list = Lists.newArrayList();

		Field[] fields = arg.getClass().getDeclaredFields();
		list.addAll(Arrays.asList(fields));

		Class<?> superclass = arg.getClass().getSuperclass();
		while (superclass != null) {
			Field[] superDeclaredFields = superclass.getDeclaredFields();

			list.addAll(Arrays.asList(superDeclaredFields));

			superclass = superclass.getSuperclass();
		}

		return list;
	}

	private void checkEachField(Object arg, Field field) throws IllegalAccessException {
		field.setAccessible(true);

		// filed自己标记的注解
		Annotation[] annotations = field.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(NotEmpty.class)) {
				checkNotEmptyAnnotation(arg, field, annotation);
			} else if (annotation.annotationType().equals(NotNull.class)) {
				checkNotNullAnnotation(arg, field, annotation);
			} else if (annotation.annotationType().equals(Range.class)) {
				checkRangeAnnotation(arg, field, annotation);
			}
		}

		// filed字段类型是idss的类，check这个类
		if (field.getType().getName().contains(GPT_CLASS)) {
			Object filedValue = field.get(arg);
			checkArg(filedValue);
		}

		// filed是collection，collection的元素是idss的实例
		if (field.getType().isAssignableFrom(ArrayList.class)) {
			if (field.getGenericType().getTypeName().contains(GPT_CLASS)) {
				ArrayList collection = (ArrayList) field.get(arg);
				for (Object o : collection) {
					checkArg(o);
				}
			}
		}
	}

	private void checkNotEmptyAnnotation(Object arg, Field field, Annotation annotation) throws IllegalAccessException {
		String message = ((NotEmpty) annotation).message();

		Object value = field.get(arg);
		if (value == null) {
			throw new IllegalArgumentException(message);
		}

		if (value.getClass().equals(String.class)) {
			String string = (String) value;
			if (StringUtils.isEmpty(string)) {
				throw new IllegalArgumentException(message);
			}
		} else if (value.getClass().isAssignableFrom(ArrayList.class)) {
			Collection collection = (Collection) value;
			if (CollectionUtils.isEmpty(collection)) {
				throw new IllegalArgumentException(message);
			}
		}
	}

	private void checkNotNullAnnotation(Object arg, Field field, Annotation annotation) throws IllegalAccessException {
		Object value = field.get(arg);
		if (value == null) {
			String message = ((NotNull) annotation).message();
			throw new IllegalArgumentException(message);
		}
	}

	private void checkRangeAnnotation(Object arg, Field field, Annotation annotation) throws IllegalAccessException {
		if (field.getType().equals(int.class)) {
			int value = (int) field.get(arg);
			checkIntegerByRange(value, (Range) annotation);
		} else if (field.getType().equals(Integer.class)) {
			Integer value = (Integer) field.get(arg);
			checkIntegerByRange(value, (Range) annotation);
		}
	}

	private void checkIntegerByRange(Integer value, Range range) {
		String message = range.message();
		long min = range.min();
		long max = range.max();

		if (value == null) {
			throw new IllegalArgumentException(message);
		}

		if (value < min || value > max) {
			throw new IllegalArgumentException(message);
		}
	}
}

package com.gpengtao.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pengtao.geng on 2015/10/27.
 */
public class ModelGenerateUtil {

    public static <T> T generateModel(Class<T> clazz) throws Exception {
        T model = clazz.newInstance();

        Field[] declaredFields = getInheritedFields(clazz).toArray(new Field[]{});
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            Class<?> type = field.getType();

            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }

            String setter = calcSetMethodName(fieldName);

            if (type.isAssignableFrom(String.class)) {
                // String
                Method setterMethod = clazz.getMethod(setter, String.class);

                if (isSetStartEndDate(setter)) {
                    setterMethod.invoke(model, getDateString());
                } else {
                    setterMethod.invoke(model, fieldName + randomInt());
                }

            } else if (type.isAssignableFrom(int.class)) {
                // int
                Method setterMethod = clazz.getMethod(setter, int.class);
                setterMethod.invoke(model, randomInt());
            } else if (type.isAssignableFrom(Integer.class)) {
                // Integer
                Method setterMethod = clazz.getMethod(setter, Integer.class);
                setterMethod.invoke(model, randomInt());
            } else if (type.isAssignableFrom(Long.class)) {
                // long
                Method setterMethod = clazz.getMethod(setter, long.class);
                setterMethod.invoke(model, randomInt());
            } else if (type.isAssignableFrom(double.class)) {
                // double
                Method setterMethod = clazz.getMethod(setter, double.class);
                setterMethod.invoke(model, new Random().nextDouble());
            } else if (type.isAssignableFrom(boolean.class)) {
                // boolean
                Method setterMethod = clazz.getMethod(setter, boolean.class);
                setterMethod.invoke(model, randomBoolean());
            } else if (type.isAssignableFrom(BigDecimal.class)) {
                // BigDecimal
                Method setterMethod = clazz.getMethod(setter, BigDecimal.class);
                setterMethod.invoke(model, randomBigDecimal());
            } else if (type.isAssignableFrom(Date.class)) {
                // Date
                Method setterMethod = clazz.getMethod(setter, Date.class);
                setterMethod.invoke(model, new Date());
            } else if (field.getType().isAssignableFrom(List.class)) {
                // List<>
                String fullTypeName = field.getGenericType().getTypeName();
                String listType = fullTypeName.substring(fullTypeName.indexOf("<") + 1, fullTypeName.lastIndexOf(">"));
                Class<?> listClass = Class.forName(listType);
                Object o1 = generateModel(listClass);
                Object o2 = generateModel(listClass);
                Method setterMethod = clazz.getMethod(setter, List.class);
                setterMethod.invoke(model, Lists.newArrayList(o1, o2));
            } else {
                System.out.println("没有找到对应的类型：" + fieldName);

                Object o = generateModel(type);
                Method setterMethod = clazz.getMethod(setter, type);
                setterMethod.invoke(model, o);
            }
        }

        return model;
    }

    private static String calcSetMethodName(String fieldName) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        return "set" + firstLetter + fieldName.substring(1);
    }

    private static String getDateString() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    private static boolean isSetStartEndDate(String setter) {
        return StringUtils.containsIgnoreCase(setter, "StartDate") || StringUtils.containsIgnoreCase(setter, "EndDate");
    }

    private static List<Field> getInheritedFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    private static BigDecimal randomBigDecimal() {
        return new BigDecimal(randomInt());
    }

    private static int randomInt() {
        int i = new Random().nextInt(10);
        return i < 0 ? -i : i;
    }

    private static boolean randomBoolean() {
        return new Random().nextBoolean();
    }
}

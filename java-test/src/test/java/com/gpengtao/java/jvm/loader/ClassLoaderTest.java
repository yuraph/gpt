package com.gpengtao.java.jvm.loader;

import org.junit.Test;
import sun.management.VMManagement;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;

/**
 * Created by gpengtao on 16/7/21.
 */
public class ClassLoaderTest {

	@Test
	public void test_my_impl_class_loader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		ClassLoader myClassLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String className) throws ClassNotFoundException {
				System.out.println("myClassLoader load class : " + className);

				// java的类不能又自定义的来加载器加载
				if (className.startsWith("java.")) {
					return super.loadClass(className);
				}

				// 读取class文件
				String fileName = "/" + className.replace(".", "/") + ".class";
				InputStream inputStream = getClass().getResourceAsStream(fileName);
				try {
					byte[] bytes = new byte[inputStream.available()];

					inputStream.read(bytes);

					// 转换成class
					return defineClass(className, bytes, 0, bytes.length);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ClassNotFoundException();
				}
			}
		};

		// MyClass会被默认的类加载器加载
		String className = MyClass.class.getName();

		// 用自己写的类加载加载,并new一个对象
		Class<?> myClass = myClassLoader.loadClass(className);
		Object myClassInstance = myClass.newInstance();

		// 不是同一个类型
		System.out.println("instanceof: " + (myClassInstance instanceof MyClass));
		System.out.println("class name: " + myClassInstance.getClass());
		System.out.println("class loader: " + myClassInstance.getClass().getClassLoader());

		System.out.println("class loader: " + MyClass.class.getClassLoader());
	}

	@Test
	public void test_no_found_class() throws ClassNotFoundException {
		this.getClass().getClassLoader().loadClass("xxx");
	}

	@Test
	public void print_class_loader() {
		// 这并不表示Integer这个类没有类加载器，而是它的加载器比较特殊，是BootstrapClassLoader，由于它不是Java类，因此获得它的引用肯定返回null。
		System.out.println(Integer.class.getClassLoader());

		// sun.misc.Launcher$AppClassLoader
		System.out.println(MyClass.class.getClassLoader());
	}

	@Test
	public void class_load_mbean() {
		ClassLoadingMXBean mxBean = ManagementFactory.getClassLoadingMXBean();

		System.out.println(mxBean.getLoadedClassCount());
	}

	/**
	 * "jvm中load的class数量"、"jvm运行中JIT花费的时间"，这两个值我们能通过MXBean得到。但是现在我们得不到"jvm中load的class花费的时间"。
	 * <p>
	 * 这个测试方法为了看看load class花费多少时间，我们是从MXBean的实现类里面先拿到VMManagement，然后从它的接口获得jvm中load的class花费的时间。
	 * <p>
	 * 明明这个时间已经有了，现在不明白为什么java没有把这个时间直接在MXBean里面暴露出来，而是只暴露了load的class数量。
	 *
	 * @throws ClassNotFoundException e
	 * @throws IllegalAccessException e
	 */
	@Test
	public void test_load_class_time() throws ClassNotFoundException, IllegalAccessException {
		// mxBean，记录了class加载的数量
		ClassLoadingMXBean mxBean = ManagementFactory.getClassLoadingMXBean();

		// ClassLoadingImpl是mxBean的真正实现类
		Class<?> clazz = Class.forName("sun.management.ClassLoadingImpl");
		Field[] fields = clazz.getDeclaredFields();
		Field vmManagementField = fields[0];
		vmManagementField.setAccessible(true);

		// 得到了ClassLoadingImpl里面的字段"jvm"，有了它我们可以得到class load花费的时间
		VMManagement vm = (VMManagement) fields[0].get(mxBean);

		System.out.println("loaded class 数量:" + mxBean.getLoadedClassCount() + " time: " + vm.getClassLoadingTime());

		TestLoadClass test = new TestLoadClass();

		System.out.println("loaded class 数量:" + mxBean.getLoadedClassCount() + " time: " + vm.getClassLoadingTime());

		test.loadSomeClass();    // 第一次load耗时会长

		System.out.println("loaded class 数量:" + mxBean.getLoadedClassCount() + " time: " + vm.getClassLoadingTime());

		test.loadSomeClass();    // class已经load过，执行很快

		System.out.println("loaded class 数量:" + mxBean.getLoadedClassCount() + " time: " + vm.getClassLoadingTime());

		test.loadSomeClass();    // class已经load过，执行很快

		System.out.println("loaded class 数量:" + mxBean.getLoadedClassCount() + " time: " + vm.getClassLoadingTime());
	}
}

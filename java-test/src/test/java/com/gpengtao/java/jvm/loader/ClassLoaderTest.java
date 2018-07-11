package com.gpengtao.java.jvm.loader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

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

		mxBean.getLoadedClassCount();
	}

	@Test
	public void test_load_class_time() {
		TestLoadClass test = new TestLoadClass();
		test.load();    // 第一次load耗时会长
		test.load();
		test.load();
	}
}

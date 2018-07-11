package com.gpengtao.java.jvm.loader;

import com.google.common.collect.Lists;
import com.gpengtao.java.jmx.JmxTest;
import com.gpengtao.java.model.Person;

/**
 * @author pengtao.geng on 2018/7/11 下午2:53.
 */
public class TestLoadClass {

	public void loadSomeClass() {
		long start = System.currentTimeMillis();

		Class[] c1 = {
				String.class,   // java lang的
				Integer.class,  // java lang的
				Lists.class,
				Person.class,
				JmxTest.class,
				MyClass.class
		};

		System.out.println(System.currentTimeMillis() - start);
	}
}

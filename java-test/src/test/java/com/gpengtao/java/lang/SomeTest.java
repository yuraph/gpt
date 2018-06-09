package com.gpengtao.java.lang;

import org.junit.Test;

/**
 * @author pengtao.geng on 2018/6/7 上午10:48.
 */
public class SomeTest {

	@Test
	public void test_npe() {
		Integer num = null;

		if (num == 1) {
			System.out.println("1111111");
		} else {
			System.out.println("222222");
		}
	}

	@Test
	public void test_integer() {
		Integer num1 = new Integer("15");
		Integer num2 = new Integer("15");

		System.out.println(num1 == num2);
	}
}

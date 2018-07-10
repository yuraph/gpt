package com.gpengtao.java.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengtao.geng on 2018/7/10 上午11:06.
 */
public class StringTest {

	private static final int TIMES = 10000;

	@Test
	public void test_time() {
		for (int i = 0; i < 10; i++) {
			doTest();
			System.out.println("===================");
		}
	}

	private void doTest() {
		testPlus();
		testConcat();
		testJoin();
		testStringBuffer();
		testStringBuilder();
		testStringFormat();
	}

	private void testPlus() {
		long ts = System.currentTimeMillis();

		String s = "";
		for (int i = 0; i < TIMES; i++) {
			s = s + String.valueOf(i);
		}

		long te = System.currentTimeMillis();
		System.out.println("plus+ cost\t\t\t\t\t" + (te - ts));
	}

	private void testConcat() {
		long ts = System.currentTimeMillis();

		String s = "";
		for (int i = 0; i < TIMES; i++) {
			s = s.concat(String.valueOf(i));
		}

		long te = System.currentTimeMillis();
		System.out.println("concat cost\t\t\t\t\t" + (te - ts));
	}

	private void testJoin() {
		long ts = System.currentTimeMillis();

		List<String> list = new ArrayList<>();
		for (int i = 0; i < TIMES; i++) {
			list.add(String.valueOf(i));
		}
		String ret = StringUtils.join(list, "");

		long te = System.currentTimeMillis();
		System.out.println("StringUtils.join cost\t\t" + (te - ts));
	}

	private void testStringBuffer() {
		long ts = System.currentTimeMillis();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < TIMES; i++) {
			sb.append(String.valueOf(i));
		}
		String ret = sb.toString();

		long te = System.currentTimeMillis();
		System.out.println("StringBuffer cost\t\t\t" + (te - ts));
	}

	private void testStringBuilder() {
		long ts = System.currentTimeMillis();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < TIMES; i++) {
			sb.append(String.valueOf(i));
		}
		String ret = sb.toString();

		long te = System.currentTimeMillis();
		System.out.println("StringBuilder cost\t\t\t" + (te - ts));
	}

	private void testStringFormat() {
		long ts = System.currentTimeMillis();

		String str = "";
		for (int i = 0; i < TIMES; i++) {
			str = String.format("%s%s", str, String.valueOf(i));
		}

		long te = System.currentTimeMillis();

		System.out.println("StringFormat cost\t\t\t" + (te - ts));
	}
}

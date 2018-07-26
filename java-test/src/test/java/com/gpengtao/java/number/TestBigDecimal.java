package com.gpengtao.java.number;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by pengtao.geng on 2017/3/22.
 */
public class TestBigDecimal {

	@Test
	public void test_equal() {

		System.out.println(Double.toString(1.0));
		System.out.println(Double.toString(1.00));

		// 结果true，equal比较的是数值和精度，这个返回true因为valueOf一个double数值时候都先转换给字符串，都是1.0，精度一样了
		BigDecimal v1 = BigDecimal.valueOf(1.0);
		BigDecimal v2 = BigDecimal.valueOf(1.00);
		System.out.println(v1.equals(v2));

		System.out.println(Double.doubleToLongBits(1.0));
		System.out.println(Double.doubleToLongBits(1.00));

		// 结果true，因为Double.doubleToLongBits是一样的
		BigDecimal v3 = new BigDecimal(1.0);
		BigDecimal v4 = new BigDecimal(1.00);
		System.out.println(v3.equals(v4));

		// 结果false，因为两个精度不一样
		BigDecimal v5 = new BigDecimal("1.0");
		BigDecimal v6 = new BigDecimal("1.00");
		System.out.println(v5.equals(v6));

	}

	@Test
	public void test_big_decimal() {
		// 打印0.1000000000000000055511151231257827021181583404541015625
		BigDecimal d1 = new BigDecimal(0.1);
		System.out.println(d1);

		// 打印0.299999999999999988897769753748434595763683319091796875
		BigDecimal d2 = new BigDecimal(0.3);
		System.out.println(d2);

		Float f = 2.2f;
		double d = (double) f;
		System.out.println(d);
		f = 2.25f;
		d = (double) f;
		System.out.println(d);
	}

	@Test
	public void test1() {
		System.out.println(new BigDecimal(0.3));
		System.out.println(new BigDecimal("0.3"));

		System.out.println(Double.doubleToLongBits(0.3));
	}

	@Test
	public void test() {
		System.out.println(new BigDecimal("11.999").setScale(0, BigDecimal.ROUND_DOWN));

		System.out.println(new BigDecimal("11.111").setScale(0, BigDecimal.ROUND_CEILING));
	}

	@Test
	public void test_int_max_value() {
		long num = Integer.MAX_VALUE;
//		num *= 2;
		System.out.println(num);

		long year = num / (10 * 10000) / 365;

		System.out.println(year);
	}
}

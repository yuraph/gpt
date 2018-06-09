package com.gpengtao.java.joda;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author pengtao.geng on 2018/6/7 上午11:08.
 */
public class JodaTest {

	@Test
	public void test() {
		LocalTime time = LocalTime.of(11, 0);
		System.out.println(time.format(DateTimeFormatter.ISO_TIME));
	}
}

package com.gpengtao.java;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengtao.geng on 2017/10/24.
 */
public class Other {

    @Test
    public void test() {
        System.out.println(10.1 % 3);

        System.out.println(~1);
    }

    @Test
    public void test_list() {
        List<Person> list = new ArrayList<>(3);

        list.add(new Person("1"));
        list.add(new Person("2"));
        list.add(new Person("3"));
        list.add(new Person("4"));
        list.add(new Person("5"));

        for (int i = 0; i < 100; i++) {
            list.add(new Person("" + i));
        }

        Person person = list.get(0);
        list.remove(person);

        System.out.println(list);
    }

    @Test
    public void test_integer() {
        Integer integer1 = new Integer("1234567");
        Integer integer2 = new Integer("1234567");

        System.out.println(integer1 == integer2);
    }

    @Test
    public void test_integer_compare() {
        Integer integer1 = new Integer("1234567");
        int integer2 = 123456;

        System.out.println(integer1 > integer2);

        System.out.println(integer1.compareTo(integer2));

    }

    @Test
    public void test_double() {
        double a = 0.3;
        System.out.println(a);

        BigDecimal b = new BigDecimal(0.6);
        System.out.println(b);
    }

    @Test
    public void test_uniq() {
        List<Integer> list = Lists.newArrayList(1, 2, 1, 2, 3, 4, 1, 2);

        System.out.println(list);
    }

    @Test
    public void test_sort() {
        List<Integer> list = Lists.newArrayList(3, 2, 1, 5, 4);

        list.sort(Integer::compareTo);

        System.out.println(list);
    }

}

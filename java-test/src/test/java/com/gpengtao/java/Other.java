package com.gpengtao.java;

import org.junit.Test;

import java.io.IOException;
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

    }

    public static void main(String[] args) throws IOException {
        System.in.read();
    }
}

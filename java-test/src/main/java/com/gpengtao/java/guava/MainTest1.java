package com.gpengtao.java.guava;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class MainTest1 {

    public static void main(String[] args) {

        List<List<Integer>> list = Lists.newArrayList();
        list.add(Lists.newArrayList());
        System.out.println(list);

        List<List<Integer>> list2 = Collections.singletonList(Lists.newArrayList());
        System.out.println(list2);
    }
}

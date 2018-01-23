package com.gpengtao.java.stream;

import com.google.common.collect.Lists;

import java.util.List;

public class ParallelStreamTest {

    public static void main(String[] args) {

        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(11, 22, 33, 44, 55);

        List<List<Integer>> lists = Lists.newArrayList(list1, list2);

        lists.parallelStream().forEach(one -> one.parallelStream().forEach(System.out::println));
    }
}

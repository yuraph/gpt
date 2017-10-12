package com.gpengtao.java.stream;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2017/10/11.
 */
public class SortTest {

    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        List<Integer> list2 = list.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list2);
    }
}

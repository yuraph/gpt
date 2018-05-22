package com.gpengtao.java.collection;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pengtao.geng on 2017/11/27.
 */
public class Java8Test {

    public static void main(String[] args) {
        List<List<Integer>> list = Lists.newArrayList();

        list.add(Lists.newArrayList(1, 2, 3));
        list.add(Lists.newArrayList(4, 5, 6));
        list.add(Lists.newArrayList(7, 8, 9));

        List<Integer> all = list.stream().flatMap((Function<List<Integer>, Stream<Integer>>) Collection::stream).collect(Collectors.toList());

        System.out.println(all);
    }
}

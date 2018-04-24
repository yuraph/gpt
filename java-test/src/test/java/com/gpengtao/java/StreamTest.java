package com.gpengtao.java;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2017/9/4.
 */
public class StreamTest {
    @Test
    public void test() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);

        List<Integer> list2 = list.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 2;
            }
        }).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void test_reduce() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 1; i <= 1000; i++) {
            list.add(i);
        }

        AtomicInteger mapCount = new AtomicInteger(0);
        AtomicInteger reduceCount = new AtomicInteger(0);

        Optional<Integer> ret = null;
        try {
            ret = list.parallelStream()
                    .map(integer -> {
                        int i = integer * 2;
                        System.out.println("map " + integer + " -> " + i);
                        mapCount.incrementAndGet();
                        return i;
                    })
                    .reduce((s1, s2) -> {
                        int sum = s1 + s2;
                        System.out.println("reduce " + s1 + " " + s2 + " -> " + sum);
                        reduceCount.incrementAndGet();
                        if (sum > 0) {
                            System.out.println("===========================" + Thread.currentThread());
                            throw new RuntimeException("too big");
                        }
                        return sum;
                    });
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("结果" + ret);

        System.out.println("map count " + mapCount);
        System.out.println("reduce count " + reduceCount);
    }

    @Test
    public void test_grouping_min_by() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 1, 1);

        Map<String, Optional<Integer>> groups = list.stream().collect(Collectors.groupingBy(one -> {
            if (one > 3) {
                return "big";
            } else {
                return "small";
            }
        }, Collectors.minBy(Comparator.naturalOrder())));

        System.out.println(groups);
    }

    @Test
    public void test_xing_neng() {
        List<Integer> list = Lists.newArrayList(1, 2);

        int times = 100000000;

        for (int count = 0; count < 100; count++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                list.forEach(x -> {
                    int a = x * x;
                });
            }
            System.out.println(System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                for (Integer x : list) {
                    int a = x * x;
                }
            }
            System.out.println(System.currentTimeMillis() - start);

            System.out.println("-----------------------------");
        }
    }
}

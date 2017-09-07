package com.gpengtao.java;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
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
}

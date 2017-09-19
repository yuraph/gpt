package com.gpengtao.java.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by pengtao.geng on 2017/9/14.
 */
public class MultiMapTest {

    @Test
    public void test() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();

        for (Integer integer : list) {
            multimap.put(integer, integer);
        }

        System.out.println(multimap.size());
        System.out.println(multimap.keys().size());
        System.out.println(multimap.keySet().size());
        System.out.println(multimap.values().size());

        for (Map.Entry<Integer, Collection<Integer>> entry : multimap.asMap().entrySet()) {
            Integer key = entry.getKey();
            Collection<Integer> value = entry.getValue();

            System.out.println(key + " -> " + value);
        }
    }
}

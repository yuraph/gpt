package com.gpengtao.java.collection;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2018/5/2 下午8:44.
 */
public class MapTest {

    @Test
    public void test_hash_map() {
        // permit null
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 13; i++) {
            map.put(i, i);
        }

        System.out.println(map.get(null));
    }

    @Test
    public void test_hash_table() {
        // synchronized and not permit null
        Map<Integer, Integer> table = new Hashtable<>();
        table.put(1, 2);
        table.put(null, 1);
    }

    @Test
    public void test_sort_map() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("33333333", 300);
        map.put("11111111", 100);
        map.put("55555555", 500);
        map.put("22222222", 200);
        map.put("44444444", 400);

        System.out.println(map);

        Map<String, Integer> sort = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(sort);

        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        System.out.println(collect);

        // treeMap排序
        Map<String, Integer> treeMap = new TreeMap<>(map);
        System.out.println(treeMap);
    }

    @Test
    public void test_map_key_value_reverse() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "2");
        map.put("4", "2");
        map.put("5", "2");

        Map<String, String> reverseMap = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, new BinaryOperator<String>() {
            @Override
            public String apply(String s1, String s2) {
                return s2;
            }
        }));

        System.out.println(reverseMap);

    }
}

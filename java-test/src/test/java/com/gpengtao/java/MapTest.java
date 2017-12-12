package com.gpengtao.java;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2017/12/12.
 */
public class MapTest {

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
    }
}

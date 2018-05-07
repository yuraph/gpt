package com.gpengtao.java.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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
}

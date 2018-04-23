package com.gpengtao.oter;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Created by pengtao.geng on 2017/10/18.
 */
public class Simple {

    public static void main(String[] args) {
        System.out.println((int)' ');
        System.out.println((int)' ');
    }

    @Test
    public void simple_test(){

        int a = 1;

        just_print(a++);
    }

    private void just_print(int i) {
        System.out.println(i);
    }


    @Test
    public void test_map(){

        Map<String, String> map = Maps.newHashMap();
        map.put("11", "11");

        process(map);

        System.out.println(map);
    }

    private void process(Map<String, String> map) {
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("22", "22");

        map = map2;
    }
}

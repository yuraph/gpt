package com.gpengtao.java.other;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.Range;

import java.util.Map;

/**
 * Created by pengtao.geng on 2017/11/25.
 */
public class RangeTest {

    public static void main(String[] args) {
        Range range = Range.between(1, 10);

        System.out.println(range);

        Map<String, String> map = Maps.newHashMap();

        map.put("hello", null);

        System.out.println(map);

    }
}

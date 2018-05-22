package com.gpengtao.java.jmx;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/22 下午4:57.
 */
public class OrderService implements OrderMXBean {

    @Override
    public List<String> query() {
        return Lists.newArrayList("a", "b", "c");
    }
}

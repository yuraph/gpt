package com.gpengtao.java.jmx;

import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/29 上午11:05.
 */
public class JmxTestGC {

    @Test
    public void show_gc() {
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();

        // PS Scavenge
        // PS MarkSweep
        for (GarbageCollectorMXBean bean : beans) {
            System.out.println(bean.getName());
        }
    }
}

package com.gpengtao.java.jmx;

import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by pengtao.geng on 2018/5/22 下午4:58.
 */
public class JmxTest {

    @Test
    public void test_jmx() throws Exception {
        OrderService orderService = new OrderService();

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(orderService, new ObjectName("jmxBean:name=orderService"));

        System.out.println(mBeanServer.getMBeanCount());

    }
}

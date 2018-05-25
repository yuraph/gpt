package com.gpengtao.java.jmx;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.*;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * JMX（Java Management Extensions，即Java管理扩展）是一个为应用程序、设备、系统等植入管理功能的框架。
 * <p>
 * Created by pengtao.geng on 2016/4/21.
 */
public class JmxTest {

    /**
     * 简单的mbean注册
     *
     * @throws Exception
     */
    @Test
    public void simple_register() throws Exception {
        // 路径随便写:name=名字随便写（(name也可以是type关键字)）
        ObjectName objectName = new ObjectName("com.gpengtao.x.y.z:name=HelloWold");

        Hello helloObject = new Hello();

        // 注册到mBeanServer
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(helloObject, objectName);

        System.out.println("registry finish");

        TimeUnit.SECONDS.sleep(10000);
    }

    /**
     * 可以被远程连接的mbean注册
     *
     * @throws Exception
     */
    @Test
    public void register_jmx_remote_server() throws Exception {
        ObjectName objectName = new ObjectName("com.gpengtao.x.y.z:name=HelloWoldRemote");
        Hello helloObject = new Hello();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(helloObject, objectName);

        LocateRegistry.createRegistry(9999);
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);

        jmxConnectorServer.start();

        System.out.println("registry remote finish");

        TimeUnit.SECONDS.sleep(10000);
    }

    /**
     * 连接远程的jmx
     *
     * @throws Exception
     */
    @Test
    public void test_connect_remote_jmx() throws Exception {
        Map<String, Object> env = Maps.newHashMap();
        env.put(javax.management.remote.JMXConnector.CREDENTIALS, new String[]{"xx", "xx"});

        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, env);

        MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        String[] domains = mBeanServerConnection.getDomains();

        // 排序，打印
        List<String> domainList = Lists.newArrayList(domains);
        domainList.sort(Ordering.natural());
        for (String domain : domainList) {
            System.out.println("domain: " + domain);
        }

        // mbean数量
        System.out.println("mbean count: " + mBeanServerConnection.getMBeanCount());

        // 获得所有mbean
        Set<ObjectName> objectNames = mBeanServerConnection.queryNames(null, null);
        System.out.println(objectNames);

        for (ObjectName objectName : objectNames) {
            System.out.println(objectName);
        }
    }
}

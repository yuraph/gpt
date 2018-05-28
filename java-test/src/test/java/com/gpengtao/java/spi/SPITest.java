package com.gpengtao.java.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * Created by pengtao.geng on 2018/5/25 下午6:29.
 */
public class SPITest {

    @Test
    public void test() {
        ServiceLoader<SayHelloable> loader = ServiceLoader.load(SayHelloable.class);

        for (SayHelloable hello : loader) {
            System.out.println(hello);
            hello.say();
        }
    }
}

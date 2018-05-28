package com.gpengtao.java.spi;

/**
 * Created by pengtao.geng on 2018/5/25 下午6:42.
 */
public class SayEnglishHello implements SayHelloable {

    @Override
    public void say() {
        System.out.println("hello");
    }
}

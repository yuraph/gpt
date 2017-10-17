package com.gpengtao.handler;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.Method;

/**
 * ��̬�ֽ�������
 * <p>
 * Created by gpengtao on 3/30/15.
 */
public class SayHiCallBack implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("say hi call back obj:" + obj.getClass());
        System.out.println("say hi call back method:" + method);
        System.out.println("say hi call back args:" + ToStringBuilder.reflectionToString(args));
        System.out.println("say hi call back proxy:" + proxy);
        proxy.invokeSuper(obj, args);
        return null;
    }
}

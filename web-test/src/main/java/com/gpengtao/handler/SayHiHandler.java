package com.gpengtao.handler;

import com.gpengtao.interfaces.ISayHiable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��̬����
 * <p>
 * Created by gpengtao on 3/29/15.
 */
public class SayHiHandler implements InvocationHandler {

    private ISayHiable target;

    public SayHiHandler(ISayHiable sayHiable) {
        target = sayHiable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("say hi handler proxy :" + proxy.getClass());
        System.out.println("say hi handler method :" + method);
        System.out.println("say hi handler args :" + ToStringBuilder.reflectionToString(args));

        method.invoke(target, args);
        return null;
    }
}

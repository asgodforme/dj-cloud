package com.dj.cloud.mybatis.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RenterInvocationHandler<T> implements InvocationHandler {
    private T target;

    public RenterInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("租客与中介进行交流");
        Object result = method.invoke(target, args);
        return result;
    }
}

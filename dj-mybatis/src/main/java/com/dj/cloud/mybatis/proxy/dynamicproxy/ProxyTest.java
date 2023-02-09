package com.dj.cloud.mybatis.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        Person renter = new Renter();
        InvocationHandler renterHandler = new RenterInvocationHandler<>(renter);

        Person renterProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{ Person.class }, renterHandler);
        renterProxy.rentHouse();
    }
}

package com.dj.cloud.mybatis.proxy.staticproxy;

public class StaticProxyTest {

    public static void main(String[] args) {
        Person renter = new Renter();
        RenterProxy proxy = new RenterProxy(renter);
        proxy.rentHouse();
    }
}

package com.dj.cloud.mybatis.proxy.dynamicproxy;

public class Renter implements Person {
    @Override
    public void rentHouse() {
        System.out.println("租客租房成功！");
    }
}

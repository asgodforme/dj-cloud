package com.dj.cloud.test.jvm.initialization.a;

public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

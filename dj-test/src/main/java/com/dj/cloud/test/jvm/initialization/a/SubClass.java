package com.dj.cloud.test.jvm.initialization.a;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}

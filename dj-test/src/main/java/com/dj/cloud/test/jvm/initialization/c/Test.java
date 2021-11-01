package com.dj.cloud.test.jvm.initialization.c;

public class Test {
    static int i = 1;
    static {
        i = 0;
        System.out.println(i);
    }

//    static int i = 1;
}

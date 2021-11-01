package com.dj.cloud.test.jvm.initialization.a;

/**
 * 1。 System.out.println(SubClass.value);
 *  SuperClass init!
 * 123
 * 子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
 *
 * 2. SuperClass[] superClass = new SuperClass[10];
 * 通过数组类引用类，不会触发其初始化。创建动作有newarray指令发出
 */
public class NotInitialization {

    public static void main(String[] args) {
//        System.out.println(SubClass.value);

        SuperClass[] superClass = new SuperClass[10];
    }
}

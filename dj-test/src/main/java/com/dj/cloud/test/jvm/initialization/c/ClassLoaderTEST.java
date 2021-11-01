package com.dj.cloud.test.jvm.initialization.c;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTEST {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[0];
                    b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.dj.cloud.test.jvm.initialization.c.ClassLoaderTEST").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.dj.cloud.test.jvm.initialization.c.ClassLoaderTEST);
    }
}

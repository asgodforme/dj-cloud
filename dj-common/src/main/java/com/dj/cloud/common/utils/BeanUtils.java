package com.dj.cloud.common.utils;

import com.dj.cloud.common.exception.CoreException;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;

public class BeanUtils {

    private static Gson gson = new Gson();

    public static boolean isBaseType(Class className) {
        if (className.equals(int.class) ||
                className.equals(byte.class) ||
                className.equals(long.class) ||
                className.equals(double.class) ||
                className.equals(float.class) ||
                className.equals(char.class) ||
                className.equals(short.class) ||
                className.equals(boolean.class)||
                className.equals(String.class)) {
            return true;
        }
        return false;
    }

    public static Map<String, Object> toMap(Object object) throws CoreException {
        Class clazz = object.getClass();
        Map<String, Object> map = new HashMap<>();

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        for (Field field: fields) {
            try {
                field.setAccessible(true);
                if (field.get(object) != null) {
                    if (field.getType().getName().contains("java.lang") || field.getType().equals(Date.class)) {
                        map.put(field.getName(), field.get(object));
                    }else {
                        map.put(field.getName(), gson.fromJson(gson.toJson(field.get(object)), field.getType()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new CoreException("object to map exception", "对象转换异常！");
            }
        }
        return map;
    }

    public static <T> T toObject(Map<String, Object> map, Class<T> clazz) throws CoreException {
        T t;
        try {
            t = clazz.getConstructor().newInstance();
            for (Map.Entry<String, Object> entry: map.entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                if (field.getType().getName().contains("java.lang") || field.getType().equals(Date.class)) {
                    field.set(t, entry.getValue());
                } else {
                    field.set(t, gson.fromJson(gson.toJson(entry.getValue()), field.getType()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CoreException("object to map exception", "对象转换异常！");
        }
        return t;
    }
}

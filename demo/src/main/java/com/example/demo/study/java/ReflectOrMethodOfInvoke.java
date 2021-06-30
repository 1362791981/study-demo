package com.example.demo.study.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: java反射及Method的invoke方法
 * @ClassName: ReflectOrMethodOfInvoke
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-17 14:57
 */
public class ReflectOrMethodOfInvoke {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.example.demo.study.java.common.Hello");
        Object object = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("hello");
        method.invoke(object);
    }
}

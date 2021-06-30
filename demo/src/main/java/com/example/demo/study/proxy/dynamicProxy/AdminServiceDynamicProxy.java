package com.example.demo.study.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description: jdk动态代理类
 * @ClassName: AdminServiceDynamicProxy
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 11:31
 */
public class AdminServiceDynamicProxy {

    private Object target;
    private InvocationHandler invocationHandler;

    public AdminServiceDynamicProxy(Object target, InvocationHandler invocationHandler) {
        this.target = target;
        this.invocationHandler = invocationHandler;
    }

    public Object newProxyInstance() {
        Object obj = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
        return obj;
    }
}

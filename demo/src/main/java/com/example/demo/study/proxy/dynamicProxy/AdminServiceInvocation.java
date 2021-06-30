package com.example.demo.study.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: 重写时间处理器方法
 * @ClassName: AdminServiceInvocation
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 11:27
 */
public class AdminServiceInvocation implements InvocationHandler {

    private Object target;

    public AdminServiceInvocation(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断用户是否有权限进行操作");
        Object object = method.invoke(target);
        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
        return object;
    }
}

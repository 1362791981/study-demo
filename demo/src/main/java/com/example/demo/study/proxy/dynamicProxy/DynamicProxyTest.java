package com.example.demo.study.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @ClassName: DynamicProxyTest
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 11:36
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        //方法一，使用自定义动态代理类
        AdminService adminService = new AdminServiceImpl();
        AdminServiceInvocation adminServiceInvocation = new AdminServiceInvocation(adminService);

        AdminServiceDynamicProxy adminServiceDynamicProxy = new AdminServiceDynamicProxy(adminService, adminServiceInvocation);
        AdminService proxy = (AdminService) adminServiceDynamicProxy.newProxyInstance();
        proxy.addUser();
        proxy.getUserName();
        System.out.println("=============================================");

        //方法二
        AdminService proxy2 = (AdminService) Proxy.newProxyInstance(adminService.getClass().getClassLoader(), adminService.getClass().getInterfaces(), adminServiceInvocation);
        proxy2.addUser();
        proxy2.getUserName();
        System.out.println("=============================================");

        //方法三
        AdminService proxy3 = (AdminService) Proxy.newProxyInstance(adminService.getClass().getClassLoader(), adminService.getClass().getInterfaces(), new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("判断用户是否有权限进行操作");
                Object obj = method.invoke(adminService, args);
                System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
                return obj;
            }
        });
        proxy2.addUser();
        proxy2.getUserName();
    }
}

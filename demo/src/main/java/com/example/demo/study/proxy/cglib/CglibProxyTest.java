package com.example.demo.study.proxy.cglib;

/**
 * @Description:
 * @ClassName: CglibProxyTest
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 13:56
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        AdminCglibService target = new AdminCglibService();
        AdminServiceCglibProxy proxyFactory = new AdminServiceCglibProxy(target);
        AdminCglibService proxy = (AdminCglibService)proxyFactory.getProxyInstance();

        System.out.println("代理对象：" + proxy.getClass());

        Object obj = proxy.addUser();
        System.out.println("find 返回对象：" + obj.getClass());
        System.out.println("----------------------------------");
        proxy.addUser();
        proxy.getUserName();
    }
}

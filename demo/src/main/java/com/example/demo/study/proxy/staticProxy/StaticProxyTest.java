package com.example.demo.study.proxy.staticProxy;

/**
 * @Description:
 * @ClassName: MainTest
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 11:05
 */
public class StaticProxyTest {

    public static void main(String[] args) {

        AdminService adminService = new AdminServiceImpl();

        AdminServiceProxy adminServiceProxy = new AdminServiceProxy(adminService);

        adminServiceProxy.getUserName();
        adminServiceProxy.addUser();
        System.out.println("结束啦~~~~~~~~~~~~~~~~");
    }
}

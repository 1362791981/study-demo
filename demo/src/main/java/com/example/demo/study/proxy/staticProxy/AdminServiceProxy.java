package com.example.demo.study.proxy.staticProxy;

/**
 * @Description: AdminService的静态代理类
 * @ClassName: AdminServiceProxy
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 11:01
 */
public class AdminServiceProxy implements AdminService {

    private AdminService adminService;

    public AdminServiceProxy(AdminService adminService) {
        this.adminService = adminService;
    }

    public String getUserName() {
        System.out.printf("我是静态代理，呜呜呜 方法：%s\n", "getUserName");
        return adminService.getUserName();
    }

    public boolean addUser() {
        System.out.printf("我是静态代理，呜呜呜 方法：%s\n", "addUser");
        return adminService.addUser();
    }
}

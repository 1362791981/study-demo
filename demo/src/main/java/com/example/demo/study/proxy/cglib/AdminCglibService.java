package com.example.demo.study.proxy.cglib;

/**
 * @Description:
 * @ClassName: AdminCglibService
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 13:53
 */
public class AdminCglibService {

    public String getUserName() {
        System.out.println("我是超级管理员用户");
        return "我是超级管理员用户";
    }

    public boolean addUser() {
        System.out.println("添加成功了");
        return true;
    }
}

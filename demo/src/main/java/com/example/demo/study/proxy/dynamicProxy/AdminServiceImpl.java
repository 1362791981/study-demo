package com.example.demo.study.proxy.dynamicProxy;

/**
 * @Description: 真实对象实现类
 * @ClassName: AdminServiceImpl
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-18 10:56
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public String getUserName() {
        System.out.println("我是超级管理员用户");
        return "我是超级管理员用户";
    }

    @Override
    public boolean addUser() {
        System.out.println("添加成功了");
        return true;
    }
}

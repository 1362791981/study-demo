package com.example.demo.test;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * java顶级父类Object测试
 */
public class ObjectTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Object object = new Object();

        Class aclass = object.getClass();
        System.out.println(aclass);

        Object bclass = Class.forName("com.example.demo.test.ObjectTest");
        System.out.println(bclass);

        Persion persion = new Persion();
        System.out.println(persion.toString());

        //wait()/ wait(long)/ waite(long,int)

    }


    public void Object() {

    }
}

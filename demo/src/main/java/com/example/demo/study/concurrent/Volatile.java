package com.example.demo.study.concurrent;

/**
 * @Description: Volatile关键字->保证内存可见性
 * @ClassName: Volatile
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-25 17:00
 */
public class Volatile {

    public static void main(String[] args) {
        Zhaomx zhaomx = new Zhaomx();
        zhaomx.start();
        for (; ; ) {
            if (zhaomx.isZhaomx()) {
                System.out.println("哈哈哈哈哈哈");
            }
        }
    }
}

class Zhaomx extends Thread {
    private boolean flag = false;

    public boolean isZhaomx() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            //super.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        flag = true;
    }
}

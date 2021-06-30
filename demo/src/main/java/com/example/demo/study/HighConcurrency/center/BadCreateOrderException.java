package com.example.demo.study.HighConcurrency.center;

/**
 * @Description: 订单异常类
 * @ClassName: CreateOrderErrorExcetion
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 20:56
 */
public class BadCreateOrderException extends RuntimeException{

    public BadCreateOrderException(String message) {
        super(message);
    }

    public BadCreateOrderException() {
    }
}

package com.example.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Value("${test.str}")
    private String str;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return str;
    }
}

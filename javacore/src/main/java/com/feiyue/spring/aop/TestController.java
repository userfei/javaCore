package com.feiyue.spring.aop;

import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    public void div(int k, int p) {
        System.out.println(k / p);
    }
}

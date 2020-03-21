package com.feiyue.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextConfig.class})
public class TestControllerTestV2 {

    @Autowired
    private TestController controller;

    @Test
    public void div() {
        controller.div(3, 1);
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);

        TestController controller = context.getBean(TestController.class);
        controller.div(3, 2);
    }

}
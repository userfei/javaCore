package com.feiyue.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestControllerTest {

    @Test
    public void div() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
        TestController controller = context.getBean(TestController.class);
        controller.div(3, 2);
    }

}
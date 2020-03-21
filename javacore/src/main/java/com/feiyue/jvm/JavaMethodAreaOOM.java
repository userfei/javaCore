package com.feiyue.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助 CGLib 使方法区出现内存溢出异常(JDK1.7之前才使用永久代)
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author  feiyue
 * @date  2019/9/22
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            System.out.println(i++);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {}
}

package com.feiyue.jvm;

/**
 * unable to create new native thread 这个报错和操作系统有关以及权限设置有关：
 *  比如 Centos7 在配置文件 /etc/security/limits.d/20-nproc.conf 和 /etc/systemd/system.conf 可以进行线程数的配置
 * @author  feiyue
 * @date  2019/12/15
 */
public class UnableToCreateThread {

    public static void main(String[] args) {
        for (int i=0; ;i++) {
            new Thread(()->{
                System.out.println("new thread:" + Thread.currentThread().getName());
            }).start();
        }
    }
}

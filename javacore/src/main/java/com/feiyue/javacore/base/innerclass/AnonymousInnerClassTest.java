package com.feiyue.javacore.base.innerclass;

public class AnonymousInnerClassTest {

    public static void main(String[] args) {
        // 使用成员内部类（需要定义内部类名为Job）
        AnonymousInnerClassTest classTest = new AnonymousInnerClassTest();
        Job job = classTest.new Job();
        Thread thread = new Thread(job);
        thread.start();

        // JDK7 匿名内部类的写法(省略类名)
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run run run!");
            }
        }).start();

        // JDK8 lambda 表达式用法(省略类名、方法名甚至参数类型)
        new Thread(() -> {
            System.out.println("fly fly fly!");
        }).start();
    }

    // 成员内部类
    class Job implements Runnable {
        @Override
        public void run() {
            System.out.println("go go go!");
        }
    }
}

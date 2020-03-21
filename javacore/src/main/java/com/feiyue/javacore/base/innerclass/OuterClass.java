package com.feiyue.javacore.base.innerclass;

public class OuterClass {

    private String name;

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();
        outerClass.test();
        System.out.println(StaticInnerClass.staticName);
        System.out.println(new OuterClass().test());
    }

    public int test() {
        int localVar = 1;
        // 方法内部类
        class MethodInnerClass {
            int methodClassVar = localVar;
            public void print() {
                System.out.println("method class method, var=" + methodClassVar);
            }
        }
        MethodInnerClass methodInnerClass = new MethodInnerClass();
        methodInnerClass.print();
        return localVar;


    }

    /**
     * 静态嵌套类，作为外部类的类变量
     *      1、只能直接访问外部类的类变量
     *      2、可以通过内部引用外部对象实例，达到访问外部类的成员变量（
     * @author  feiyue
     * @date  2019/12/4
     */
    public static class StaticInnerClass {
        public int age;
        private static String staticName;

        private OuterClass outerClass;
        StaticInnerClass(OuterClass outerClass) {
            this.outerClass = outerClass;
        }
    }

    /**
     * 直接内部类，作为外部类的成员变量
     * @author  feiyue
     * @date  2019/12/4
     */
    public class InnerClass {
        String innerName = name;

        public void print() {
            System.out.println(1);
        }
    }
}

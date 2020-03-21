package com.feiyue.jvm;

/**
 * vm Args: -Xss128k
 * @author  feiyue
 * @date  2019/9/22
 */
public class JavaVMStackSOF {

    public static int stackLength = 1;

    public static void main(String[] args) throws Throwable{
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + stackLength);
            throw e;
        }

    }

    private void stackLeak() {
        stackLength++;
        stackLeak();
    }
}

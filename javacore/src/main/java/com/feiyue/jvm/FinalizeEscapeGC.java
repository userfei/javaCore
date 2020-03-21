package com.feiyue.jvm;

/**
 * 此代码演示两点：
 *      1、对象可以在 GC 时自我拯救
 *      2、这种自救的机会只有一次，一个对象的 finalize 方法最能只能被系统自动调用一次
 * @author  feiyue
 * @date  2019/10/26
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();

        // SAVE_HOOK 对象第一次成功自救
        SAVE_HOOK = null;
        System.gc();

        // 因为 Finalize 方法优先级低，当前线程暂停 0.5s
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no i am dead :(");
        }

        // 下面这段代码和上面完全相同，但是这次自救失败
        SAVE_HOOK = null;
        System.gc();

        // 因为 Finalize 方法优先级低，当前线程暂停 0.5s
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no i am dead :(");
        }
    }
}

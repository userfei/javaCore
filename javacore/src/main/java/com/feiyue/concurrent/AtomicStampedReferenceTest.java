package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 利用 AtomicStampedReference 解决 ABA 问题
 * @author  feiyue
 * @date  2019/12/7
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference(1, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            stampedReference.compareAndSet(1, 100, 1, 2);
            System.out.println("update value from 1 to " + stampedReference.getReference()
                    + "version 1 to " + stampedReference.getStamp());
            stampedReference.compareAndSet(100, 1, 2, 3);
            System.out.println("update value from 100 to " + stampedReference.getReference()
                    + "version 2 to " + stampedReference.getStamp());
        }).start();

        // t2 确保上个线程完成一次CAS的ABA设值
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean casResult = stampedReference.compareAndSet(1, 100, 1, 2);
        System.out.println("caseResult: " + casResult +
                ", atomicReferenceValue: " + stampedReference.getReference() +
                ", current stamp: " + stampedReference.getStamp());
    }
}

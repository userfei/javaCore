package com.feiyue.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

    public static void main(String[] args) {

        Object o1 = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> reference = new PhantomReference(o1, queue);

        // null
        System.out.println(reference.get());
        // null
        System.out.println(queue.poll());

        o1 = null;
        System.gc();

        // null
        System.out.println(reference.get());
        // java.lang.ref.PhantomReference@4dc63996
        System.out.println(queue.poll());
    }
}

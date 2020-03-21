package com.feiyue.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10M
 * @author  feiyue
 * @date  2019/10/23
 */
public class DirectMemoryOOM {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1M);
        }
    }
}

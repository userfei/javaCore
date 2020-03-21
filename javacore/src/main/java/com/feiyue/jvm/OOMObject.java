package com.feiyue.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM 参数：-Xms100M -Xmx100M -XX:UseSerialGC
 */
public class OOMObject {

    public byte[] placeHolder = new byte[64 * 1024];

    public static void main(String[] args) throws Exception{
        Integer a = 3;
        Integer b = 3;
        System.out.println(a == b);
        fillHeap(1000);
    }

    private static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i< num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}

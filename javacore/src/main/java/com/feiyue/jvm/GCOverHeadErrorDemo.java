package com.feiyue.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * GC 时间过长（每次GC回收的内存很少,导致需要不断重复GC）
 * VM Args: -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5M
 * @author  feiyue
 * @date  2019/10/23
 */
public class GCOverHeadErrorDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        int i = 0;

        try {
            while (true) {
                list.add(String.valueOf(i++));
            }
        } catch (Throwable e) {
            System.out.println(list.size());
            e.printStackTrace();
            throw e;
        }

    }
}

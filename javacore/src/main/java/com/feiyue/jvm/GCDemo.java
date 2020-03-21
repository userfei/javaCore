package com.feiyue.jvm;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author  feiyue
 * @date  2019/9/23
 */
public class  GCDemo {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        testAllocation();
    }

    /**
     * GC log:
     *
     * [GC (Allocation Failure) [PSYoungGen: 6741K->1000K(9216K)] 6741K->5200K(19456K), 0.0274954 secs]
     * [Times: user=0.00 sys=0.00, real=0.04 secs]
     *
     * PSYoungGen: 年轻代 使用 Parallel Scavenge 收集器
     * 6741K->1000K(9216K) GC前使用 6741K, GC后使用1000K, 年轻代可用内存9M
     * 6741K->5200K(19456K) GC前堆使用的容量6741K，GC后堆使用的容量5200K，堆内存大小20M
     */
    public static void testAllocation() throws Exception{
        byte[] allocation1, allocation2, allocation3, allocation4;

        // gc 前对象优先分配到 Eden 区
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        Thread.sleep(1000);

        /**
         * 1、Eden 区内存不够，发生 Minor gc；
         * 2、Survivor 区只有 1M 大小，无法容纳 2M 的 allocation1 等对象
         * 3、通过分配担保机制将 allocation1 等对象转移到老年代3
         */
        //System.gc();
        allocation4 = new byte[4 * _1MB];
    }
}

package com.feiyue.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * VM Args: -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:MaxMetaspaceSize=5m -XX:MaxDirectMemorySize=5M -XX:+PrintCommandLineFlags
 * @author  feiyue
 * @date  2019/9/22
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) throws Exception {
        // 使用 List 保持常量池引用，避免 Full GC 回收常量池行为
        List<String> list = new ArrayList<>();
        TimeUnit.SECONDS.sleep(1);

        // 10MB 的 PermSize 在 integer 范围内足够产生 OOM
        int i = 0;
        while (true) {
            // JDK1.8 之后，常量池不在属于永久代
            list.add(String.valueOf(i++).intern());
            if (i % 1000 == 0) {
                list = new ArrayList<>();
            }
            System.out.println(i);
        }
    }
}

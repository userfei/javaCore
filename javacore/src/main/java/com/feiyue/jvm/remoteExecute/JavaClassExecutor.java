package com.feiyue.jvm.remoteExecute;

import java.lang.reflect.Method;

/**
 * javaClass 执行工具
 * @author  feiyue
 * @date  2019/11/24
 */
public class JavaClassExecutor {

    /**
     * 执行外部输入的字节数组，然后修改代表 java.lang.System 的 CONSTANT_Utf8_info常量为 System.out/error.HackSystem
     * @author feiyue
     * @date 2019/11/24 16:40
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modifyBytes = cm.modifyUTF8Constant("java/lang/System",
                "com/feiyue/jvm/remoteExecute/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modifyBytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable t) {
            t.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}

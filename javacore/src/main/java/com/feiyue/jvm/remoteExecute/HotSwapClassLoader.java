package com.feiyue.jvm.remoteExecute;

/**
 * 自定义类加载器，多次载入执行类
 *   1、该加载器没有重写 loadClass 和 findClass 方法，如果外部不调用 loadByte 方法，那么该类加载器和父类加载器的查询范围一致
 * @author  feiyue
 * @date  2019/11/24
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        // 使用加载 HotSwapClassLoader 类的加载器作为父类加载器
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 将执行类的字节数组转为 Class 对象
     * @param classByte 执行类的字节数组
     * @return java.lang.Class
     */
    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}

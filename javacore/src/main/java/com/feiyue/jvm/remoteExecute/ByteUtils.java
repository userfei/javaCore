package com.feiyue.jvm.remoteExecute;

/**
 * Bytes 数组处理工具
 * @author  feiyue
 * @date  2019/11/24
 */
public class ByteUtils {

    /**
     * 将字节数组中特定位置的byte值转换为 int值
     * @author feiyue
     * @date 2019/11/24 15:33
     */
    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            // byte 转 int, 保留低 8位的值
            int n = ((int) b[i]) & 0xff;

            // 双字节，高位字节需要按位右移 8 位
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    /**
     * 将一个 int 型的值转换为 byte 数组
     * @author feiyue
     * @date 2019/11/24 15:33
     */
    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int end) {
        return new String(b, start, end);
    }

    public static byte[] string2Bytes(String string) {
        return string.getBytes();
    }

    /**
     * 替换字节数组中特定位置的数据
     * @author feiyue
     * @date 2019/11/24 16:00
     * @param  originalBytes: 字节数组
     * @param  offset: 要替换数据的起始偏移位置
     * @param  len: 要替换的字节长度
     * @param  replaceBytes: 新的字节数据
     * @return 新的字节数组
     */
    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];

        // 拷贝原数组的 0~offset 到 newBytes 数组中
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);

        // 拷贝要替换数据接续到 newBytes 数组中
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);

        // 拷贝原数组中要替换数据后面的数据接续到 newBytes 数组中
        System.arraycopy(originalBytes, offset + len, newBytes,
                offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }
}

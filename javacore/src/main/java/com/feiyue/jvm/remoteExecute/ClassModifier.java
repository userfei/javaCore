package com.feiyue.jvm.remoteExecute;

/**
 * 修改 class 文件，暂时只提供修改常量池常量的功能
 * @author  feiyue
 * @date  2019/11/24
 */
public class ClassModifier {

    /**
     * Class 文件中常量池的起始偏移量 = 8
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info 常量的 tag 标志
     */
    private static final int CONSTANT_Utf8_info = 1;

    /**
     * 常量池 11 种常量所占的长度，CONSTANT_Utf8_info 常量除外（不定长），常量池中常量 tag 依次为 1,3,4...
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};
    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修改常量池中 CONSTANT_Utf8_info 常量的内容
     *
     * @param oldStr : 修改前字符串
     * @param newStr : 修改后字符串
     * @return 修改结果
     * @author feiyue
     * @date 2019/11/24 15:05
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();

        // offset 是常量池的 value 的初始偏移字节数
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++) {
            // 常量池每项常量的 tag 均为 u1 类型
            int tag = ByteUtils.bytes2Int(classByte, offset, u1);
            if (tag == CONSTANT_Utf8_info) {

                // CONSTANT_Utf8_info 型常量构成为 u1(tag) + u2(len) + u1*len
                int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);
                offset += (u1 + u2);

                // 获取 CONSTANT_Utf8_info 常量所代表的字符串
                String str = ByteUtils.bytes2String(classByte, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    // 修改后的字符串转换为字节数组
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    // 字符串的长度对应字节数，该字节数在双字节数组中表现形式
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
                    classByte = ByteUtils.bytesReplace(classByte, offset - u2, u2, strLen);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            } else {
                // 根据 tag 取得常量所占字节，然后跳过该常量
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中常量的数量
     * @author feiyue
     * @date 2019/11/24 15:07
     * @return 常量池常量的数量
     */
    public int getConstantPoolCount() {
        // 常量池大小占2个字节
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}

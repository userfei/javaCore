package com.feiyue.javacore.advance.chaptertwo;

import java.io.*;

public class InputOutputStreamDemo {

    public static void main(String[] args){
        InputStream inputStream;
        OutputStream outputStream;
        byte[] in = new byte[1024];
        try {

            // 读取文件中的字节到内存数组中
            inputStream = new FileInputStream(new File("C:\\Users\\zhou.xm\\Desktop\\test.txt"));
            inputStream.read(in, 0, 1024);
            System.out.println("读入的字节数：" + in.length);

            // 输出数组的字节到文件中
            outputStream = new FileOutputStream(new File("C:\\Users\\zhou.xm\\Desktop\\test2.txt"));
            outputStream.write(in, 0, 1024);
            outputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

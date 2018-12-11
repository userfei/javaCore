package com.feiyue.javacore.advance.chaptertwo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderWriterDemo {

    public static void main(String[] args) throws Exception{
        Reader reader = new InputStreamReader(new FileInputStream("C:\\Users\\zhou.xm\\Desktop\\test.txt"));
        Writer writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\zhou.xm\\Desktop\\test3.txt"));
        char[] data = new char[8192];
        try{
            reader.read(data);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

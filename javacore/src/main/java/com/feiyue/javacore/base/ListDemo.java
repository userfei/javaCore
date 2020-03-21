package com.feiyue.javacore.base;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 *  对象集合的深复制和浅复制 -- **基本类型和字符串例外**
 *      1、浅复制：二者指向同一个内存地址
 *          aList = bList;
 *          Collections.copy(dest, src);
 *          aList.addAll(bList);
 *
 *      2、深复制：二者指向不同的内存地址
 *          通过序列化实现
 *
 *      3、数组的Arrays.copyOf()方法也是浅复制
 */
public class ListDemo {

    public static void main(String[] args){
        List data = Arrays.asList(3, 1, 2);   // 这种 list 是不能修改的
        // unsupported operation
        // data.add(0);

        System.out.println("---------------------------");
        People p1 = new People("lihua");
        People p2 = new People("xiaohua");
        List<People> people1  = new ArrayList<>();
        List<People> people2  = new ArrayList<>();
        people1.add(p1);

        people2.addAll(people1);  // 浅复制
        people2.get(0).setName("newName");  // 修改People2集合也会影响people1集合
        for(People p : people1){
            System.out.print(p.getName() + " ");
        }

        people2.add(p2);  // 新增元素不影响源集合
        System.out.println();
        System.out.println("目的集合新增元素：");
        for(People p : people1){
            System.out.print(p.getName() + " ");
        }

        List<People> people3  = new ArrayList<>();
        people3.addAll(people2);
        people1.get(0).setName("oldName");
        Collections.copy(people3, people1);  // 浅复制
        System.out.println("目的集合新增元素：");
        for(People p : people3){
            System.out.print(p.getName() + " ");
        }
        System.out.println();
        people3.get(0).setName("name3");
        for(People p : people2){
            System.out.print(p.getName() + " ");
        }

        System.out.println();
        List<People> people4  = depCopy(people1);  // 深度复制，修改目的集合不影响源集合
        people4.get(0).setName("deepName");
        System.out.println("深度复制后，源集合：");
        for(People p : people1){
            System.out.println(p.getName() + " ");
        }
        System.out.println("深度复制后，目的集合：");
        for(People p : people4){
            System.out.println(p.getName() + " ");
        }

    }

    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class People implements Cloneable, Serializable{
    String name;

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

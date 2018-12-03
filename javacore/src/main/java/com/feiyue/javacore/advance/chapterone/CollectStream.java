package com.feiyue.javacore.advance.chapterone;

import java.util.*;
import java.util.stream.Collectors;

public class CollectStream {

    public static void main(String[] args){

        List<String> data = Arrays.asList("a", "1", "22", "a ", "bb");

        // forEach 方法遍历流中元素, 并行流的时候顺序不能保证, forEachOrdered 可以在并行处理保证顺序
        data.parallelStream().forEach(System.out::print);
        System.out.println();
        data.parallelStream().forEachOrdered(System.out::print);

        // toArray 产生一个对象数组
        Object[] arr = data.stream().toArray();
        for(Object object : arr){
            System.out.print(object + " ");
        }
        System.out.println();

        // toList, toSet, toCollection
        List<String> aList = data.stream().collect(Collectors.toList());
        System.out.print("遍历list: ");
        for(String str : aList){
            System.out.print(str + " ");
        }
        System.out.println();

        Set<String> aSet = data.stream().collect(Collectors.toSet());
        System.out.print("遍历set: ");
        for(String str : aSet){
            System.out.print(str + " ");
        }
        System.out.println();

        TreeSet<String> tSet = data.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.print("遍历TreeSet: ");
        for(String str : tSet){
            System.out.print(str + " ");
        }
        System.out.println();

        // Collectors.joining() 拼接字符串结果
        String result = data.stream()
                .collect(Collectors.joining(",", "begin: ", " :end"));
        System.out.println("字符串拼接结果：" + result);

        // toMap, toConcurrentMap 收集到映射表map中
        Map<String, Integer> cMap = data.parallelStream()
                .collect(Collectors.toConcurrentMap(String::trim, String::length, (oldValue, currentValue)->currentValue));
        System.out.println("遍历concurrentMap: ");
        for(Map.Entry<String, Integer> entry : cMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // List<String> data = Arrays.asList("a", "1", "22", "a ", "bb");
        Map<String, Integer> aMap = data.stream()
                .collect(Collectors.toMap(String::trim, String::length ,(oldValue, currentValue)->
                {
                    System.out.println("oldValue = " + oldValue);  // 1
                    System.out.println("currentValue = " + currentValue); // 2
                    return currentValue;
                }));
        System.out.println("遍历Map: ");
        for(Map.Entry<String, Integer> entry : aMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

package com.feiyue.javacore.advance.chapterone;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStream {

    public static void main(String[] args){
        List<String> data = Arrays.asList("a", "123", "22223", "a ", "bb21");

        // 并行执行，确保传递给流的任何函数都可以安全并行执行
        Map<Integer, Long> aMap = data.parallelStream().filter(r->r.length()>1)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        for(Map.Entry<Integer, Long> entry : aMap.entrySet()){
            System.out.println("长度为=" + entry.getKey() + "的字符串个数：" + entry.getValue());
        }
    }
}

package com.feiyue.javacore.advance.chapterone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author  feiyue
 * @date  2019/9/1
 */
public class TransformStream {

    public static void main(String[] args){

        List<String> data = Arrays.asList("a", "1", "22", "aa", "bb");

        // filter 过滤
        List<String> data1 = data.stream().filter(r->r.length()>1)
                .peek(m->System.out.print(m + " ")).collect(Collectors.toList());
        System.out.println("---过滤后长度:" + data1.size());

        // map 映射
        List<String> result = data.stream().map(r->{
            r.substring(1);
            return r;
        }).collect(Collectors.toList());

        // flatMap 映射拼接
        List<String> data2 = data.stream().flatMap(r->letters(r))
                .peek(m->System.out.print(m + " ")).collect(Collectors.toList());
        System.out.println("---映射拼接后长度:" + data2.size());


        // distinct 去重
        List<String> data3 = data2.stream().distinct()
                .peek(r->System.out.print(r + " ")).collect(Collectors.toList());
        System.out.println("---去重后长度:" + data3.size());


        // sorted 排序
        List<String> data4 = data3.stream().sorted()
                .peek(r->System.out.print(r + " ")).collect(Collectors.toList());
        System.out.println("---排序长度:" + data4.size());
    }

    public static Stream<String> letters(String s) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            // 获取字符串的每个字符
            list.add(s.substring(i, i+1));
        }
        return list.stream();
    }
}

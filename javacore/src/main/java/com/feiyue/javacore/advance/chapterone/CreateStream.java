package com.feiyue.javacore.advance.chapterone;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args){

        // 1 Stream.of 生成流
        Stream s = Stream.of("1", 2, "3", 4);
        Long count = s.filter(r->r.equals("2")).count();
        System.out.println(count);

        // 2 Stream.generate 方法生成流
        List<String> list = Stream.generate(()->"hello").limit(3).collect(Collectors.toList());
        System.out.println(list.get(0));

        // 3 Stream.iterate 方法生成流
        List<Integer> data = Stream.iterate(0, r->r+1).limit(5).collect(Collectors.toList());
        System.out.println(data.size());
        data.stream().peek(r->System.out.println(r));  // 惰性执行，不打印
        data.stream().peek(r->System.out.print(r+" ")).collect(Collectors.toList()); // 有终止操作，执行
    }
}

package com.feiyue.javacore.advance.chapterone;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTypeStream {

    public static void main(String[] args){

        // 基本类型流
        OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();
        System.out.println("基本类型数据的平均值：" + average);  // 3.0

        OptionalInt max = IntStream.rangeClosed(5, 10).max();
        System.out.println("基本类型数据的最大值：" + max);  // 10

        // 获取对应封装器对象流
        List<Integer> list = IntStream.of(1, 2, 3, 4, 5)
                .boxed().collect(Collectors.toList());
    }
}

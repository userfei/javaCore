package com.feiyue.javacore.advance.chapterone;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TerminalStream {

    public static void main(String[] args){
        List<String> data = Arrays.asList("a", "1", "22", "aa", "bb");

        // 最值 max/min
        Optional<String> max = data.stream().max(String::compareTo);
        System.out.println(max);   // bb

        // 计数
        Long count = Stream.empty().count();
        System.out.println("空流的长度 " + count);

        // 查询
        Optional<String> result = data.stream().filter(r->r.equals("c")).findFirst();
        System.out.println("找到字符串a " + result);

        // 判断
        Boolean result2 = data.stream().anyMatch(r->r.equals("c"));
        System.out.println("找到字符串c " + result2);

    }
}

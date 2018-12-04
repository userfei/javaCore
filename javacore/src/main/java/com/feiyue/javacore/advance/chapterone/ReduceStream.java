package com.feiyue.javacore.advance.chapterone;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReduceStream {

    public static void main(String[] args){

        List<String> data = Arrays.asList("a", "1", "22", "a ", "bb");

        // groupingBy 分组
        Map<String, List<String>> aMap = data.stream().collect(Collectors.groupingBy(String::trim));
        System.out.println("遍历分组后结果: ");
        for(Map.Entry<String, List<String>> entry : aMap.entrySet()){
            System.out.println("key=" + entry.getKey() + "; value=" + entry.getValue());
        }

        // reduce 约简
        Optional<String> result = data.stream().reduce((x, y)->x + y);
        System.out.println("合并字符串结果：" + result.get());  // 结果: a122a bb

        // 有幺元值的约简, 将字符串的长度累加, 由于元素是String,结果是int, 所以需要两个函数
        int length = data.stream().reduce(0,
                (x, y) -> x + y.length(),
                (total1, total2) -> total1 + total2);
        System.out.println("字符串的长度总和：" + length);
    }
}

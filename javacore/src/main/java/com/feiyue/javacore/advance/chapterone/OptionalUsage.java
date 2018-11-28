package com.feiyue.javacore.advance.chapterone;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalUsage {

    public static void main(String[] args){

        List<String> data = Arrays.asList("1", "a", "2", "b");

        // Optional 对象为空吧，寻找替代值
        Optional<String> optionalString = Optional.empty();
        String s1 = optionalString.orElse("null");
        System.out.println("optional 为空值设置默认值：" + s1);

        // Optional 对象为空时，调用方法获取默认值
        Optional<String> optionalString2 = data.stream().filter(r->r.equals("c")).findAny();
        String s2 = optionalString2.orElseGet(()->getDefaultString());
        System.out.println("optional 为空值调用默认方法获取默认值：" + s2);

        // Optional 对象为空时，抛出异常
        /*Optional<String> optionalString3 = data.stream().filter(r->r.equals("c")).findAny();
        String s3 = optionalString3.orElseThrow(NullPointerException::new);
        System.out.println("optional 为空值抛出异常：" + s3);*/


        // Optional 对象存在，才消费，不返回任何值 ifPresent

        Optional<String> optionalString4 = data.stream().filter(r->r.equals("c")).findAny();
        optionalString4.ifPresent(r->System.out.println(r + " "));  // 不打印任何也不报错


        // Optional 对象存在，map 消费，有返回值
        Optional<String> optionalString5 = data.stream().filter(r->r.equals("c")).findAny();
        String result = optionalString5.orElse(getDefaultString());
        if(optionalString5.isPresent())
            result = optionalString5.map(r->"_" + r + "_").get();  // _a_
        System.out.println("map consumer the optional value, result=" + result);

        // 使用 flatMap 构建 Optional 值的函数
        Optional<Double> optional = Optional.of(-4.0).flatMap(OptionalUsage::reverse);
        if(optional.isPresent())
            optional.ifPresent(r->System.out.println("-4 的倒数= " + r));
    }

    public static Optional<Double> reverse(Double x){
        return x != 0 ? Optional.of(1/x) : Optional.empty();
    }

    public static String getDefaultString(){
        return "null";
    }
}

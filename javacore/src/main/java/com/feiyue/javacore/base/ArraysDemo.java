package com.feiyue.javacore.base;

import java.util.Arrays;

public class ArraysDemo {

    public static void main(String[] args){

        Integer[] nums = new Integer[]{3, 1, 2};
        for(Integer data : nums){
            System.out.printf("%s-", String.valueOf(data));
        }
        System.out.println("---------------复制前");
        Integer[] otherNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(otherNums);
        for(Integer data : nums){
            System.out.printf("%s-", String.valueOf(data));
        }
        System.out.println("---------------复制后");
        for(Integer data : otherNums){
            System.out.printf("%s-", String.valueOf(data));
        }
        System.out.println("---------------排序后");
    }
}

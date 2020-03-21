package com.feiyue.designmode.strategy;

public class Hand {
    public static final int HANDVALUE_GUU = 0; // 表示石头
    public static final int HANDVALUE_CHO = 1; // 表示剪刀
    public static final int HANDVALUE_PAA = 2; // 表示布
    public static final Hand[] hand = {
            new Hand(HANDVALUE_GUU),
            new Hand(HANDVALUE_CHO),
            new Hand(HANDVALUE_PAA),
    };

    private static final String[] name ={
        "石头", "剪刀", "布",
    };

    private int handValue;
    public Hand(int handValue){
        this.handValue = handValue;
    }

    // 获取手势值对应的 Hand 实例
    public static Hand getHand(int handValue){
        return hand[handValue];
    }

    public boolean isStrongerThan(Hand h){
        return fight(h) == 1;
    }

    public boolean isWeakerThan(Hand h){
        return fight(h) == -1;
    }

    // 与 h 比较大小, 1 大于, 0 相等, -1 小于
    private int fight(Hand h) {
        if(this == h){
            return 0;
        }else if((this.handValue + 1) % 3 == (h.handValue % 3)){
            return 1;
        }else{
            return -1;
        }
    }

    public String toString(){
        return name[handValue];
    }
}

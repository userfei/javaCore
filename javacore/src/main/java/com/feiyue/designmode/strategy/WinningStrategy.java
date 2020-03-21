package com.feiyue.designmode.strategy;

import java.util.Random;

public class WinningStrategy implements Strategy {

    private Random random;
    private boolean won = false;    // 上一局是否获胜
    private Hand prevHand;

    // 含参的构造函数其实是伪随机，有可预见性; 不含参的构造函数每次都使用当前时间作为种子，随机性更强
    public WinningStrategy(int seed){
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if(!won){
            return Hand.getHand(random.nextInt(3));
        }else{
            return prevHand;
        }
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}

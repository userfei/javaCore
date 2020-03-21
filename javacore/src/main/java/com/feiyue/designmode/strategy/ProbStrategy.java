package com.feiyue.designmode.strategy;

import java.util.Random;

/**
 *  ProbStrategy 策略每次手势随机，但是每种手势的概率和之前猜拳结果有关
 *
 */
public class ProbStrategy implements Strategy {

    private Random random;
    private int prevHandValue = 0;
    private int currentHandValue = 0;

    // history[上一局手势][这局手势], 值越大过去胜率越大
    private int[][] history = {
        {1, 1, 1, },    // [0][0]=1, [0][1]=1, [0][2]=1
        {1, 1, 1, },
        {1, 1, 1, },
    };

    public ProbStrategy(int seed){
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {

        // 根据[当前手势][下一个手势]的占比, 决定下一个手势
        int bet = random.nextInt(getSum(currentHandValue));
        int handValue = 0;
        if(bet < history[currentHandValue][0]){
            handValue = 0;
        }else if(bet < history[currentHandValue][0] + history[currentHandValue][1]){
            handValue = 1;
        }else {
            handValue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handValue;
        return Hand.getHand(handValue);
    }

    private int getSum(int hv) {
        int sum = 0;
        for(int i=0; i<3; i++){
            sum += history[hv][i];
        }
        return sum;
    }

    @Override
    public void study(boolean win) {
        if(win){
            history[prevHandValue][currentHandValue]++;
        }else {
            history[prevHandValue][(currentHandValue + 1) % 3]++;
            history[prevHandValue][(currentHandValue + 2) % 3]++;
        }
    }
}

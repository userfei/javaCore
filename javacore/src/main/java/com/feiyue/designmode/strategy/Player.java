package com.feiyue.designmode.strategy;

public class Player {
    private String name;
    private Strategy strategy;
    private int winCount;
    private int loseCount;
    private int gameCount;

    public Player(String name, Strategy strategy){
        this.name = name;
        this.strategy = strategy;
    }

    public Hand nextHand(){
        return strategy.nextHand();
    }

    public void win(){      // 获胜
        strategy.study(true);
        winCount++;
        gameCount++;
    }

    public void lose(){
        strategy.study(false);  // 失败
        loseCount++;
        gameCount++;
    }

    public void even(){     // 平局
        gameCount++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", strategy=" + strategy +
                ", winCount=" + winCount +
                ", loseCount=" + loseCount +
                ", gameCount=" + gameCount +
                '}';
    }
}

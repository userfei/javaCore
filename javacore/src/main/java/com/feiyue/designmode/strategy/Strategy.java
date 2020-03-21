package com.feiyue.designmode.strategy;

public interface Strategy {
    // 下一次所出的手势
    public abstract Hand nextHand();

    // 上一局的手势是否获取，上一局获胜 study(true) 否则 study(false)
    public abstract void study(boolean win);
}

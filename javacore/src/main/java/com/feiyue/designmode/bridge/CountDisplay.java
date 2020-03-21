package com.feiyue.designmode.bridge;

public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl){
        super(impl);
    }

    // 扩展功能 - 规定显示次数
    public void multiDisplay(int times){
        open();
        for(int i=0; i<times; i++){
            print();
        }
        close();
    }
}

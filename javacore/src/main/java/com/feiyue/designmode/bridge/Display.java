package com.feiyue.designmode.bridge;

public class Display {
    private DisplayImpl impl;   // impl 是两种层次结构的桥梁

    public Display(DisplayImpl impl){
        this.impl = impl;
    }

    public void open(){
        impl.rawOpen();
    }

    public void print(){
        impl.rawPrint();
    }

    public void close(){
        impl.rawClose();
    }

    public final void display(){
        open();
        print();
        close();
    }
}

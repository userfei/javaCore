package com.feiyue.designmode.adapter;

import java.util.Properties;

public class Main {
    public static void main(String[] args){
        FileIO fileIO = new FileProperties();
        try {
            fileIO.readFromFile("file.txt");
            System.out.println("the year from origin file: " + fileIO.getValue("year"));
            fileIO.setValue("year", "2019");
            fileIO.setValue("month", "3");
            fileIO.setValue("day", "17");
            fileIO.writeToFile("newFile.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

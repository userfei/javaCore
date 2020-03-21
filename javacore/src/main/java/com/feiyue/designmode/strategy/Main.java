package com.feiyue.designmode.strategy;

public class Main {
    public static void main(String[] args){
        if(args.length != 2){
            System.out.println("Usage: java Main random1 random2");
            System.out.println("Example: java Main 314 15");
            System.exit(0);
        }

        int seed1 = Integer.parseInt(args[0]);
        int seed2 = Integer.parseInt(args[1]);
        Player player1 = new Player("小红", new WinningStrategy(seed1));
        Player player2 = new Player("小明", new ProbStrategy(seed2));
        for(int i =0; i<100; i++){
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();
            if(nextHand1.isStrongerThan(nextHand2)){
                System.out.println("winner:" + player1);
                player1.win();
                player2.lose();
            }else if(nextHand1.isWeakerThan(nextHand2)){
                System.out.println("winner:" + player2);
                player1.lose();
                player2.win();
            }else {
                System.out.println("Even...");
                player1.even();
                player2.even();
            }
        }
        System.out.println("total result:");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }
}

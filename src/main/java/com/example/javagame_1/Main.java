package com.example.javagame_1;

import java.util.Scanner;

public class Main {
    public static int rows;
    public static int columns;
    public static int amountOfEnemies;
    public static int transistorsNeeded;
    public static int moves;
    public static int getAmountOfFlowers;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String cmd;

        OptionsMenu.setDefaultGameValues();

        do {
            System.out.println("=========| Welcome to the funny game! |=========\n" +
                    "\n----------------| Main menu |----------------" +
                    "\nSelect button: ");
            System.out.println("\t---> Press 1 - Start new game.");
            System.out.println("\t---> Press 2 - Options.");
            System.out.println("\t---> Press 3 - Credits.");
            System.out.println("\t---> Press 4 - Exit.");
            System.out.println("--------------------------------------------");
            System.out.print("cmd: ");

            cmd = scan.nextLine();

            switch (cmd) {
                case "1":
                    newGame();
                    break;
                case "2":
                    OptionsMenu.showOM();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    System.out.println("-------------| The game is closed! |-------------\n");
                    System.out.println("");
                    break;
                default:
                    System.out.println("\n\n\n\n\n-----| Command not recognized! Please try again |-----\n\n\n\n\n");
            }
        } while (!cmd.equals("4"));
    }

    private static void showCredits() {
        System.out.println("--------------| Credits |--------------");
        System.out.println("Created by Mikhail Salychev.\n" +
                "Idea by Dmitrijs Finaskins.\n" +
                "Version 1.0, last modified on 19.11.2022.\n" +
                "Contact me at salychevms@gmail.com");
        System.out.println("---------------------------------------\n\n\n");
    }

    private static void newGame() {
        Game game = new Game(rows, columns, amountOfEnemies, transistorsNeeded, moves, getAmountOfFlowers);
        game.fillTheFieldIfEmpty();
        game.startGame();
    }
}

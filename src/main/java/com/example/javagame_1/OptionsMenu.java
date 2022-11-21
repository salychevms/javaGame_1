package com.example.javagame_1;

import java.util.Scanner;

public class OptionsMenu {
    //default settings for game======================
    public final static int defaultRows = 4;
    public final static int defaultColumns = 4;
    public final static int defaultEnemies = 2;
    public final static int defaultTransistors = 100;
    public final static int defaultMoves = 40;
    public final static int defaultFlowers = 6;
    //===============================================
    //this parameters use for define new game options in menu
    public static int rows;
    public static int columns;
    public static int amountOfEnemies;
    public static int transistorsNeeded;
    public static int moves;
    public static int getAmountOfFlowers;
    //==============================================
    static Scanner scan = new Scanner(System.in);
    static int cmd;
    static boolean check = false;

    public static void showOM() {
        do {
            System.out.println("--------------| Options |--------------");
            System.out.println("Select button:\n" +
                    "\t---> Press 1 - Show current settings.\n" +
                    "\t---> Press 2 - Change settings.\n" +
                    "\t---> Press 3 - Restore default settings.\n" +
                    "\t---> Press 4 - Exit options.");
            System.out.println("---------------------------------------");
            System.out.print("cmd: ");

            cmd = scan.nextInt();

            switch (cmd) {
                case 1:
                    System.out.println("--------------| Current settings |--------------");
                    System.out.println("Rows: " + Main.rows +
                            "\nColumns: " + Main.columns +
                            "\nEnemies: " + Main.amountOfEnemies +
                            "\nTransistors: " + Main.transistorsNeeded +
                            "\nMoves: " + Main.moves +
                            "\nPoint of spawn: " + Main.getAmountOfFlowers);
                    System.out.println("------------------------------------------------\n");
                    break;
                case 2:
                    scan.nextLine();
                    do {
                        System.out.print("---> Rows <---\nLeave blank to keep current value [" + Main.rows + "]\nOr enter new value: ");
                        rows = checkBlank(Main.rows);
                        System.out.println(Main.rows + " " + rows);
                        System.out.print("---> Columns <---\nLeave blank to keep current value [" + Main.columns + "]\nOr enter new value: ");
                        columns = checkBlank(Main.columns);
                        System.out.print("---> Enemies <---\nLeave blank to keep current value [" + Main.amountOfEnemies + "]\nOr enter new value: ");
                        amountOfEnemies = checkBlank(Main.amountOfEnemies);
                        System.out.print("---> Transistors <---\nLeave blank to keep current value [" + Main.transistorsNeeded + "]\nOr enter new value: ");
                        transistorsNeeded = checkBlank(Main.transistorsNeeded);
                        System.out.print("---> Moves <---\nLeave blank to keep current value [" + Main.moves + "]\nOr enter new value: ");
                        moves = checkBlank(Main.moves);
                        System.out.print("---> Flowers <---\nLeave blank to keep current value [" + Main.getAmountOfFlowers + "]\nOr enter new value: ");
                        getAmountOfFlowers = checkBlank(Main.getAmountOfFlowers);
                        checkSettings();
                        System.out.println(check);
                        if (check) {
                            setGameValues();
                            System.out.println("----------------| New settings |----------------");
                            showSettings();
                        } else {
                            System.out.println("\n\n++++++++| Your values is incorrect for start the game! Please redefine values! |++++++++\n");
                        }
                    } while (!check);
                    break;
                case 3:
                    setDefaultGameValues();
                    System.out.println("--------------| Default settings |--------------");
                    showSettings();
                case 4:
                    break;
                default:
                    System.out.println("\n\n\n\n\n-----| Command not recognized! Please try again |-----\n\n\n\n\n");
            }
        } while (cmd != 4);
    }

    //this method checks the player's settings
    //if sum of elements more that size of field - player needs to redefine settings
    private static boolean checkSettings() {
        int fieldValue = rows * columns;
        int amountOfObjects = amountOfEnemies + getAmountOfFlowers + 1;
        float checker = (float) amountOfObjects / fieldValue;
        if ((checker >= 0.3) && (checker <= 0.7)) {
            System.out.println(checker);
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    private static void showSettings() {
        System.out.println("Rows: " + Main.rows +
                "\nColumns: " + Main.columns +
                "\nEnemies: " + Main.amountOfEnemies +
                "\nTransistors: " + Main.transistorsNeeded +
                "\nMoves: " + Main.moves +
                "\nPoint of spawn: " + Main.getAmountOfFlowers);
        System.out.println("------------------------------------------------\n");
    }

    private static int checkBlank(int option) {
        String value = scan.nextLine();
        if (!value.isBlank()) {
            option = Integer.parseInt(value);
            System.out.print("\n---> New value [" + option + "] <---\n\n");
        } else System.out.print("\n---> You keep current value [" + option + "] <---\n\n");
        return option;
    }

    static void setGameValues() {
        Main.rows = rows;
        Main.columns = columns;
        Main.amountOfEnemies = amountOfEnemies;
        Main.transistorsNeeded = transistorsNeeded;
        Main.moves = moves;
        Main.getAmountOfFlowers = getAmountOfFlowers;
    }

    static void setDefaultGameValues() {
        Main.rows = defaultRows;
        Main.columns = defaultColumns;
        Main.amountOfEnemies = defaultEnemies;
        Main.transistorsNeeded = defaultTransistors;
        Main.moves = defaultMoves;
        Main.getAmountOfFlowers = defaultFlowers;
    }

}

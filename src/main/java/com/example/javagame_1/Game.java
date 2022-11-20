package com.example.javagame_1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int rows;
    private int columns;
    private int amountOfEnemies;
    private int transistorsNeeded;
    private int turnsLeft;
    private int transistorsGathered;
    private Field field;
    private boolean isGameFinished = false;
    private int amountOfFlowers;
    private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
    private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
    private Random randomNumber = new Random();
    private Player player;
    private Scanner scan = new Scanner(System.in);
    private Boolean isIncorrectCmd = true;

    public Game(int rows, int columns, int amountOfEnemies, int transistorsNeeded, int turnsLeft, int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorsNeeded = transistorsNeeded;
        this.turnsLeft = turnsLeft;
        this.amountOfFlowers = amountOfFlowers;
        field = new Field(rows, columns);
    }

    public void fillTheFieldIfEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field.setFieldable(i, j, new Empty());
            }
        }
    }

    public void startGame() {
        possesPlayer();
        possesFlowers();
        possesEnemies();
        while (!isGameFinished) {
            showField();
            playerTurn();
            if (isIncorrectCmd) {
                System.out.println("\n\t+++++++++++++++++++++++++++++++++\n\t| You tried to go to wrong way! |\n\t+++++++++++++++++++++++++++++++++");
                continue;
            }
            computerTurn();
            checkIfGameNotFinished();
        }
    }

    private void possesEnemies() {
        generateEnemies();
    }

    private void generateEnemies() {
        for (int i = amountOfEnemies - enemyArrayList.size(); i > 0; ) {
            int enemyRowPosition = randomNumber.nextInt(rows);
            int enemyColumnPosition = randomNumber.nextInt(columns);
            if (field.getFieldable(enemyRowPosition, enemyColumnPosition) instanceof Empty) {
                Enemy enemy = new Enemy(enemyRowPosition, enemyColumnPosition);
                field.setFieldable(enemyRowPosition, enemyColumnPosition, enemy);
                enemyArrayList.add(enemy);
                i--;
            }
        }
    }

    private void possesFlowers() {
        generateFlowers();

    }

    private void generateFlowers() {
        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {
            int flowerAmountOfTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);
            if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Empty) {
                Flower flower = new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;
            }
        }
    }

    private void possesPlayer() {
        int playerRowPosition = randomNumber.nextInt(rows);
        int playerColumnPosition = randomNumber.nextInt(columns);
        player = new Player(playerRowPosition, playerColumnPosition, this);
    }

    private void showField() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Turns left: " + turnsLeft + ", transistors gathered: " + transistorsGathered + "/" + transistorsNeeded);
        field.showField();
    }

    private void playerTurn() {
        System.out.print("Please enter your command and press Enter: ");
        String cmd = scan.nextLine();
        isIncorrectCmd = player.makeMove(cmd);
        this.generateFlowers();
    }

    private void computerTurn() {
        enemyMove();
        generateFlowers();
        turnsLeft--;
    }

    private void enemyMove() {
        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate = true;

        for (Enemy enemy : enemyArrayList) {
            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();
            do {
                int deltaRow = randomNumber.nextInt(3) - 1;
                int deltaColumn = randomNumber.nextInt(3) - 1;
                newRowIndex = rowIndex + deltaRow;
                newColumnIndex = columnIndex + deltaColumn;
                if ((newRowIndex < 0) || (newColumnIndex < 0) || (newRowIndex >= field.getRowIndex())
                        || (newColumnIndex >= field.getColumnIndex()) || (field.getFieldable(newRowIndex, newColumnIndex) instanceof Player)
                        || (field.getFieldable(newRowIndex, newColumnIndex) instanceof Enemy)) {
                    regenerateIndex++;
                    isNeededToRegenerate = true;
                } else {
                    if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Flower) {
                        Flower flower = (Flower) field.getFieldable(newRowIndex, newColumnIndex);
                        flowerArrayList.remove(flower);
                        isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                    }
                }
            }
            while (isNeededToRegenerate && regenerateIndex <= 10);
        }
    }

    private boolean swapEnemy(int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy) {
        field.setFieldable(newRowIndex, newColumnIndex, enemy);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);
        return false;
    }

    private void checkIfGameNotFinished() {
        if (turnsLeft == 0) {
            System.out.println("\nNo more turns left!\n\n*************\n* YOU LOST! *\n*************\n");
            isGameFinished = true;
        } else if (transistorsGathered >= transistorsNeeded) {
            System.out.println("\nYou have gathered the required " + transistorsGathered
                    + " number of transistors\n\n************\n* YOU WON! *\n************\n");
            isGameFinished = true;
        }
    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return this.flowerArrayList;
    }

    public void setTransistorsNeeded(int transistorsToAdd) {
        this.transistorsGathered += transistorsToAdd;

    }
}

package com.example.javagame_1;

public class Player implements Fieldable {
    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "s";
    private static final String NO_MOVE = "x";
    private int rowIndex;
    private int columnIndex;
    private Game game;
    private Field field;

    @Override
    public String getSymbol() {
        return " @ ";
    }

    public Player(int rowIndex, int columnIndex, Game game) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setFieldable(rowIndex, columnIndex, this);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Boolean makeMove(String cmd) {
        Boolean isIncorrectMove = true;
        switch (cmd) {
            case MOVE_LEFT:
                isIncorrectMove = movePlayer(0, -1);
                break;
            case MOVE_RIGHT:
                isIncorrectMove = movePlayer(0, 1);
                break;
            case MOVE_UP:
                isIncorrectMove = movePlayer(-1, 0);
                break;
            case MOVE_DOWN:
                isIncorrectMove = movePlayer(1, 0);
                break;
            case NO_MOVE:
                System.out.println("\n\t++++++++++++++++++++++\n\t| You skip the move! |\n\t++++++++++++++++++++++");
                isIncorrectMove = false;
                break;
            default:
                System.out.println("\n\t++++++++++WARNING!!!++++++++++\n\t| " + cmd.toUpperCase() + " is a bad key! Try again! |\n\t++++++++++++++++++++++++++++++");
                break;
        }
        return isIncorrectMove;
    }

    public Boolean movePlayer(int deltaRowIndex, int deltaColumnIndex) {
        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        if ((newRowIndex >= 0) && (newRowIndex < field.getRowIndex())
                && (newColumnIndex >= 0) && (newColumnIndex < field.getColumnIndex())
                && !((field.getFieldable(newRowIndex, newColumnIndex)) instanceof Enemy)) {
            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Flower) {
                Flower flower = (Flower) field.getFieldable(newRowIndex, newColumnIndex);
                game.setTransistorsNeeded(flower.getTransistors());
                game.getFlowerArrayList().remove(flower);
                swapPlayer(newRowIndex, newColumnIndex);
            }
            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Empty) {
                swapPlayer(newRowIndex, newColumnIndex);
            }
            return false;
        } else {
            return true;
        }
    }

    public void swapPlayer(int newRowIndex, int newColumnIndex) {
        field.setFieldable(newRowIndex, newColumnIndex, this);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }
}

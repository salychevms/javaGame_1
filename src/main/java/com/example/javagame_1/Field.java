package com.example.javagame_1;

public class Field {
    private int rowIndex;
    private int columnIndex;
    private Fieldable[][] field;

    public Field(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        field = new Fieldable[rowIndex][columnIndex];
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setFieldable(int x, int y, Fieldable object) {
        field[x][y] = object;
    }

    public Fieldable getFieldable(int x, int y) {
        return field[x][y];
    }

    public void showField() {
        for (int i = 0; i < rowIndex; i++) {
            System.out.println();
            for (int j = 0; j < columnIndex; j++) {
                System.out.print(field[i][j].getSymbol());
            }
        }
        System.out.println("\n");
    }
}

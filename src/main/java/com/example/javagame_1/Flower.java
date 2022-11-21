package com.example.javagame_1;

import java.util.Objects;

public class Flower implements Fieldable {
    private int transistors;
    private int rowIndex;
    private int columnIndex;

    public Flower(int transistors, int rowIndex, int columnIndex) {
        this.transistors = transistors;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getTransistors() {
        return transistors;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(" " + transistors + " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return rowIndex == flower.rowIndex && columnIndex == flower.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}

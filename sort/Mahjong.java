package com.bozo.structurealgorithm.sort;

public class Mahjong {
    public int suit;
    public int rank;

    public Mahjong(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "[" + suit + "," + rank + "]";
    }
}

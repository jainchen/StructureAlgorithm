package com.bozo.structurealgorithm.sort;

/**
 * 牌
 */
public class Card implements Comparable<Card> {

    private int cardColors;
    private int cardPoint;

    public Card(int cardColors, int cardPoint) {
        this.cardColors = cardColors;
        this.cardPoint = cardPoint;
    }

    /**
     * 提供比较两张牌大小的方法
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Card o) {
        if (this.cardPoint > o.cardPoint) {
            return 1;
        } else if (this.cardPoint < o.cardPoint) {
            return -1;
        }
        if (this.cardColors > o.cardColors) {
            return 1;
        } else if (this.cardColors < o.cardColors) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[" + cardColors + "," + cardPoint + "]";
    }
}

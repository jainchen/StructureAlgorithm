package com.bozo.structurealgorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        hanoi(4, 1, 2, 3);
    }

    /**
     * 汉诺塔问题
     * @param n
     * @param start
     * @param middle
     * @param end
     */
    private void hanoi(int n, int start, int middle, int end) {
        if (n <= 1) {
            System.out.println(start + "-------------->" + end);
        } else {
            hanoi(n - 1, start, end, middle);
            System.out.println(start + "-------------->" + end);
            hanoi(n - 1, middle, start, end);
        }
    }
}
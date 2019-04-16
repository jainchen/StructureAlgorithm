package com.bozo.structurealgorithm.sort;

import com.bozo.structurealgorithm.linked.LinkedList;

import org.junit.Test;

/**
 * 排序
 */
public class SortTest {

    @Test
    public void sortTest() {
        Card[] cards = new Card[]{new Card(4, 4),
                new Card(3, 9),
                new Card(2, 7),
                new Card(3, 2),
                new Card(1, 3),
                new Card(2, 6),};

        bubbleSort(cards);
//        selectSort(cards);

        for (Card card : cards) {
            System.out.println(card);
        }
    }

    @Test
    public void radixSortTest() {
        LinkedList<Mahjong> mahjongs = new LinkedList<>();
        mahjongs.add(new Mahjong(1, 5));
        mahjongs.add(new Mahjong(3, 2));
        mahjongs.add(new Mahjong(2, 7));
        mahjongs.add(new Mahjong(1, 2));
        mahjongs.add(new Mahjong(3, 1));
        mahjongs.add(new Mahjong(2, 8));
        mahjongs.add(new Mahjong(1, 3));
        mahjongs.add(new Mahjong(2, 1));
        mahjongs.add(new Mahjong(3, 8));
        mahjongs.add(new Mahjong(1, 7));
        mahjongs.add(new Mahjong(2, 6));
        mahjongs.add(new Mahjong(3, 2));
        mahjongs.add(new Mahjong(1, 5));
        mahjongs.add(new Mahjong(3, 4));

        radixSort(mahjongs);

        for (int i = 0; i < mahjongs.getSize(); i++) {
            System.out.println(mahjongs.get(i));
        }
    }

    @Test
    public void selectTest() {
        int[] array = new int[]{1, 5, 8, 12, 19, 23, 28, 34, 37, 45};
        System.out.println(binarySelect(array, 0, array.length, 100));
    }

    @Test
    public void quickSortTest() {
        int[] array = new int[]{1, 5, 8, 12, 19, 23, 28, 34, 37, 45};
        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + "   ");
        }
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort(Card[] cards) {
        for (int j = cards.length - 1; j > 0; j--) {
            boolean changed = false;
            for (int i = 0; i < j; i++) {
                if (cards[i].compareTo(cards[i + 1]) > 0) {
                    Card tmp = cards[i];
                    cards[i] = cards[i + 1];
                    cards[i + 1] = tmp;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param cards
     */
    public void selectSort(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int index = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[index].compareTo(cards[j]) > 0) {
                    index = j;
                }
            }
            if (index != i) {
                Card tmp = cards[i];
                cards[i] = cards[index];
                cards[index] = tmp;
            }
        }
    }

    /**
     * 基数排序
     */
    public void radixSort(LinkedList<Mahjong> list) {
        //声明点数分组的数组
        LinkedList[] rankLists = new LinkedList[9];
        for (int i = 0; i < rankLists.length; i++) {
            rankLists[i] = new LinkedList();
        }
        //将麻将分别装在以点数分组的数组中 1-9
        while (list.getSize() > 0) {
            Mahjong mahjong = list.remove(0);
            rankLists[mahjong.rank - 1].add(mahjong);
        }
        //将各个数组连接到一起
        for (LinkedList rankList : rankLists) {
            list.addAll(rankList);
        }
        //声明花色分组的数组
        LinkedList[] suitLists = new LinkedList[3];
        for (int i = 0; i < suitLists.length; i++) {
            suitLists[i] = new LinkedList();
        }
        //将麻将分别装在以花色分组的数组中
        while (list.getSize() > 0) {
            Mahjong mahjong = list.remove(0);
            suitLists[mahjong.suit - 1].add(mahjong);
        }
        //将麻将装回原来数组
        for (LinkedList suitList : suitLists) {
            list.addAll(suitList);
        }
    }

    /**
     * 二分查找
     *
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    public int binarySelect(int[] array, int fromIndex, int toIndex, int key) {
        int start = fromIndex;
        int end = toIndex - 1;
        int middle = 0;
        while (start <= end) {
            middle = (start + end) >> 1;
            if (key > array[middle]) {
                start = middle + 1;
            } else if (key < array[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        return -(middle + 1);
    }

    /**
     * 快速排序
     *
     * @param array
     * @param begin
     * @param end
     */
    public void quickSort(int[] array, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int low = begin;
        int high = end;
        //取第一个数据作为基数
        int key = array[0];
        //从右边开始比较为true, 左边为false
        boolean direction = true;
        L:
        while (low < high) {
            if (direction) {
                //循环从高指针位置开始往左边边拿数据出来比较
                for (int i = high; i > low; i--) {
                    //若比基数小则将该数据放到低指针的位置，并且将高指针位置修改为该数据位置，然后改变查找方向从低指针位置开始比较
                    if (array[i] < key) {
                        array[low] = array[i];
                        low++;
                        high = i;
                        direction = !direction;
                        continue L;
                    }
                }
                low = high;
            } else {
                //循环从低指针位置开始依次往右边拿数据出来比较
                for (int i = low; i < high; i++) {
                    if (array[i] > key) {
                        array[high] = array[i];
                        high--;
                        low = i;
                        direction = !direction;
                        continue L;
                    }
                }
                low = high;
            }
        }
        //基数左边数据排序
        quickSort(array, begin, low - 1);
        //基数右边数据排序
        quickSort(array, low + 1, end);
    }

    @Test
    public void testMargeSort() {
        int[] array = new int[]{7, 3, 8, 2, 4, 1, 6, 5};
        margeSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i);
        }
    }

    /**
     * 归并排序
     *
     * @param array
     * @param left
     * @param right
     */
    public void margeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = (left + right) / 2;
        //排序左边数据
        margeSort(array, left, middle);
        //排序右边数据
        margeSort(array, middle + 1, right);
        //将数组进行归并
        marge(array, left, middle + 1, right);
    }

    /**
     * 归并
     *
     * @param array
     * @param left
     * @param middle
     * @param right
     */
    public void marge(int[] array, int left, int middle, int right) {
        //声明两个数组,将数据以middle为分割点分别装进数组
        int[] leftArray = new int[middle - left];
        int[] rightArray = new int[right - middle + 1];
        for (int i = left; i < middle; i++) {
            leftArray[i - left] = array[i];
        }
        for (int i = middle; i <= right; i++) {
            rightArray[i - middle] = array[i];
        }
        //将两个数组进行归并
        int i = 0; //记录左边数组提取数据的指针
        int j = 0; //记录右边数组提取数据的指针
        int k = left; //记录归并数据填充的指针
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] < rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        //将左边剩余数据填充到数组
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }
        //将右边剩余数据填充到数组
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
}

package com.bozo.structurealgorithm.linked;

import org.junit.Test;

public class LinkedTest {

    @Test
    public void testLinkedList(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0, 0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(2, 6);

        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(10);
        linkedList2.add(20);
        linkedList2.add(30);
        linkedList.addAll(linkedList2);

        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
//        System.out.println();
//        linkedList.remove(3);
//        for (int i = 0; i < linkedList.getSize(); i++) {
//            System.out.print(linkedList.get(i));
//        }
    }
}

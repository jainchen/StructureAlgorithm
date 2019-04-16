package com.bozo.structurealgorithm.linked;

/**
 * 节点，用于存放LinkedList中的每个数据
 */
public class Node<T> {

    //数据
    T data;
    //前驱
    Node<T> prev;
    //后继
    Node<T> next;

    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

package com.bozo.structurealgorithm.linked;

/**
 * 自定义LinkedList
 */
public class LinkedList<E> {

    //记录第一条数据
    private Node<E> first;
    //记录最后一条数据
    private Node<E> last;
    //记录数据条数
    private int size;

    public LinkedList() {
    }

    /**
     * 添加一条数据
     *
     * @param data
     */
    public void add(E data) {
        linkLast(data);
    }

    /**
     * 在指定索引位置添加数据
     *
     * @param index
     * @param data
     */
    public void add(int index, E data) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == size - 1) {
            linkLast(data);
        } else {
            //找到要添加数据的位置的节点
            Node<E> node = getNode(index);
            Node<E> newNode = new Node<>(data, node == null ? null : node.prev, node);
            if (newNode.prev != null) {
                newNode.prev.next = newNode;
            } else {
                first = newNode;
            }
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            } else {
                last = newNode;
            }
            size++;
        }
    }

    /**
     * 添加一个集合
     * @param list
     */
    public void addAll(LinkedList<E> list) {
        if (list == null || list.getSize() == 0) {
            return;
        }
        if (last != null) {
            last.next = list.first;
        } else {
            first = list.first;
        }
        list.first.prev = last;
        last = list.last;
        size += list.size;
    }

    /**
     * 获取对应索引位置的数据
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getNode(index).data;
    }

    /**
     * 移除指定位置数据
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> node = getNode(index);
        //断开链表链接
        unlink(node);
        return node.data;
    }

    /**
     * 获取数据数量
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 添加在最后面
     *
     * @param data
     */
    private void linkLast(E data) {
        Node<E> newNode = new Node<>(data, last, null);
        Node<E> tLast = last;
        last = newNode;
        if (newNode.prev == null) {
            first = newNode;
        } else {
            tLast.next = newNode;
        }
        size++;
    }

    /**
     * 获取相应位置的节点
     *
     * @param index
     * @return
     */
    private Node<E> getNode(int index) {
        Node<E> node;
        //如果index在整个链表的前半部分
        if (index < size >> 1) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * 断开指定节点连接
     *
     * @param node
     */
    private void unlink(Node<E> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }
        size--;
    }
}
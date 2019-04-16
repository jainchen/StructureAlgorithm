package com.bozo.structurealgorithm.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {

    @Test
    public void testSearchBinaryTree() {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(7);
        list.add(3);
        list.add(1);
        list.add(9);
        list.add(11);
        list.add(8);
        list.add(7);
        list.add(3);
        list.add(2);
        SearchBinaryTree<Integer> searchBinaryTree = new SearchBinaryTree<>();
        searchBinaryTree.createTree(list);
        searchBinaryTree.showTree(searchBinaryTree.root);

        System.out.println();
        SearchBinaryTree.TreeNode treeNode = searchBinaryTree.searchNode(4);
        System.out.println(treeNode == null ? "没找到" : treeNode.data);

        searchBinaryTree.delete(6);
        searchBinaryTree.showTree(searchBinaryTree.root);
        System.out.println();
        searchBinaryTree.put(10);
        searchBinaryTree.showTree(searchBinaryTree.root);
    }


    private void bubble(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            boolean changed = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }

    private void select(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            if (index != i) {
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }
    }
}

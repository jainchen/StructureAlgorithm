package com.bozo.structurealgorithm.sort;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 二叉排序树
 */
public class SearchBinaryTree<E extends Comparable> {

    public TreeNode<E> root;

    /**
     * 创建一棵树
     *
     * @param list
     */
    public void createTree(List<E> list) {
        if (list != null) {
            for (E e : list) {
                put(e);
            }
        }
    }

    /**
     * 添加一个节点
     *
     * @param data
     * @return
     */
    public TreeNode<E> put(E data) {
        if (data == null) {
            return null;
        }
        if (root == null) {
            root = new TreeNode<>(data);
            return root;
        } else {
            TreeNode<E> node = root;
            TreeNode<E> parent = null;
            //查找到新数据要接入的父节点
            while (node != null) {
                parent = node;
                if (data.compareTo(node.data) > 0) {
                    node = node.rightChild;
                } else if (data.compareTo(node.data) < 0) {
                    node = node.leftChild;
                } else { //若已经存在则直接返回
                    return node;
                }
            }
            //将新节点添加到二叉树
            TreeNode<E> newNode = new TreeNode<>(data);
            newNode.parent = parent;
            if (data.compareTo(parent.data) > 0) {
                parent.rightChild = newNode;
            } else {
                parent.leftChild = newNode;
            }
            return newNode;
        }
    }

    /**
     * 查找数据在树中的节点
     *
     * @param data
     */
    public TreeNode<E> searchNode(E data) {
        if (root == null) {
            return null;
        }
        TreeNode<E> node = root;
        while (node != null) {
            if (data.compareTo(node.data) > 0) {
                node = node.rightChild;
            } else if (data.compareTo(node.data) < 0) {
                node = node.leftChild;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 删除一个节点
     *
     * @param data
     * @return
     */
    public void delete(E data) {
        if (root == null) {
            return;
        }
        //查找到要删除的节点
        TreeNode<E> delNode = searchNode(data);
        if (delNode == null) {
            throw new NoSuchElementException("没找到相应数据");
        }
        TreeNode<E> parent = delNode.parent;
        //根据不同情况来删除节点
        if (delNode.leftChild == null && delNode.rightChild == null) { //删除的节点没有子节点
            //跟据两种情况来删除
            if (delNode == root) { //删除的节点是根节点
                root = null;
            } else {
                if (parent.leftChild == delNode) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                delNode.parent = null;
            }
        } else if (delNode.leftChild != null && delNode.rightChild == null) { //删除的节点只有左子节点
            if (parent == null) {
                root = delNode.leftChild;
                delNode.leftChild.parent = null;
                delNode.leftChild = null;
            } else {
                if (parent.leftChild == delNode) {
                    parent.leftChild = delNode.leftChild;
                } else {
                    parent.rightChild = delNode.leftChild;
                }
                delNode.leftChild.parent = parent;
                delNode.parent = null;
            }
        } else if (delNode.leftChild == null && delNode.rightChild != null) {//删除的节点只有右子节点
            if (parent == null) {
                root = delNode.rightChild;
                delNode.rightChild.parent = null;
                delNode.rightChild = null;
            } else {
                if (parent.leftChild == delNode) {
                    parent.leftChild = delNode.rightChild;
                } else {
                    parent.rightChild = delNode.rightChild;
                }
                delNode.rightChild.parent = parent;
                delNode.parent = null;
            }
        } else { //删除的节点左右子节点都有
            if (delNode.rightChild.leftChild == null) {//删除的节点的右子节点没有左子节点,直接将右子节点放到删除的节点
                delNode.rightChild.leftChild = delNode.leftChild;
                delNode.leftChild.parent = delNode.rightChild;
                if (parent == null) { //删除的节点是根节点
                    root = delNode.rightChild;
                } else {
                    delNode.rightChild.parent = parent;
                    if (parent.leftChild == delNode) {
                        parent.leftChild = delNode.rightChild;
                    } else {
                        parent.rightChild = delNode.rightChild;
                    }
                }
                delNode.leftChild = null;
                delNode.rightChild = null;
                delNode.parent = null;
            } else {
                //找到删除节点左子树中最小的左子节点
                TreeNode<E> leftMindNode = findLeftMinNode(delNode);
                //1.将最小左子节点的右子节点接到最小左节点的父节点
                if (leftMindNode.rightChild != null) {
                    leftMindNode.parent.leftChild = leftMindNode.rightChild;
                    leftMindNode.rightChild.parent = leftMindNode.parent;
                }
                //2.将删除节点的左右子节点接到最小左节点
                leftMindNode.leftChild = delNode.leftChild;
                leftMindNode.rightChild = delNode.rightChild;
                delNode.leftChild.parent = leftMindNode;
                delNode.rightChild.parent = leftMindNode;
                //3.将新的节点接到删除节点的父节点下
                if (parent == null) {
                    root = leftMindNode;
                    leftMindNode.parent = null;
                } else {
                    if (parent.leftChild == delNode) {
                        parent.leftChild = leftMindNode;
                    } else {
                        parent.rightChild = leftMindNode;
                    }
                    leftMindNode.parent = parent;
                }
                delNode.leftChild = null;
                delNode.rightChild = null;
                delNode.parent = null;
            }
        }
    }

    /**
     * 找到指定节点左子树下最小的左节点
     *
     * @param treeNode
     * @return
     */
    private TreeNode<E> findLeftMinNode(TreeNode<E> treeNode) {
        if (treeNode == null || treeNode.leftChild == null) {
            return treeNode;
        }
        TreeNode<E> node = treeNode.leftChild;
        while (true) {
            node = node.leftChild;
            if (node.leftChild == null) {
                return node;
            }
        }
    }

    /**
     * 使用中序遍历打印数据
     *
     * @param node
     */
    public void showTree(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        showTree(node.leftChild);
        System.out.print(node.data + " ");
        showTree(node.rightChild);
    }

    public class TreeNode<E extends Comparable> {

        E data;
        public TreeNode<E> parent;
        public TreeNode<E> leftChild;
        public TreeNode<E> rightChild;

        public TreeNode(E data) {
            this.data = data;
            parent = null;
            leftChild = null;
            rightChild = null;
        }
    }
}

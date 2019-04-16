package com.bozo.structurealgorithm.sort;

/**
 * 二叉平衡树
 */
public class AVLBTree<E> {

    public TreeNode<E> root;

    public static final int LH = 1;
    public static final int RH = -1;
    public static final int EH = 0;

    /**
     * 左旋转
     *
     * @param node
     */
    public void leftRotate(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        //记录父节点
        TreeNode<E> parent = node.parent;
        //1.将旋转的节点的右子节点作为旋转节点的父节点
        TreeNode<E> rightChild = node.rightChild;
        node.parent = rightChild;
        //2.将旋转节点的左孩子作为旋转节点的右子节点
        node.rightChild = rightChild.leftChild;
        if (rightChild.leftChild != null) {
            rightChild.leftChild.parent = node;
        }
        //3.将旋转节点作为旋转节点右子节点的左子节点
        rightChild.leftChild = node;
        //4.将旋转节点的父节点作为旋转节点右子节点的父节点
        if (parent == null) {
            root = rightChild;
        } else {
            if (parent.leftChild == node) {
                parent.leftChild = rightChild;
            } else {
                parent.rightChild = rightChild;
            }
        }
        rightChild.parent = parent;
    }

    /**
     * 右旋转
     *
     * @param node
     */
    public void rightRotate(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        TreeNode<E> parent = node.parent;
        //1.将旋转节点的左子节点作为旋转节点的父节点
        TreeNode<E> leftChild = node.leftChild;
        node.parent = leftChild;
        //2.将旋转节点左子节点的右子节点作为旋转节点的左子节点
        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null) {
            leftChild.rightChild.parent = node;
        }
        //3.将旋转节点作为左子节点的右子节点
        leftChild.rightChild = node;
        //3.将旋转节点的父节点作为旋转节点左子节点的父节点
        if (parent == null) {
            root = leftChild;
        } else {
            if (parent.leftChild == node) {
                parent.leftChild = leftChild;
            } else {
                parent.rightChild = leftChild;
            }
        }
        leftChild.parent = parent;
    }

    public void rightBalance(TreeNode<E> node) {
        TreeNode<E> rightChild = node.rightChild;
        switch (rightChild.balance) {
            case RH:
                leftRotate(rightChild);
                node.balance = EH;
                rightChild.balance = EH;
                break;
            case LH:
                TreeNode<E> rlChild = rightChild.leftChild;
                switch (rlChild.balance){
                    case LH:
                        node.balance = EH;
                        rightChild.balance = RH;
                        rlChild.balance = EH;
                        break;
                    case RH:
                        node.balance = LH;
                        rightChild.balance = EH;
                        rlChild.balance = EH;
                        break;
                    case EH:
                        node.balance = EH;
                        rightChild.balance = EH;
                        rlChild.balance = EH;
                        break;
                }
                rightRotate(rightChild);
                leftRotate(node);
                break;
        }
    }

    public class TreeNode<E> {

        E data;
        int balance;  //平衡因子
        TreeNode<E> leftChild;
        TreeNode<E> rightChild;
        TreeNode<E> parent;

        public TreeNode(E data) {
            this.data = data;
        }
    }
}

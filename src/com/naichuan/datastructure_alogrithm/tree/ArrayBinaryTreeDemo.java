package com.naichuan.datastructure_alogrithm.tree;

/**
 * @author Naichuan Zhang
 * 02-Aug-2020
 **/
public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
    }
}

// 以数组的方式实现顺序存储二叉树的遍历
class ArrayBinaryTree {
    private int[] arr; // 存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 顺序存储二叉树的前序遍历，index为数组下标
    public void preOrder(int index) {
        // 如果数组为空，或者arr.length为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}

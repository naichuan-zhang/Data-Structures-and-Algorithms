package com.naichuan.datastructure_alogrithm.tree;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 02-Aug-2020
 **/
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

        heapSort(arr);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    // 编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp;
        // 构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    // 将一个数组（二叉树），调整为一个大顶堆
    // i为非叶子节点在数组中的索引
    // length为对多少个元素进行调整，length是在逐渐减少
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  // 先取出当前元素的值，保存在一个临时变量
        // 开始调整，找到i对应的左子节点（i*2+1）
        // 1. k指向i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if ((k + 1) < length && arr[k] < arr[k + 1]) {
                // 说明左子节点的值小于右子节点的值
                k++;    // k指向右子节点
            }
            if (arr[k] > temp) {
                // 如果子节点大于父节点的值
                arr[i] = arr[k];    // 把较大的值赋给当前的节点
                i = k;  // 让i指向k，继续循环比较
            } else {
                break;
            }
        }
        // for循环结束后，我们已将以i为父节点的树的最大值放在了最顶上（局部）
        arr[i] = temp;  // 将temp赋值放到调整后的位置
    }
}

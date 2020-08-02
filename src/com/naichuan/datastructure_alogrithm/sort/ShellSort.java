package com.naichuan.datastructure_alogrithm.sort;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

//        shellSort(arr);
        shellSort2(arr);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    // 希尔排序交换法 -> 效率较低
    public static void shellSort(int[] arr) {
        int temp;
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 因为第一轮排序是将10个元素分为了两组
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前的元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 希尔排序优化 -> 移动法
    public static void shellSort2(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 从第gap个元素开始，逐个对其所在的组直接插入排序
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}

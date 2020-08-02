package com.naichuan.datastructure_alogrithm.sort;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class InsertionSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

        insertionSort(arr);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];   // 要排序的数
            int index = i - 1;
            while (index >= 0 && arr[index] > key) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = key;
        }
    }
}

package com.naichuan.datastructure_alogrithm.sort;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 31-Jul-2020
 **/
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

        selectionSort(arr);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    public static void selectionSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;       // 初始化最小值的坐标为 i
            // 找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            // 将找到的最小值与arr[i]交换
            if (i != min) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}

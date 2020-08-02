package com.naichuan.datastructure_alogrithm.search;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class InsertValueSearch {

    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, arr.length - 1, 75);
        System.out.println("Index: " + index);
    }

    // 插值查找算法（前提为数组有序）
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + ((value - arr[left]) / (arr[right] - arr[left])) * (right - left);
        int midValue = arr[mid];
        if (value > midValue) {
            // 向右查找
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            // 向左查找
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}

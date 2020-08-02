package com.naichuan.datastructure_alogrithm.search;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class LinearSearch {

    public static void main(String[] args) {

        int[] arr = {1,9,11,-1,34,89};
        int index = linearSearch(arr, 11);
        if (index == -1) {
            System.out.println("未找到！！！");
        } else {
            System.out.println("Index: " + index);
        }
    }

    // 线性查找是逐一比对，发现有相同的值，就返回下标
    public static int linearSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

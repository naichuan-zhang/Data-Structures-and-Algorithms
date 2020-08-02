package com.naichuan.datastructure_alogrithm.sort;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

        quickSort(arr, 0, arr.length - 1);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int index = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= index) {
                j--;
            }
            while (i < j && arr[i] <= index) {
                i++;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[low];
        arr[low] = temp;
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }

//    public static void quickSort(int[] arr, int left, int right) {
//        int l = left;   // 左下标
//        int r = right;  // 右下标
//        int pivot = arr[(left + right) / 2];    // 中轴值
//        int temp;
//        // 循环的目的是让比pivot值小的放到左边，大的放到右边
//        while (l < r) {
//            // 在pivot的左边一直找，找到大于等于pivot的值才退出
//            while (arr[l] < pivot) {
//                l++;
//            }
//            // 在pivot的右边一直找，找到小于等于pivot的值才退出
//            while (arr[r] > pivot) {
//                r--;
//            }
//            // 如果l>=r成立，说明pivot左右两边的值已经按照顺序排好
//            if (l >= r) {
//                break;
//            }
//            // 交换
//            temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//            // 如果交换完发现arr[l]==pivot值相等 --，前移
//            if (arr[l] == pivot) {
//                r--;
//            }
//            if (arr[r] == pivot) {
//                l++;
//            }
//        }
//        if (l == r) {
//            l++;
//            r--;
//        }
//        // 向左递归
//        if (left < r) {
//            quickSort(arr, left, r);
//        }
//        // 向右递归
//        if (right > l) {
//            quickSort(arr, l, right);
//        }
//    }
}

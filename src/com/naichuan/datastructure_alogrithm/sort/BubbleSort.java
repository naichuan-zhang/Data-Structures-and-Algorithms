package com.naichuan.datastructure_alogrithm.sort;

import java.util.Arrays;

/**
 * @author Naichuan Zhang
 * 31-Jul-2020
 **/
public class BubbleSort {

    public static void main(String[] args) {

//        int[] arr = {3, 9, -1 ,10, 2};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);     // [0, 80000)
        }

        long before = System.currentTimeMillis();

        bubbleSort(arr);

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println("所用时间为：" + (after - before));
    }

    public static void bubbleSort(int[] arr) {
        int temp;
        boolean flag = false;       // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                // 在一次排序中一次交换都没有发生过，说明已经完成了排序
                break;
            } else {
                // 重置flag，进行下次判断
                flag = false;
            }
        }
    }
}

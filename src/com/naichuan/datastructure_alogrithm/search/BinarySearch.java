package com.naichuan.datastructure_alogrithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1,8,10,20,45,50,80,80,80,99,99};
        List<Integer> list = binarySearch(arr, 0, arr.length - 1, 80);
        System.out.println(list.toString());
    }

    // 二分查找，如果找到返回下标，如果未找到，返回-1
    public static List<Integer> binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<>();
        }
//        int mid = (left + right) / 2;
        // 插值查找算法对mid的优化公式！！！
        int mid = left + ((value - arr[left]) / (arr[right] - arr[left])) * (right - left);
        int midValue = arr[mid];
        if (value > midValue) {
            // 向右查找
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            // 向左查找
            return binarySearch(arr, left, mid - 1, value);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                // 否则，将temp放入list中
                list.add(temp);
                temp--;
            }
            list.add(mid);
            // 向mid的右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}

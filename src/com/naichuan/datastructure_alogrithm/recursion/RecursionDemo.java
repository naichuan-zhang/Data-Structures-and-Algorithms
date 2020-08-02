package com.naichuan.datastructure_alogrithm.recursion;

/**
 * @author Naichuan Zhang
 * 31-Jul-2020
 **/
public class RecursionDemo {

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}

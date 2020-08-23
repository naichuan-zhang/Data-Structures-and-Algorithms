package com.naichuan.datastructure_alogrithm.fenzhi;

/**
 * @author Naichuan Zhang
 * 23-Aug-2020
 **/
public class HanoiTower {

    public static void main(String[] args) {
        hanoi(5, 'A', 'B', 'C');
    }

    // 分治算法（将大问题分解为多个小问题来解决）
    public static void hanoi(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            // 如果n>=2，我们可以看做成两个盘
            // 1. 先把最上面的所有盘A->B,移动过程会用到C
            hanoi(num - 1, a, c, b);
            // 2. 把最下面的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 3. 把B所有盘从B->C
            hanoi(num - 1, b, a, c);
        }
    }
}

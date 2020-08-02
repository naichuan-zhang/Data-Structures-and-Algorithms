package com.naichuan.datastructure_alogrithm.recursion;

/**
 * @author Naichuan Zhang
 * 31-Jul-2020
 **/

/**
 * 八皇后问题 - 递归
 */
public class EightQueenofDeathProblem {

    // 表示皇后的个数
    int max = 8;
    // 数组保存皇后放置位置的结果 {0, 4, 7, 5, 2, 6, 1, 3}
    int[] arr = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        EightQueenofDeathProblem queen = new EightQueenofDeathProblem();
        queen.check(0);
        System.out.println("总共解法次数为：" + count);
    }

    // 放置第n个皇后
    private void check(int n) {
        if (n == max) {  // n = 8时，前面的8个皇后已经放好
            print();
            return;
        }
        // 一次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n，放到该行的第i列
            arr[n] = i;
            // 判断当放置第i个皇后到i列时，是否冲突
            if (judge(n)) {
                // 不冲突时，放第n+1个皇后
                check(n + 1);
            }
            // 如果冲突时，就继续执行arr[n] = i；即将第n个皇后放在本行的后移的一个位置
        }
    }

    // 查看当我们放置第n个皇后时，检测该皇后是否和前面已经摆好的n-1个皇后冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                // 在同一列或同一斜线
                return false;
            }
        }
        return true;
    }

    // 写一个方法，将皇后的位置输出
    private void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        count++;
    }
}

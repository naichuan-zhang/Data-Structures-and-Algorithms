package com.naichuan.datastructure_alogrithm.recursion;

/**
 * @author Naichuan Zhang
 * 31-Jul-2020
 **/

/**
 * 递归解决小球找路的问题
 */
public class MazeProblem {

    public static void main(String[] args) {

        int[][] maze = new int[8][7];
        // 使用1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[2][2] = 1;
        System.out.println("迷宫为：");
        for (int[] row : maze) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        setWay2(maze, 1, 1);
        System.out.println("找到路后的迷宫为：");
        for (int[] row : maze) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }

    // 使用递归给小球找路
    // i，j：从哪个位置开始找（起始点），终点为（6，5）
    // 找到通路返回true，未找到返回false
    // 当为0，该点没走；1，墙不能走；2表示通路可以走；3表示该位置已经走过但是走不通
    // 策略：下右上左
    public static boolean setWay(int[][] maze, int i, int j) {
        // 已经到达终点
        if (maze[6][5] == 2) {
            return true;
        }
        if (maze[i][j] == 0) {
            // 该点还没有走过
            maze[i][j] = 2;     // 假定该点可以走
            if (setWay(maze, i + 1, j)) {   // 下
                return true;
            } else if (setWay(maze, i, j + 1)) {    // 右
                return true;
            } else if (setWay(maze, i - 1, j)) {    // 上
                return true;
            } else if (setWay(maze, i, j - 1)) {    // 左
                return true;
            } else {    // 都不通！为死路
                maze[i][j] = 3;
                return false;
            }
        } else {        // 值可能为 1，2，3
            return false;
        }
    }

    // 修改策略，上右下左
    public static boolean setWay2(int[][] maze, int i, int j) {
        // 已经到达终点
        if (maze[6][5] == 2) {
            return true;
        }
        if (maze[i][j] == 0) {
            // 该点还没有走过
            maze[i][j] = 2;     // 假定该点可以走
            if (setWay2(maze, i - 1, j)) {   // 上
                return true;
            } else if (setWay2(maze, i, j + 1)) {    // 右
                return true;
            } else if (setWay2(maze, i + 1, j)) {    // 下
                return true;
            } else if (setWay2(maze, i, j - 1)) {    // 左
                return true;
            } else {    // 都不通！为死路
                maze[i][j] = 3;
                return false;
            }
        } else {        // 值可能为 1，2，3
            return false;
        }
    }
}

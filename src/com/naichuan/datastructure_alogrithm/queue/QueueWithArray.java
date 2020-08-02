package com.naichuan.datastructure_alogrithm.queue;

/**
 * @author Naichuan Zhang
 * 29-Jul-2020
 **/

import java.util.Scanner;

/**
 * 用数组模拟队列
 */
public class QueueWithArray {

    public static void main(String[] args) {
        // 创建一个对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.add(value);
                    System.out.printf("输入数据为：%d\n", value);
                    break;
                case 'g':
                    try {
                        int result = arrayQueue.get();
                        System.out.printf("数据为：%d\n", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int result = arrayQueue.peekFront();
                        System.out.printf("数据为：%d\n", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

// 使用数组模拟队列
class ArrayQueue {
    private int maxSize; // 数组的最大容量
    private int front;  // 指向队列头部的指针
    private int rear;   // 指向队列尾部的指针
    private int[] arr;  // 用于存放数据

    // 初始化构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;     // 指向队列头的前一个位置
        rear = -1;      // 指向队列尾的具体的数据（即就是队列的最后一个数据）
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加数据到队列
    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满！！！");
            return;
        }
        rear++;
        arr[rear] = value;
    }

    // 获取队列的数据，出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！！！");
        }
        front++;
        return arr[front];
    }

    // 显示队列的所有数据
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空！！！");
            return;
        }
        for (int data : arr) {
            System.out.printf("%d\t", data);
        }
    }

    // 显示头数据
    public int peekFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！！！");
        }
        return arr[front + 1];
    }
}

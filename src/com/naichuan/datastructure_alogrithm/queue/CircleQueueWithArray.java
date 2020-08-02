package com.naichuan.datastructure_alogrithm.queue;

import java.util.Scanner;

/**
 * @author Naichuan Zhang
 * 29-Jul-2020
 **/
public class CircleQueueWithArray {

    public static void main(String[] args) {

        // 测试数组模拟环形队列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);    // 有效数据个数最大为3
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
                    circleArrayQueue.show();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.add(value);
                    System.out.printf("输入数据为：%d\n", value);
                    break;
                case 'g':
                    try {
                        int result = circleArrayQueue.get();
                        System.out.printf("数据为：%d\n", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int result = circleArrayQueue.peekFront();
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

class CircleArrayQueue {
    private int maxSize; // 数组的最大容量
    private int front;  // 指向队列头部的指针
    private int rear;   // 指向队列尾部的指针
    private int[] arr;  // 用于存放数据

    // 初始化构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;     // 指向队列头的数据
        rear = 0;      // 指向队列尾的最后一个数据的后一个位置
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        // 直接将数据加入
        arr[rear] = value;
        // 将rear后移
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据，出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！！！");
        }
        // front 指向队列的第一个元素
        // 1. 先把front对应的值保存到一个临时变量
        int value = arr[front];
        // 2. 将front后移
        front = (front + 1) % maxSize;
        // 3. 将临时保存的变量返回
        return value;
    }

    // 显示队列的所有数据
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空！！！");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据个数
    public int size() {
        // rear = 1, front = 0, maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头数据
    public int peekFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！！！");
        }
        return arr[front];
    }
}
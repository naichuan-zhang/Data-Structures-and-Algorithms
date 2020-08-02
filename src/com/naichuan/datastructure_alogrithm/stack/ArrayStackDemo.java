package com.naichuan.datastructure_alogrithm.stack;

/**
 * @author Naichuan Zhang
 * 30-Jul-2020
 **/

/**
 * 用数组模拟栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.show();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.show();
    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;    // 数组模拟栈，数据放在该数组中
    private int top = -1;   // 指向栈顶，初始化为-1，表示没有数据

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满！！！");
        }
        // 先top+1，后赋值
        stack[++top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！！！");
        }
        return stack[top--];
    }

    // 返回当前栈顶的值
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！！！");
        }
        return stack[top];
    }

    // 显示栈，遍历时需要从栈顶开始显示数据
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空！！！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级，优先级由程序员确定，数越大，优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是否一个运算符
    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    // 计算方法
    public int calculate(int num1, int num2, int operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}


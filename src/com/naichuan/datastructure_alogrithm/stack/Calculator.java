package com.naichuan.datastructure_alogrithm.stack;

/**
 * @author Naichuan Zhang
 * 30-Jul-2020
 **/

/**
 * 用栈实现计算器
 */
public class Calculator {

    public static void main(String[] args) {
        String expr = "70+2*6-4";
        // 创建两个栈，一个存放数字，一个存放运算符
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack opStack = new ArrayStack(10);
        int index = 0;      // 用于扫描
        int num1;
        int num2;
        int operator;
        int result;
        char ch;  // 每次扫描到的char保存到ch中
        String numStr = "";  // 用于拼接多位数
        while (index < expr.length()) {
            // 依次得到expr的每一个字符
            ch = expr.charAt(index);
            // 如果当前为运算符
            if (opStack.isOperator(ch)) {
                // 判断当前符号栈是否为空
                if (opStack.isEmpty()) {
                    // 为空直接入栈
                    opStack.push(ch);
                } else {
                    // 不为空检查优先级
                    if (opStack.priority(ch) <= opStack.priority(opStack.peek())) {
                        // 当ch运算符的优先级小于或等于opStack中的第一个运算符，把opStack中的运算符拿出
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = opStack.pop();
                        result = numStack.calculate(num1, num2, operator);
                        numStack.push(result);
                        opStack.push(ch);
                    } else {
                        // 当ch运算符的优先级大于opStack的第一个运算符，直接将ch push进opStack
                        opStack.push(ch);
                    }
                }
            } else {
                // 如果当前为数字，直接入numStack
                // 1. 定义一个String变量用于拼接
                numStr += ch;
                // 如果ch是expr的最后一位，直接入栈
                if (expr.length() - 1 == index) {
                    numStack.push(ch - 48);
                } else {
                    // 2. 当处理多位数时，不能当发现一个数字就立即入栈，因为它可能是多位数
                    // 3. 向当前表达式后再看一位，如果是数则继续扫描，如果是符号才入栈
                    if (opStack.isOperator(expr.charAt(index + 1))) {
                        numStack.push(Integer.parseInt(numStr));
                        numStr = "";
                    }
                }
            }
            index++;
        }

        // 当表达式扫描完毕，顺序从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字，该数字为最终结果
            if (opStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = opStack.pop();
            result = numStack.calculate(num1, num2, operator);
            numStack.push(result);
        }

        System.out.println("Result: " + numStack.peek());
    }
}

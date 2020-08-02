package com.naichuan.datastructure_alogrithm.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Naichuan Zhang
 * 30-Jul-2020
 **/
public class PolishNotation {

    public static void main(String[] args) {

        String suffixExpr = "3 4 + 5 * 6 - ";
        // 先将suffixExpr放入ArrayList中
        List<String> rpnList = getListString(suffixExpr);
        System.out.println(rpnList.toString());
        // 将ArrayList传给一个方法，配合“栈”完成计算
        System.out.println(calculate(rpnList));
        String infixExpr = "1+((2+3)*4)-5";     // ==> 1 2 3 + 4 * + 5 -
        List<String> infixList = toInfixExpressionList(infixExpr);
        System.out.println(infixList.toString());
        System.out.println(parseToSuffixExprList(infixList).toString());
    }

    // 将一个逆波兰表达式一次将数据和运算符放入到一个ArrayList中
    public static List<String> getListString(String suffixExpr) {
        // 将expr分割
        String[] s = suffixExpr.split(" ");
        return new ArrayList<>(Arrays.asList(s));
    }

    // 完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {
        // 创建一个栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item : list) {
            if (item.matches("\\d+")) {
                // 当为一个数，直接入栈
                stack.push(item);
            } else {
                // 如果不是数，则pop两个数，进行运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num1 - num2;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！！！");
                }
                // 把result入栈
                stack.push(result + "");
            }
        }
        // 最后留在栈里面的就是结果
        return Integer.parseInt(stack.pop());
    }

    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;      // 指针，用来遍历中缀表达式字符串
        String str;     // 对多位数进行拼接
        char ch;
        do {
            // 如果ch是一个非数字，需要加入到list中
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                list.add("" + ch);
                i++;
            } else {
                str = "";
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    // 将中缀表达式转为后缀表达式
    public static List<String> parseToSuffixExprList(List<String> list) {
        Stack<String> stack1 = new Stack<>();       // 符号栈
        List<String> stack2 = new ArrayList<>();
        for (String item : list) {
            // 如果是一个数，入stack2
            if (item.matches("\\d+")) {
                stack2.add(item);
            } else if (item.equals("(")) {
                // 如果是一个左括号，入stack1
                stack1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，依次弹出栈stack1的符号，将符号加入stack2，直到遇到左括号为止
                while (!stack1.peek().equals("(")) {
                    stack2.add(stack1.pop());
                }
                stack1.pop();   // 将左括号弹出
            } else {
                // 当item的优先级 <= stack1栈顶的运算符优先级，将stack1弹出并加入到stack2
                while (!stack1.isEmpty() && Operator.getPriority(item) <= Operator.getPriority(stack1.peek())) {
                    stack2.add(stack1.pop());
                }
                // 将item入栈
                stack1.push(item);
            }
        }
        // 将stack1中剩余的运算符一次弹出并加入stack2
        while (stack1.size() != 0) {
            stack2.add(stack1.pop());
        }
        return stack2;
    }
}

// 编写一个类，返回一个运算符对应的优先级
class Operator {
    private static int ADD = 1;
    private static int MINUS = 1;
    private static int MULT = 2;
    private static int DIV = 2;

    public static int getPriority(String operator) {
        switch (operator) {
            case "+":
                return ADD;
            case "-":
                return MINUS;
            case "*":
                return MULT;
            case "/":
                return DIV;
            default:
                return -1;
        }
    }
}

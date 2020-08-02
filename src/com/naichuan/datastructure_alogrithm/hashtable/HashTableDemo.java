package com.naichuan.datastructure_alogrithm.hashtable;

/**
 * @author Naichuan Zhang
 * 01-Aug-2020
 **/

import java.util.Scanner;

/**
 * 哈希表管理雇员的系统
 */
public class HashTableDemo {

    // 哈希表可以用来实现缓存 （哈希数组，链表数组，二叉树）
    public static void main(String[] args) {

        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add, show, exit");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("Enter id: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter name: ");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "show":
                    hashTable.show();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

class HashTable {

    private EmpLinkedList[] empLinkedListArray;
    private int size;       // 表示共有多少条员工链表

    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        int empNo = hashFunc(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empNo].add(emp);
    }

    // 遍历hashtable
    public void show() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].show();
        }
    }

    public int hashFunc(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// 表示一条链表
class EmpLinkedList {
    // 头指针，指向第一个Emp，head为有效的
    private Emp head;

    public void add(Emp emp) {
        // 如果为添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是添加第一个Emp，则使用一个辅助指针帮助定位到最后
        Emp curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        // 退出时直接将emp加到最后
        curr.next = emp;
    }

    public void show() {
        if (head == null) {
            // 说明链表为空
            System.out.println("链表为空！！！");
            return;
        }
        System.out.println("当前链表为：");
        Emp curr = head;
        while (true) {
            System.out.printf("Id: %d, Name: %s\t", curr.id, curr.name);
            if (curr.next == null)
                break;
            curr = curr.next;
        }
    }
}

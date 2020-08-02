package com.naichuan.datastructure_alogrithm.linkedlist;

/**
 * @author Naichuan Zhang
 * 30-Jul-2020
 **/
/**
 * 约瑟夫环问题
 */
public class JosephusProblem {

    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoys(25);
        linkedList.show();
        linkedList.countBoy(5, 3, 25);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    // 添加Boy的节点，构建成一个环形的链表
    public void addBoys(int nums) {
        // 至少添加一个Boy
        if (nums < 1) {
            System.out.println("nums的值至少为1！！！");
            return;
        }
        // 辅助指针
        Boy currBoy = null;
        // 使用一个for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建Boy节点
            Boy boy = new Boy(i);
            // 如果是第一个Boy
            if (i == 1) {
                first = boy;
                first.setNext(first);   // 构成一个环
                currBoy = first;        // currBoy指向第一个小孩
            } else {
                boy.setNext(first);
                currBoy.setNext(boy);
                currBoy = boy;
            }
        }
    }

    // 根据用户输入，计算出Boy出圈的操作
    // startNo：从第几个小孩开始数  countNum：数几下  nums：最初小孩的个数
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有问题！！！请重新输入！！！");
            return;
        }
        // 创建要给辅助的指针，帮助完成小孩出圈
        Boy helper = first;
        // 事先创建一个helper辅助指针，指向环形链表的最后一个节点
        while (true) {
            // 说明helper指向了最后一个节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // helper现在指向了最后一个节点
        // 小孩报数前，先让first和helper移动startNo-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时，让first和helper指针同时移动countNum-1次，然后出圈
        while (true) {
            // 当圈中只剩一个节点
            if (first == helper) {
                break;
            }
            // 让first和helper指针同时移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            // first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩为%d\n", first.getNo());
    }

    public void show() {
        if (first == null) {
            System.out.println("空");
            return;
        }
        Boy currBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", currBoy.getNo());
            if (currBoy.getNext() == first) {
                break;
            }
            currBoy = currBoy.getNext();
        }
    }
}

// 创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public int getNo() {
        return no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}

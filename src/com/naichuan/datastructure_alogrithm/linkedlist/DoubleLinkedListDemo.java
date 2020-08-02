package com.naichuan.datastructure_alogrithm.linkedlist;

/**
 * @author Naichuan Zhang
 * 29-Jul-2020
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(new HeroNode2(1, "name1", "nick1"));
        linkedList.add(new HeroNode2(2, "name2", "nick2"));
        linkedList.add(new HeroNode2(3, "name3", "nick3"));
        linkedList.add(new HeroNode2(5, "name5", "nick5"));
        linkedList.show();

        System.out.println("----------------------");

        linkedList.update(new HeroNode2(5, "changed name 5", "changed nick 5"));     // 修改成功
        linkedList.update(new HeroNode2(3, "name3haha", "nick3haha"));               // 修改成功
        linkedList.show();

        System.out.println("----------------------");

        linkedList.delete(1);
        linkedList.delete(2);
        linkedList.show();
    }
}

// 创建一个双向链表的类
class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        // 遍历列表
        while (true) {
            // temp到达链表尾部
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，temp后移
            temp = temp.next;
        }
        // 当退出循环后，temp指向了最后
        temp.next = heroNode;
        // heroNode向前指向temp
        heroNode.prev = temp;
    }

    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空！！！");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有该编号的节点，不能修改！！！");
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空！！！");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        } else {
            System.out.println("没有该编号的节点，不能删除！！！");
        }
    }

    // 显示链表
    public void show() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！！！");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.name + " " + temp.nickname + " " + temp.no);
            temp = temp.next;
        }
    }

    public HeroNode2 getHead() {
        return head;
    }
}

// 定义HeroNode，每个对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;   // 指向下一个节点
    public HeroNode2 prev;   // 指向前一个节点

    // 构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }
}

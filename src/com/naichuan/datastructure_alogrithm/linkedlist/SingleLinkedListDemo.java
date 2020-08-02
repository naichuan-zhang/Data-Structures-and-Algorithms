package com.naichuan.datastructure_alogrithm.linkedlist;

/**
 * @author Naichuan Zhang
 * 29-Jul-2020
 **/

import java.util.Stack;

/**
 * 单向链表测试
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(1, "name1", "nick1"));
        linkedList.add(new HeroNode(2, "name2", "nick2"));
        linkedList.add(new HeroNode(3, "name3", "nick3"));
        linkedList.add(new HeroNode(5, "name5", "nick5"));
        linkedList.addByNumber(new HeroNode(9, "name9", "nick9"));
        linkedList.addByNumber(new HeroNode(7, "name7", "nick7"));
        linkedList.addByNumber(new HeroNode(4, "name4", "nick4"));
//        linkedList.show();

        linkedList.update(new HeroNode(5, "changed name 5", "changed nick 5"));     // 修改成功
        linkedList.update(new HeroNode(6, "changed name 6", "changed nick 6"));     // 修改失败
        linkedList.update(new HeroNode(3, "name3haha", "nick3haha"));               // 修改成功
//        linkedList.show();

        linkedList.delete(11);
        linkedList.delete(5);
        linkedList.delete(9);
        linkedList.show();

        System.out.println(getLength(linkedList.getHead()));
        System.out.println(findLastKthNode(linkedList.getHead(), 2));
        reverse(linkedList.getHead());
        linkedList.show();

        reversedPrint(linkedList.getHead());
    }

    // 获取到单链表有效的节点个数（如果有头节点，需要不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null)
            return 0;
        int count = 0;
        HeroNode temp = head;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // 查找倒数第k个的节点
    public static HeroNode findLastKthNode(HeroNode head, int k) {
        if (head.next == null)
            return null;
        int length = getLength(head);
        int index = length - k + 1;
        if (index <= 0)
            return null;
        HeroNode temp = head;
        length = 0;
        while (length++ != index) {
            temp = temp.next;
        }
        return temp;
    }

    // 单链表反转
    public static void reverse(HeroNode head) {
        // 如果当前链表为空，或只有一个节点，无需反转
        if (head.next == null || head.next.next == null) {
            System.out.println("无法反转");
            return;
        }
        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode curr = head.next;
        HeroNode next;   // 指向当前节点的下一个节点
        HeroNode reversedHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个点，将其取出，并放在新的链表reversedHead的最前端
        while (curr != null) {
            next = curr.next;      // 先暂时保留当前节点的下一个节点，因为后面需要使用
            curr.next = reversedHead.next;      // 将curr的下一个节点指向新的链表的最前端
            reversedHead.next = curr;   // 将reversedHead.next指向cur
            curr = next;    // 让curr后移
        }
        // 将head.next指向reversedHead.next
        head.next = reversedHead.next;
    }

    // 反向输出链表（利用栈）
    public static void reversedPrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空！！！");
            return;
        }
        HeroNode temp = head;
        Stack<HeroNode> stack = new Stack<>();
        while (temp.next != null) {
            stack.push(temp.next);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            HeroNode node = stack.pop();
            System.out.println(node.toString());
        }
    }
}

// 定义一个SingleLinkedList来管理英雄
class SingleLinkedList {
    // 初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向列表
    // 当不考虑编号（no）的顺序时，找到当前列表的最后节点，将最后这个节点的next指向新的HeroNode即可
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
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
    }

    // 按number顺序插入节点
    public void addByNumber(HeroNode heroNode) {
        // 因为头节点不能动，因此我们仍然需要一个辅助指针（变量）来帮助找到添加的位置
        HeroNode temp = head;
        // 标记该元素是否重复
        boolean duplicated = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明该编号已经存在，不能添加该元素
                duplicated = true;
                break;
            }
            temp = temp.next;
        }
        if (duplicated) {
            throw new RuntimeException("元素存在！！！");
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    // 修改链表信息，根据编号锁定节点
    public void update(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("修改失败！！！");
                break;
            }
            if (temp.next.no == heroNode.no) {
                heroNode.next = temp.next.next;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    // 删除一个节点
    public void delete(int number) {
        HeroNode temp = head;
        while (temp.next.no != number) {
            temp = temp.next;
            if (temp.next == null) {
                System.out.println("删除失败！！！");
                return;
            }
        }
        temp.next = temp.next.next;
    }

    // 显示链表
    public void show() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！！！");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public HeroNode getHead() {
        return head;
    }
}

// 定义HeroNode，每个对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;   // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}

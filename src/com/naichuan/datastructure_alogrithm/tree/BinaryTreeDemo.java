package com.naichuan.datastructure_alogrithm.tree;

/**
 * @author Naichuan Zhang
 * 02-Aug-2020
 **/
public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        // 先手动创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);

//        // 前序遍历
//        binaryTree.preOrder();
//
//        // 中序遍历
//        binaryTree.infixOrder();
//
//        // 后序遍历
//        binaryTree.postOrder();
//
//        // 前序遍历查找
//        HeroNode heroNode = binaryTree.preOrderSearch(3);
//        System.out.println(heroNode.toString());

        System.out.println("删除前：");
        binaryTree.preOrder();

        // 删除节点测试
        System.out.println("删除后：");
        binaryTree.delNode(4);
        binaryTree.delNode(2);
        binaryTree.preOrder();
    }
}

// 定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空！！！");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空！！！");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空！！！");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }
        return null;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        }
        return null;
    }

    // 后续遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        }
        return null;
    }

    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root节点，立即判断root是否为要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空！！！");
        }
    }
}

// 先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历（根，左，右）
    public void preOrder() {
        System.out.println(this.toString());
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历（左，根，右）
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历（左，右，根）
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this.toString());
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // 比较当前节点是否等于要查找的
        if (this.no == no) {
            return this;
        }
        // 向左查找
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            // 说明在左子树上找到
            return node;
        }
        // 如果还未找到，继续判断
        if (this.right != null) {
            node = this.right.preOrderSearch(no);
        }
        return node;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);
        }
        return node;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }

    // 递归删除节点
    // 若节点为叶子节点，则删除该节点
    // 若节点为非叶子节点，则删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

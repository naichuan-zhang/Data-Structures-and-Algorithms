package com.naichuan.datastructure_alogrithm.tree.threaded;

/**
 * @author Naichuan Zhang
 * 02-Aug-2020
 **/
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        // 测试线索化
        threadedBinaryTree.threadedNodes();

        // 测试：以10号节点进行测试
        HeroNode leftNode = node5.getLeft();
        System.out.println(leftNode);
        HeroNode rightNode = node5.getRight();
        System.out.println(rightNode);
    }
}

// 定义线索二叉树，实现线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    // 为了实现线索化，需要创建一个指向当前节点的前驱的指针
    private HeroNode prev = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    // 编写对二叉树进行中序线索化的方法
    // left指向左子树或者前驱节点
    // right指向右子树或者后继节点
    public void threadedNodes(HeroNode node) {
        // 如果node为null，则不能线索化
        if (node == null) {
            return;
        }
        // 1. 线索化左子树
        threadedNodes(node.getLeft());
        // 2. 线索化当前节点
        // 处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(prev);
            // 修改当前节点的左指针类型
            node.setLeftType(1);
        }
        // 处理后继节点
        if (prev != null && prev.getRight() == null) {
            // 让前驱节点的右指针指向当前节点
            prev.setRight(node);
            // 修改前驱节点的右指针类型
            prev.setRightType(1);
        }
        // 将当前节点设为下一个节点的前驱节点（重要！！！！）
        prev = node;
        // 3. 线索化右子树
        threadedNodes(node.getRight());
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
    // 如果leftType为0表示指向的是左子树；如果为1，表示前驱节点
    // 如果rightType为0，表示指向的是右子树；如果为1，表示后继节点
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}


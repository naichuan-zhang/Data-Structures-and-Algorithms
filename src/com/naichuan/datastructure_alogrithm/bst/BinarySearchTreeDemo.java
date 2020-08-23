package com.naichuan.datastructure_alogrithm.bst;

/**
 * @author Naichuan Zhang
 * 23-Aug-2020
 **/
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();
    }
}

class BinarySearchTree {
    private Node root;
    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("该二叉排序树为空！");
        }
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null)
            return null;
        return root.search(value);
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null)
            return null;
        return root.searchParent(value);
    }

    // 删除node为根节点的BST的最小节点
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这是target指向了最小值，删除，返回
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null)
            return;
        Node targetNode = search(value);
        if (targetNode == null)
            return;
        // 如果targetNode没有父节点（也就是根节点）
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 去查找targetNode的父节点
        Node parentNode = searchParent(value);
        // 如果要删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断targetNode是左子节点还是右子节点
            if (parentNode.left != null && parentNode.left.value == value) {    // 是左子节点
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {   // 是右子节点
                parentNode.right = null;
            }
        }
        // 如果要删除的节点有两颗子树
        else if (targetNode.left != null && targetNode.right != null) {
            int minValue = delRightTreeMin(targetNode.right);
            targetNode.value = minValue;
        }
        // 如果要删除的节点只有一颗子树
        else {
            if (targetNode.left != null) {
                // targetNode是parent的左子节点
                if (parentNode.left.value == value) {
                    parentNode.left = targetNode.left;
                } else {
                    parentNode.right = targetNode.left;
                }
            }
            if (targetNode.right != null) {
                if (parentNode.left.value == value) {
                    parentNode.left = targetNode.right;
                } else {
                    parentNode.right = targetNode.right;
                }
            }
        }
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;
    public Node(int value) {
        this.value = value;
    }
    // 添加节点（递归），满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) return;
        if (node.value < this.value) {
            // 左子树
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node);
        } else {
            // 右子树
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }
    }

    // 中序遍历（左-根-右）
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this.value);
        if (this.right != null)
            this.right.infixOrder();
    }

    // 查找到删除的节点，找到返回该节点，否则返回null
    public Node search(int value) {
        if (value == this.value) {  // 找到
            return this;
        } else if (value < this.value) {
            if (this.left == null)
                return null;
            return this.left.search(value);
        } else {
            if (this.right == null)
                return null;
            return this.right.search(value);
        }
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}
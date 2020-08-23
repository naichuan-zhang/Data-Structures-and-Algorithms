package com.naichuan.datastructure_alogrithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Naichuan Zhang
 * 23-Aug-2020
 **/
public class Graph {
    private ArrayList<String> vertexList;   // 顶点
    private int[][] edges;      // 存储图对应的邻接矩阵
    private int numOfEdges;     // 表示边的数目
    // 定义boolean数组，记录某个定点是否被访问过
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    // ################### 深度优先遍历（DFS）###################
    // 对dfs重载
    public void dfs() {
        for (int i = 0; i < getNumOfVertices(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }
    public void dfs(boolean[] isVisited, int i) {
        // 访问该节点，输出
        System.out.println(getValueByIndex(i) + " ");
        // 将该节点置为已访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点北方问过
            w = getNextNeighbor(i, w);
        }
    }
    // 得到第一个邻接节点的下标
    // 如果存在返回对应的下标，否则返回-1
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    // 根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    // #######################################################

    // ################### 广度优先遍历（BFS）###################
    public void bfs() {
        for (int i = 0; i < getNumOfVertices(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    public void bfs(boolean[] isVisited, int i) {
        int u;  // 队列头节点的下标
        int w;  // 邻接节点w
        // 队列，记录节点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        // 访问节点
        System.out.println(getValueByIndex(i) + " ");
        // 标为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(i) + " ");
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }
    // #######################################################

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回节点i的下标对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示邻接矩阵
    public void show() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点个数
    public int getNumOfVertices() {
        return vertexList.size();
    }

    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String val : vertexValue) {
            graph.insertVertex(val);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.show();

        graph.dfs();
        graph.bfs();
    }
}

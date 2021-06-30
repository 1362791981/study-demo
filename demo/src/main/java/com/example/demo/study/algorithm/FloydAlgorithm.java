package com.example.demo.study.algorithm;

import java.util.Scanner;

/**
 * @Description: 弗洛伊德算法
 * Floyd算法又称为插点法，是一种利用动态规划的思想寻找给定的加权图中多源点之间最短路径的算法，与Dijkstra算法类似。该算法是一种在具有正或负边缘权重（但没有负环）的加权图中找到最短路径的算法，即支持负权值但不支持负权环。
 * 弗洛伊德算法采用的是动态规划思想，其状态转移方程如下： 
 * 其中matrix[i,j]表示i到j的最短距离，k是穷举i到j之间可能经过的中间点，当中间点为k时，对整个矩阵即从i到j的路径长度进行更新，对所有可能经过的中间点进行遍历以得到全局最优的最短路径。
 * 算法的单个执行将找到所有顶点对之间的最短路径长度，与迪杰斯特阿拉算法的计算目标有一些差异，迪杰斯特拉计算的是单源最短路径，而弗洛伊德计算的是多源最短路径，其时间复杂度为O(n³)。
 * 虽然它不返回路径本身的细节，但是可以通过对算法的简单修改来重建路径，我们利用这个思想，通过递归的方式访问每条路径经过的中间节点，对最终的路径进行输出。
 * 参考：https://blog.csdn.net/qq_34842671/article/details/90637502
 * @ClassName: FloydAlgorithm
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-16 14:05
 */
public class FloydAlgorithm {

    public static int MaxValue = 100000;
    public static int[][] path;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入顶点数和边数:");
        //顶点数
        int vertex = input.nextInt();
        //边数
        int edge = input.nextInt();

        int[][] matrix = new int[vertex][vertex];
        //初始化邻接矩阵
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                matrix[i][j] = MaxValue;
            }
        }

        //初始化路径数组
        path = new int[matrix.length][matrix.length];

        //初始化边权值
        for (int i = 0; i < edge; i++) {
            System.out.println("请输入第" + (i + 1) + "条边与其权值:");
            int source = input.nextInt();
            int target = input.nextInt();
            int weight = input.nextInt();
            matrix[source][target] = weight;
        }

        //调用算法计算最短路径
        floyd(matrix);
    }

    //非递归实现
    public static void floyd(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                path[i][j] = -1;
            }
        }

        for (int m = 0; m < matrix.length; m++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        //记录经由哪个点到达
                        path[i][j] = m;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    if (matrix[i][j] == MaxValue) {
                        System.out.println(i + "到" + j + "不可达");
                    } else {
                        System.out.print(i + "到" + j + "的最短路径长度是：" + matrix[i][j]);
                        System.out.print("最短路径为：" + i + "->");
                        findPath(i, j);
                        System.out.println(j);
                    }
                }
            }
        }
    }

    //递归寻找路径
    public static void findPath(int i, int j) {
        int m = path[i][j];
        if (m == -1) {
            return;
        }

        findPath(i, m);
        System.out.print(m + "->");
        findPath(m, j);
    }
}

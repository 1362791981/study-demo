package com.example.demo.study.algorithm;

import com.example.demo.study.algorithm.entity.MGraph;
import com.example.demo.study.algorithm.entity.MinTree;

/**
 * @Description: 普利姆算法
 * 参考： https://blog.csdn.net/qq1137623160/article/details/102620776
 *
 * 最小生成树
 * 修路问题本质就是就是最小生成树问题， 先介绍一下最小生成树(Minimum Cost Spanning Tree)，简称 MST。 给定一个带权的无向连通图,如何选取一棵生成树,使树上所有边上权的总和为最小,这叫最小生成树
 * N 个顶点，一定有 N-1 条边
 * 包含全部顶点
 * N-1 条边都在图中
 * 举例说明(如图:)
 * 求最小生成树的算法主要是普里姆算法和克鲁斯卡尔算法
 *
 *
 * 普利姆(Prim)算法求最小生成树，也就是在包含 n 个顶点的连通图中，找出只有(n-1)条边包含所有 n 个顶点的 连通子图，也就是所谓的极小连通子图
 * 普利姆的算法如下:
 *
 * 设G=(V,E)是连通网，T=(U,D)是最小生成树，V,U是顶点集合，E,D是边的集合
 * 若从顶点u开始构造最小生成树，则从集合V中取出顶点u放入集合U中，标记顶点v的visited[u]=1
 * 若集合U中顶点ui与集合V-U中的顶点vj之间存在边，则寻找这些边中权值最小的边，但不能构成回路，将顶点 vj 加入集合 U 中，将边(ui,vj)加入集合 D 中，标记 visited[vj]=1
 * 重复步骤2，直到U与V相等，即所有顶点都被标记为访问过，此时D中有n-1条边
 * 提示: 单独看步骤很难理解，我们通过代码来讲解，比较好理解.
 * 图解普利姆算法
 *
 * 1.有胜利乡有7个村庄(A,B,C,D,E,F,G)，现在需要修路把7个村庄连通
 * 2.各个村庄的距离用边线表示(权) ，比如 A – B 距离 5 公里
 * 3.问:如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 * @ClassName: PrimAlgorithm
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-16 11:38
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        //测试看看图是否创建 ok
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{{10000, 5, 7, 10000, 10000, 10000, 2}, {5, 10000, 10000, 9, 10000, 10000, 3}, {7, 10000, 10000, 10000, 8, 10000, 10000}, {10000, 9, 10000, 10000, 10000, 4, 10000}, {10000, 10000, 8, 10000, 10000, 5, 4}, {10000, 10000, 10000, 4, 5, 10000, 6}, {2, 3, 10000, 10000, 4, 6, 10000},};
        //创建 MGraph 对象
        MGraph graph = new MGraph(verxs); //创建一个 MinTree 对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight); //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 0);//
    }

}

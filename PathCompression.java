package com.coursera.firstweek;

/*
* 测试路径压缩
* */

public class PathCompression {
    int a[];
    int sz[];

    public PathCompression(int n) {
        a = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
            sz[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    //路径压缩，将当前节点的id设为它的祖父结点
    public int root(int p) {
        while (p != a[p]) {
            a[p] = a[a[p]];//相当于展开一部分路径
            p = a[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (sz[proot] <sz[qroot]) {
            a[proot] = qroot;//保证小树在大树的下面
            sz[qroot] += sz[proot];
        } else {
            a[qroot] = proot;
            sz[proot] += sz[qroot];
        }
    }

    public static void main(String[] args) {
        PathCompression pc = new PathCompression(10);
        pc.union(1, 3);
        pc.union(3, 2);
        pc.union(4, 1);
        pc.union(5, 6);
        pc.union(8, 9);
        pc.union(9, 6);
        pc.union(7, 5);
        pc.union(1, 7);
        for (int i : pc.a
        ) {
            System.out.println(i);

        }
    }
}


package com.coursera.firstweek;

/*
* 测试带权快速合并算法*/

public class WeightedQuickUnion {
    int a[];
    int sz[];//设置一个数组，用来存放当前节点到根节点的距离
    public WeightedQuickUnion(int n){
        a=new int[n];
        sz=new int[n];
        for (int i=0;i<n;i++){
            a[i]=i;
            sz[i]=1;
        }
    }
    public boolean connected(int p,int q){
        return root(p)==root(q);
    }
    public int root(int p){
        while (p!=a[p])
            p=a[p];
        return p;
    }
    public void union(int p,int q){
        int proot=root(p);
        int qroot=root(q);
        if(sz[proot]<sz[qroot]){
            a[proot]=qroot;//保证小树在大树的下面
            sz[qroot]+=sz[proot];
            //存在一个隐含条件：当且仅当另一棵树的节点数大于等于当前树节点数时
            //当前树可被当作位小树，合并到大树下面
        }
        else {
            a[qroot]=proot;
            sz[proot]+=sz[qroot];
        }

    }

    public static void main(String[] args) {
        WeightedQuickUnion wu = new WeightedQuickUnion(10);
        wu.union(1, 3);
        wu.union(3, 2);
        wu.union(4, 1);
        wu.union(5, 6);
        wu.union(8, 9);
        wu.union(9, 6);
        wu.union(7, 5);
        wu.union(1, 7);
        for (int i : wu.a
        ) {
            System.out.println(i);
        }
        System.out.println(wu.sz[5]);
    }
}

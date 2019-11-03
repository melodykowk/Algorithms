package com.coursera.firstweek;

public class QuickUnion {
    int a[];
    public QuickUnion(int n){
        a=new int[n];
        for (int i=0;i<n;i++){
            a[i]=i;
        }
    }
    public boolean connected(int p,int q){
        return root(p)==root(q);
    }

    public int  root(int p){
        while (p!=a[p])
            p=a[p];
        return p;
    }

    public void union(int p,int q){
        int proot=root(p);
        a[proot]=root(q);
    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(3, 5);
        qu.union(5, 1);
        qu.union(1, 8);
        qu.union(3, 6);
        qu.union(4, 7);
        qu.union(9, 4);
        for (int i : qu.a
        ) {
            System.out.println(i);
        }
        System.out.println(qu.connected(3,4));
    }

}

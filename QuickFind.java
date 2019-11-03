package com.coursera.firstweek;
/*
* 测试quick-find
* */

public class QuickFind {
    private int a[];

    //构造方法，初始化数组
    public QuickFind(int n){
        a=new int[n];
        for (int i=0;i<n;i++){
            a[i]=i;
        }
    }

    public boolean find(int p,int q){
       return a[p]==a[q];
    }

    public void union(int p,int q){
        int pid=a[p];
        for(int i=0;i<a.length;i++){
            if(a[i]==pid){
                a[i]=a[q];
            }
        }
    }

    public static void main(String[] args) {
        QuickFind qf=new QuickFind(10);
        qf.union(3,4);
        qf.union(5,7);
        qf.union(2,6);
        qf.union(1,9);
        qf.union(0,3);
        qf.union(5,8);
        System.out.println(qf.find(5,6));
        for (int i:qf.a
             ) {
            System.out.println(i);
        }
    }
}

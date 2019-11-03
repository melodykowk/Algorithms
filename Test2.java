package com.coursera.firstweek;
import edu.princeton.cs.algs4.*;
/*
* 定义了一个数组large[]
* 用来记录一个连通分量中
* 最大的值
* */
public class Test2 {
    int a[];
    int sz[];
    int large[];

    public Test2(int n){
        a=new int[n];
        sz=new int[n];
        large=new int[n];
        for (int i=0;i<n;i++){
            a[i]=i;
            sz[i]=1;
            large[i]=i;
        }
    }
    public int root(int p){
        while (p!=a[p]){
            a[p]=a[a[p]];
            p=a[p];
        }
        return p;
    }
    public boolean connected(int p,int q){
        return root(p)==root(q);
    }
    public void union(int p,int q){
        int proot=root(p);
        int qroot=root(q);
        if(sz[proot]<sz[qroot]){
            a[proot]=qroot;
            sz[qroot]+=sz[proot];
            if(large[proot]>large[qroot])
                large[qroot]=large[proot];
        }
        else{
            a[qroot]=proot;
            sz[proot]+=sz[qroot];
            if(large[qroot]>large[proot])
                large[proot]=large[qroot];
        }
    }
    //定义一个find（）方法，用来返回一个连通分量中最大的数
    public int find(int p){
    /*    int rsz=sz[root(p)];
        int proot=root(p);
        int large=0;
            for(int i=0;i<a.length;i++){
                if(root(i)==proot){
                    if(i>large){
                        large=i;
                    }
            }
        }
            return large;
            过于麻烦!!!!*/
    int proot=root(p);
    return large[proot];
    }

    public static void main(String[] args) {
        int n=StdIn.readInt();
        Test2 t=new Test2(n);
        while (!StdIn.isEmpty()){
            int p=StdIn.readInt();
            int q=StdIn.readInt();
            if(!t.connected(p,q)){
                t.union(p,q);
                StdOut.println(p+" "+q);
            }
        }
//        t.union(1,2);
//        t.union(3,2);
//        t.union(4,6);
//        t.union(6,9);
//        t.union(5,7);
//        t.union(1,5);
//        for (int i:t.a
//             ) {
//            System.out.println(i);
//        }
        System.out.println(t.connected(1,9));
        System.out.println(t.find(2));
    }
}

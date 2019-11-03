package com.coursera.firstweek;
/*
* 测试渗虑算法
* */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf_backwash;
    private boolean[] ifopen;
    private int count=0;
    int n;


    public Percolation(int n){
        if(n<=0)throw new IllegalArgumentException("n is <=0");
        uf=new WeightedQuickUnionUF((n+1)*n+n+1);
        uf_backwash=new WeightedQuickUnionUF(n*n+n+1);
        ifopen=new boolean[((n+1)*n+n+1)];
        this.n=n;

        //初始化第0行和第n+1行
        for (int i=0*n+1;i<=0*n+n;i++){
            uf.union(0*n+1,0*n+i);
            uf_backwash.union(0*n+1,0*n+i);
            ifopen[i]=true;
            uf.union((n+1)*n+1,(n+1)*n+i);
            ifopen[(n+1)*n+i]=true;
        }


    }

    //开放一个位，若其前后左右相邻位开放，则将它们连接在一起
    public void open(int row,int col){
        if(row<1||row>n) throw new IllegalArgumentException("row index out of bounds");
        if(col<1||col>n) throw new IllegalArgumentException("column index out of bounds");
        if(!this.isOpen(row,col)){
            count++;
            ifopen[row*n+col]=true;

            if (ifopen[(row-1)*n+col]){
                uf.union(n*(row-1)+col,n*row+col);
                uf_backwash.union(n*(row-1)+col,n*row+col);
            }
            if (ifopen[(row+1)*n+col]){
                uf.union(n*(row+1)+col,n*row+col);
                if(row!=n)
                    uf_backwash.union(n*(row+1)+col,n*row+col);
            }
            if(col!=1&&ifopen[row*n+col-1]){
                uf.union(row*n+col-1,row*n+col);
                uf_backwash.union(row*n+col-1,row*n+col);
            }
            if(col!=n&&ifopen[row*n+col+1]){
                uf.union(row*n+col+1,row*n+col);
                uf_backwash.union(row*n+col+1,row*n+col);
            }
        }

    }

    //某个位置是否开放
    public boolean isOpen(int row,int col){
        if(row<1||row>n) throw new IllegalArgumentException("row index out of bounds");
        if(col<1||col>n) throw new IllegalArgumentException("column index out of bounds");
        return ifopen[row*n+col];
    }

    //测试某个位置是否已经渗虑(防止回流)
    public boolean isFull(int row,int col){
        if(row<1||row>n) throw new IllegalArgumentException("row index out of bounds");
        if(col<1||col>n) throw new IllegalArgumentException("column index out of bounds");
        return uf_backwash.connected(n*row+col,0*n+1);
    }

    //返回已经开放的位
    public int numberofOpenSites(){
        return count;
    }

    // 测试是否渗虑
    public boolean percolates(){
        return uf.connected(0*n+1,(n+1)*n+1);
    }

    public static void main(String[] args) {
        int n= StdIn.readInt();
        Percolation p=new Percolation(n);
        System.out.println("系统现在渗虑吗？ "+p.percolates());
        while (!StdIn.isEmpty()){
            int i=StdIn.readInt();
            int j=StdIn.readInt();
            p.open(i,j);
            System.out.println(p.isFull(i,j));
        }


    }



}

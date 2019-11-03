package com.coursera.firstweek;
/*
* 求渗虑95%的置信区间
* */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double staT[];
    private double sta_mean;//平均数
    private double sta_stddev;//标准差
    private int trials;//进行试验的次数

    public PercolationStats(int n,int trials){
        if(n<=0)throw new IllegalArgumentException();
        if(trials<=0)throw new IllegalArgumentException();
        staT=new double[trials];
        this.trials=trials;
        int times=0;

        while (times<trials){
            Percolation p=new Percolation(n);
            while (!p.percolates()){
                int x= StdRandom.uniform(n)+1;
                int y= StdRandom.uniform(n)+1;
                p.open(x,y);
            }
            staT[times]=(double)p.numberofOpenSites()/(double)(n*n);
            times++;
        }
        this.sta_mean= StdStats.mean(staT);
        this.sta_stddev=StdStats.stddev(staT);
    }
    public double mean(){
        return this.sta_mean;
    }
    public double stddev(){
        return this.sta_stddev;
    }
    public double confidenceLo(){
        return this.sta_mean-1.96*this.sta_stddev/Math.sqrt(trials);
    }
    public double confidenceHi(){
        return this.sta_mean+1.96*this.sta_stddev/Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n= StdIn.readInt();
        int t= StdIn.readInt();
        PercolationStats ps=new PercolationStats(n,t);
        StdOut.println("mean:"+ps.sta_mean);
        StdOut.println("stddev:"+ps.sta_stddev);
        StdOut.println("95% confidence interval:"+ps.confidenceLo()+", "+ps.confidenceHi());
    }

}

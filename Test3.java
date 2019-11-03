package com.coursera.firstweek;

import java.util.Arrays;

public class Test3 {
    private int a[];

    public Test3(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
    }

    //删除元素i
    public void delete(int p) {
        int index = getIndex(p);
        int copy[] = new int[a.length - 1];
        //复制元素p以前的数组
        for (int j = 0; j < copy.length; j++) {
            if (j < index) {
                copy[j] = a[j];
            } else
                copy[j] = a[j + 1];
        }
        a = copy;
    }

    //返回在数组中大于等于p的最小数
    public int successor(int p) {
        sort();
        int index=getIndex(p);
        return a[index+1];
    }

    public void sort() {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }

        }
    }

    public int getIndex(int p) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == p)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Test3 t=new Test3(10);
        t.delete(5);
        t.delete(9);
        System.out.println(t.a.length);
        System.out.println(t.successor(6));
    }
}

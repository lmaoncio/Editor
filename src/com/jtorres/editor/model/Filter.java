package com.jtorres.editor.model;
public class Filter {
    private int[][] filter;
    private int k;

    public void calculateK() {
        int divisorK = 0;
        int temp;


        for (int[] ints : filter) {
            for (int y = 0; y < filter[0].length; y++) {
                temp = ints[y];
                divisorK = divisorK + temp;
            }
        }

        if (divisorK == 0) {
            divisorK = 1;
        }

        this.setK(divisorK);
    }

    public void blurFilter() {
        int[][] f = new int[3][3];
        f[0][0] = 5;
        f[0][1] = 5;
        f[0][2] = 5;
        f[1][0] = 5;
        f[1][1] = 5;
        f[1][2] = 5;
        f[2][0] = 5;
        f[2][1] = 5;
        f[2][2] = 5;
        this.setFilter(f);
        calculateK();
    }

    public void sharpFilter() {
        int[][] f = new int[3][3];
        f[0][0] = 0;
        f[0][1] = -1;
        f[0][2] = 0;
        f[1][0] = -1;
        f[1][1] = 5;
        f[1][2] = -1;
        f[2][0] = 0;
        f[2][1] = -1;
        f[2][2] = 0;
        this.setFilter(f);
        calculateK();
    }

    public int[][] getFilter() {
        return filter;
    }

    public void setFilter(int[][] filter) {
        this.filter = filter;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}

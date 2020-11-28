package com.jtorres.editor.model;
public class Filtro {
    private int[][] filtro;
    private int divisorK;

    public void calcularK() {
        int divisorK = 0;
        int temp = 0;


        for (int x = 0; x < filtro.length ; x++) {
            for (int y = 0; y < filtro[0].length; y++) {
                temp = filtro[x][y];
                divisorK = divisorK + temp;
            }
        }

        if (divisorK == 0) {
            divisorK = 1;
        }

        this.setDivisorK(divisorK);
    }

    public void filtroDifuminado() {
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
        this.setFiltro(f);
        calcularK();
    }

    public void filtroNegativo() {
        int[][] f = new int[3][3];
        f[0][0] = -1;
        f[0][1] = -1;
        f[0][2] = -1;
        f[1][0] = 0;
        f[1][1] = 0;
        f[1][2] = 0;
        f[2][0] = 1;
        f[2][1] = 1;
        f[2][2] = 1;
        this.setFiltro(f);
        calcularK();
    }

    public void filtroSharp() {
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
        this.setFiltro(f);
        calcularK();
    }

    public int[][] getFiltro() {
        return filtro;
    }

    public void setFiltro(int[][] filtro) {
        this.filtro = filtro;
    }

    public int getDivisorK() {
        return divisorK;
    }

    public void setDivisorK(int divisorK) {
        this.divisorK = divisorK;
    }
}

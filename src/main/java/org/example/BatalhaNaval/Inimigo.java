package org.example.BatalhaNaval;

import java.util.Random;

public class Inimigo {
    Random random = new Random();
    private int[][] inimigo = new int[5][10];
    private int repetir = 0, inicio = 1;

    public int[][] getInimigo() {
        return inimigo;
    }

    public void setInimigo(int[][] inimigo) {
        this.inimigo = inimigo;
    }

    public void Enemy(int dificuldade) {
        if(inicio == 1) {
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 10; j++) {
                    inimigo[i][j] = 0;
                }
            }
            Criacao();
            inicio = 0;
        } else {
            switch (dificuldade) {
                case 1:
                    Facil();
                    break;
                case 2:
                    Medio();
                    break;
                case 3:
                    Dificil();
                    break;
            }
        }
    }

    //consertar, está bugando tudo
    private void Criacao() {
        int linha, coluna, tipo, direcao, quantNavioPequeno = 4, quantNavioMedio = 3, quantNavioGrande = 2;

        while(quantNavioPequeno > 0 || quantNavioMedio > 0 || quantNavioGrande > 0 ) {
            tipo = random.nextInt(3);
            direcao = random.nextInt(2);

            do {
                repetir = 0;

                if(direcao == 0) {
                    linha = random.nextInt(5);
                    coluna = random.nextInt(10 - tipo) + tipo;
                } else {
                    linha = random.nextInt(5 - tipo) + tipo;
                    coluna = random.nextInt(10);
                }
                System.out.println(linha + " " + coluna + " " + tipo + " " + direcao);
                System.out.println(quantNavioPequeno + " " + quantNavioMedio + " " + quantNavioGrande);

                if(direcao == 0) {
                    if(tipo == 0) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha][coluna - 1] == 0) {
                            inimigo[linha][coluna] = 3;
                            inimigo[linha][coluna - 1] = 1;
                            quantNavioPequeno--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 1 && coluna > 1) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha][coluna - 1] == 0 && inimigo[linha][coluna - 2] == 0) {
                            inimigo[linha][coluna] = 3;
                            inimigo[linha][coluna - 1] = 2;
                            inimigo[linha][coluna - 2] = 1;
                            quantNavioMedio--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 2 && coluna > 2) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha][coluna - 1] == 0 && inimigo[linha][coluna - 2] == 0 && inimigo[linha][coluna - 3] == 0) {
                            inimigo[linha][coluna] = 3;
                            inimigo[linha][coluna - 1] = 2;
                            inimigo[linha][coluna - 2] = 2;
                            inimigo[linha][coluna - 3] = 1;
                            quantNavioGrande--;
                        } else {
                            repetir = 1;
                        }
                    }
                } else {
                    if(tipo == 0) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha - 1][coluna] == 0) {
                            inimigo[linha][coluna] = 6;
                            inimigo[linha - 1][coluna] = 4;
                            quantNavioPequeno--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 1 && linha > 1) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha - 1][coluna] == 0 && inimigo[linha - 2][coluna] == 0) {
                            inimigo[linha][coluna] = 6;
                            inimigo[linha - 1][coluna] = 5;
                            inimigo[linha - 2][coluna] = 4;
                            quantNavioMedio--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 2 && linha > 2) {
                        if(inimigo[linha][coluna] == 0 && inimigo[linha - 1][coluna] == 0 && inimigo[linha - 2][coluna] == 0 && inimigo[linha - 3][coluna] == 0) {
                            inimigo[linha][coluna] = 6;
                            inimigo[linha - 1][coluna] = 5;
                            inimigo[linha - 2][coluna] = 5;
                            inimigo[linha - 3][coluna] = 4;
                            quantNavioGrande--;
                        } else {
                            repetir = 1;
                        }
                    }
                }
            } while(repetir == 1);
        }
    }

    private void Facil() {


    }

    private void Medio() {

    }

    private void Dificil() {

    }
}

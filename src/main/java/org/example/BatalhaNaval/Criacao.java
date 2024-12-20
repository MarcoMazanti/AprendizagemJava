package org.example.BatalhaNaval;

import java.util.Random;

public class Criacao {
    public void criar(int[][] inimigo) {
        Random random = new Random();
        int linha, coluna, tipo, direcao, quantNavioPequeno = 4, quantNavioMedio = 3, quantNavioGrande = 2, repetir = 0, i, j;

        for(i = 0; i < 5; i++) {
            for(j = 0; j < 10; j++) {
                inimigo[i][j] = 0;
            }
        }

        while(quantNavioPequeno > 0 || quantNavioMedio > 0 || quantNavioGrande > 0 || repetir == 1) {
            do {
                repetir = 0;

                tipo = random.nextInt(3) + 1;
                // 1 - <> / 2 - <=> / 3 - <==>

                if(tipo == 1 && quantNavioPequeno == 0 || tipo == 2 && quantNavioMedio == 0 || tipo == 3 && quantNavioGrande == 0) {
                    repetir = 1;
                }
            } while(repetir == 1);

            direcao = random.nextInt(2);
            // 0 - Horizontal / 1 - Vertical

            if(direcao == 0) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10 - tipo) + tipo;
            } else {
                linha = random.nextInt(5 - tipo) + tipo;
                coluna = random.nextInt(10);
            }

            if(direcao == 0) {
                if(tipo == 1) {
                    if(inimigo[linha][coluna] == 0 && inimigo[linha][coluna - 1] == 0) {
                        inimigo[linha][coluna] = 3;
                        inimigo[linha][coluna - 1] = 1;
                        quantNavioPequeno--;
                    } else {
                        repetir = 1;
                    }
                } else if(tipo == 2) {
                    if(inimigo[linha][coluna] == 0 && inimigo[linha][coluna - 1] == 0 && inimigo[linha][coluna - 2] == 0) {
                        inimigo[linha][coluna] = 3;
                        inimigo[linha][coluna - 1] = 2;
                        inimigo[linha][coluna - 2] = 1;
                        quantNavioMedio--;
                    } else {
                        repetir = 1;
                    }
                } else {
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
                if(tipo == 1) {
                    if(inimigo[linha][coluna] == 0 && inimigo[linha - 1][coluna] == 0) {
                        inimigo[linha][coluna] = 6;
                        inimigo[linha - 1][coluna] = 4;
                        quantNavioPequeno--;
                    } else {
                        repetir = 1;
                    }
                } else if(tipo == 2) {
                    if(inimigo[linha][coluna] == 0 && inimigo[linha - 1][coluna] == 0 && inimigo[linha - 2][coluna] == 0) {
                        inimigo[linha][coluna] = 6;
                        inimigo[linha - 1][coluna] = 5;
                        inimigo[linha - 2][coluna] = 4;
                        quantNavioMedio--;
                    } else {
                        repetir = 1;
                    }
                } else {
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
        }
    }
}

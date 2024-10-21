package org.example.JogoDaVelha;

import java.util.Scanner;

public class Jogo {
    int i = 0, j = 0;

    public void jogoDaVelha() {
        Scanner scan = new Scanner(System.in);

        int vitoria = 0, coluna = 0, linha = 0, jogador = 0, tamanho = 3;

        System.out.println();
        do {
            if(tamanho < 3 || tamanho > 10) {
                System.out.println();
                System.out.println("Você digitou um tamanho inválido! Tente novamente.");
            }

            System.out.print("Digite o tamanho do tabuleiro, variando entre 3 e 10: ");
            tamanho = scan.nextInt();
        } while(tamanho < 3 || tamanho > 10);

        int[][] jogo = new int[tamanho][tamanho];
        int[][] mapa = new int[1 + (2 * (tamanho - 1))][1 + (2 * (tamanho - 1))];

        for(i = 0; i < 1 + (2 * (tamanho - 1)); i++) {
            for(j = 0; j < 1 + (2 * (tamanho - 1)); j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 1) {
                        mapa[i][j] = 9;
                    } else {
                        mapa[i][j] = 0;
                    }
                } else {
                    if(j % 2 == 0) {
                        mapa[i][j] = 7;
                    } else {
                        mapa[i][j] = 8;
                    }

                }
                /*
                Significado dos números
                9 - "|"
                8 - "+"
                7 - "-"
                0 - " "
                1 - "X"
                2 - "O"
                */
            }
        }

        do {
            if(jogador == 0) {
                jogador = 1;
            } else if(jogador == 1) {
                jogador = 2;
            } else {
                jogador = 1;
            }

            do {
                do {
                    if(linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
                        System.out.println("Casa inexistente! Tente novamente.");
                    }

                    System.out.printf("%nJogador %d%n", jogador);
                    leitura(mapa);

                    System.out.printf("Digite a localização da casa! (1 - %d)%n", tamanho);
                    System.out.print("Linha: ");
                    linha = scan.nextInt();
                    System.out.print("Coluna: ");
                    coluna = scan.nextInt();

                    linha--;
                    coluna--;
                } while(linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho);
            } while(jogo[linha][coluna] != 0);

            jogo[linha][coluna] = jogador;
            mapa[2 * linha][2 * coluna] = jogador;
            vitoria = vitoria(jogo, jogador);
        } while(vitoria != 1);

        leitura(mapa);
        System.out.printf("O jogador %d venceu!!!%n", jogador);
    }

    private int vitoria(int[][] jogo, int jogador) {
        int vitoria = 0, horizontal = 0, vertical = 0, diagonalPrincipal = 0, diagonalSecundaria = 0;


        for(i = 0; i < jogo.length; i++) {
            for(j = 0; j < jogo.length; j++) {
                if(jogo[j][i] == jogador) {
                    horizontal++;
                }
                if(jogo[i][j] == jogador) {
                    vertical++;
                }
                if(i == j && jogo[i][i] == jogador) {
                    diagonalPrincipal++;
                }
                if(i + j == jogo.length + 1 && jogo[i][j] == jogador) {
                    diagonalSecundaria++;
                }
            }
            if(horizontal == jogo.length || vertical == jogo.length || diagonalPrincipal == jogo.length || diagonalSecundaria == jogo.length) {
                vitoria = 1;
                break;
            }
            horizontal = 0;
            vertical = 0;
            diagonalPrincipal = 0;
            diagonalSecundaria = 0;
        }

        return vitoria;
    }

    private void leitura(int[][] mapa) {
        System.out.println();
        for(i = 0; i < mapa.length; i++) {
            for(j = 0; j < mapa.length; j++) {
                switch (mapa[i][j]) {
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("X");
                        break;
                    case 2:
                        System.out.print("O");
                        break;
                    case 7:
                        System.out.print("-");
                        break;
                    case 8:
                        System.out.print("+");
                        break;
                    case 9:
                        System.out.print("|");
                    }
            }
            System.out.println();
        }
        System.out.println();
    }
}
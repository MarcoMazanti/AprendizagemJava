package org.example.BatalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    int i = 0, j = 0;

    public void batalhaNaval() {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);

        String direcao;
        int linha = 0, coluna = 0, tipo;
        int[][] jogador = new int[5][10];
        int[][] bot = new int[5][10];

        for(i = 0; i < 5; i++) {
            for(j = 0; j < 10; j++) {
                jogador[i][j] = 0;
                bot[i][j] = 0;
            }
        }


        System.out.println("Será jogado contra um bot.");
        leitura(jogador);

        System.out.println("TIPOS DE NAVIOS");
        System.out.println("1 - Navio pequeno <>");
        System.out.println("2 - Navio médio <=>");
        System.out.println("3 - Navio grande <==>");
        System.out.println("OBS: na hora de colocar um navio, será posto pela ponta, voltada para baixo ou para a direita.");
        System.out.print("Digite qual tipo irá utilizar: ");
        tipo = scan.nextInt();

        System.out.println("Digite a direção do navio (horizontal / vertical):");
        direcao = scan.next();
        direcao = direcao.toLowerCase();

        if(direcao.equals("horizontal")) {
            System.out.print("Linha (1 - 5): ");
            linha = scan.nextInt();

            System.out.printf("Coluna (%d - 10): ", 1 + tipo);
            coluna = scan.nextInt();
        } else if(direcao.equals("vertical")) {
            System.out.printf("Linha (%d - 5): ", 1 + tipo);
            linha = scan.nextInt();

            System.out.print("Coluna (1 - 10): ");
            coluna = scan.nextInt();
        }
        linha--;
        coluna--;

        if(direcao.equals("horizontal")) {
            if(tipo == 1) {
                if(coluna > 0) {
                    if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0) {
                        jogador[linha][coluna] = 3;
                        jogador[linha][coluna - 1] = 1;
                    }
                }
            } else if(tipo == 2) {
                if(coluna > 1) {
                    if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0 && jogador[linha][coluna - 2] == 0) {
                        jogador[linha][coluna] = 3;
                        jogador[linha][coluna - 1] = 2;
                        jogador[linha][coluna - 2] = 1;
                    }
                }
            } else if(tipo == 3) {
                if(coluna > 2) {
                    if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0 && jogador[linha][coluna - 2] == 0 && jogador[linha][coluna - 3] == 0) {
                        jogador[linha][coluna] = 3;
                        jogador[linha][coluna - 1] = 2;
                        jogador[linha][coluna - 2] = 2;
                        jogador[linha][coluna - 3] = 1;
                    }
                }
            }
        } else if(direcao.equals("vertical")) {
            if(tipo == 1) {
                if(linha > 0) {
                    if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0) {
                        jogador[linha][coluna] = 6;
                        jogador[linha - 1][coluna] = 4;
                    }
                }
            } else if(tipo == 2) {
                if(linha > 1) {
                    if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0 && jogador[linha - 2][coluna] == 0) {
                        jogador[linha][coluna] = 6;
                        jogador[linha - 1][coluna] = 5;
                        jogador[linha - 2][coluna] = 4;
                    }
                }
            } else if(tipo == 3) {
                if(linha > 2) {
                    if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0 && jogador[linha - 2][coluna] == 0 && jogador[linha - 3][coluna] == 0) {
                        jogador[linha][coluna] = 6;
                        jogador[linha - 1][coluna] = 5;
                        jogador[linha - 2][coluna] = 5;
                        jogador[linha - 3][coluna] = 4;
                    }
                }
            }
        }

        leitura(jogador);

    }

    private void leitura(int[][] mapa) {
        char caractere = ' ';

        System.out.println();
        for(i = 0; i < 7; i++) {
            for(j = 0; j < 12; j++) {
                if((i == 0 && j == 0) || (i == 0 && j == 11) || (i == 6 && j == 0) || (i == 6 && j == 11)) {
                    System.out.print("+");
                } else if(i == 0 || i == 6) {
                    System.out.print(" - ");
                } else if(j == 0 || j == 11) {
                    System.out.print("|");
                } else {
                    caractere = switch (mapa[i - 1][j - 1]) {
                        case 0 -> ' ';
                        case 1 -> '<';
                        case 2 -> '=';
                        case 3 -> '>';
                        case 4 -> '^';
                        case 5 -> '║';
                        case 6 -> 'v';
                        case 7 -> '¤';
                        case 8 -> '•';
                        case 11 -> '◄';
                        case 12 -> '▬';
                        case 13 -> '►';
                        case 14 -> '▲';
                        case 15 -> '█';
                        case 16 -> '▼';
                        default -> caractere;
                    };
                    System.out.printf(" %c ", caractere);
                }
            }
            System.out.println();
        }
        System.out.println();

        /*
        Sinais gerais
        0 - " "
        7 - "¤" alt + 0164 para acertos no barco
        8 - "•" alt + 0149 para acertos na água
        21 - "<>" barco pequeno
        22 - "<=>" barco médio
        23 - "<==>" barco grande

        barcos
        1 - "<"
        2 - "="
        3 - ">"
        4 - "^" alt + 94
        5 - "║" alt + 186
        6 - "v"

        barcos explodidos
        11 - "◄" alt + 17
        12 - "▬" alt + 22
        13 - "►" alt + 16
        14 - "▲" alt + 30
        15 - "█" alt + 219
        16 - "▼" alt + 31
         */
    }
}

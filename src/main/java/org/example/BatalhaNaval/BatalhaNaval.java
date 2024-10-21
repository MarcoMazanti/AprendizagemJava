package org.example.BatalhaNaval;

import java.util.Scanner;

public class BatalhaNaval {
    int i = 0, j = 0;

    public void batalhaNaval() {
        Scanner scan = new Scanner(System.in);

        String direcao;
        int linha, coluna, tipo, repetir = 0, quantNavioPequeno = 4, quantNavioMedio = 3, quantNavioGrande = 2;
        int[][] jogador = new int[5][10];

        for(i = 0; i < 5; i++) {
            for(j = 0; j < 10; j++) {
                jogador[i][j] = 0;
            }
        }

        leitura(jogador);

        //roda até a pessoa colocar todos os navios
        while(quantNavioPequeno > 0 || quantNavioMedio > 0 || quantNavioGrande > 0) {
            //verifica se a pessoa irá escrever o tipo de navio existente
            do {
                if(repetir == 1) {
                    System.out.printf("%nVocê digitou um tipo de navio inexistente! Tente novamente.%n");
                }
                repetir = 0;

                System.out.println("TIPOS DE NAVIOS");
                System.out.printf("1 - %dxNavio pequeno <>%n", quantNavioPequeno);
                System.out.printf("2 - %dxNavio médio <=>%n", quantNavioMedio);
                System.out.printf("3 - %dxNavio grande <==>%n", quantNavioGrande);
                System.out.println("OBS: na hora de colocar um navio, será posto pela ponta, voltada para baixo ou para a direita.");

                System.out.printf("%nDigite qual tipo irá utilizar: ");
                tipo = scan.nextInt();
                if(tipo < 1 || tipo > 3 || quantNavioPequeno == 0 && tipo == 1 || quantNavioMedio == 0 && tipo == 2 || quantNavioGrande == 0 && tipo == 3) {
                    repetir = 1;
                }
            } while(repetir == 1);

            //verifica se a pessoa vai escrever a direção corretamente
            do {
                if(repetir == 1) {
                    System.out.printf("%nVocê digitou uma direção inexistente! Tente novamente.%n");
                }
                repetir = 0;

                System.out.print("Digite a direção do navio (horizontal / vertical): ");
                direcao = scan.next();
                direcao = direcao.toLowerCase();

                if(!direcao.equals("horizontal") && !direcao.equals("vertical")) {
                    repetir = 1;
                }
            } while(repetir == 1);

            //verifica se a pessoa escolheu um espaço suficiente para colocar o navio
            do {
                if(repetir == 1) {
                    System.out.printf("%nNão houve espaço para colocar o navio! Tente novamente.%n");
                }
                repetir = 0;

                //verifica se a pessoa escolheu uma casa que esteja dentro dos parâmetros da matriz int[5][10] jogador
                do {
                    if(repetir == 1) {
                        System.out.printf("%nVocê digitou uma casa inexistente! Tente novamente.%n");
                    }
                    repetir = 0;

                    if(direcao.equals("horizontal")) {
                        System.out.print("Linha (1 - 5): ");
                        linha = scan.nextInt();

                        System.out.printf("Coluna (%d - 10): ", 1 + tipo);
                        coluna = scan.nextInt();

                        if(linha < 1 || linha > 5 || coluna < 1 + tipo || coluna > 10) {
                            repetir = 1;
                        }
                    } else {
                        System.out.printf("Linha (%d - 5): ", 1 + tipo);
                        linha = scan.nextInt();

                        System.out.print("Coluna (1 - 10): ");
                        coluna = scan.nextInt();

                        if(linha < 1 + tipo || linha > 5 || coluna < 1 || coluna > 10) {
                            repetir = 1;
                        }
                    }
                } while(repetir == 1);
                //transforma para poder utilizada diretamente na localização de um valor dentro da matriz
                linha--;
                coluna--;

                //verifica se será possivel preencher o local desejado
                if(direcao.equals("horizontal")) {
                    if(tipo == 1) {
                        if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0) {
                            jogador[linha][coluna] = 3;
                            jogador[linha][coluna - 1] = 1;
                            quantNavioPequeno--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 2 && coluna > 1) {
                        if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0 && jogador[linha][coluna - 2] == 0) {
                            jogador[linha][coluna] = 3;
                            jogador[linha][coluna - 1] = 2;
                            jogador[linha][coluna - 2] = 1;
                            quantNavioMedio--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 3 && coluna > 2) {
                        if(jogador[linha][coluna] == 0 && jogador[linha][coluna - 1] == 0 && jogador[linha][coluna - 2] == 0 && jogador[linha][coluna - 3] == 0) {
                            jogador[linha][coluna] = 3;
                            jogador[linha][coluna - 1] = 2;
                            jogador[linha][coluna - 2] = 2;
                            jogador[linha][coluna - 3] = 1;
                            quantNavioGrande--;
                        } else {
                            repetir = 1;
                        }
                    }
                } else {
                    if(tipo == 1) {
                        if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0) {
                            jogador[linha][coluna] = 6;
                            jogador[linha - 1][coluna] = 4;
                            quantNavioPequeno--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 2 && linha > 1) {
                        if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0 && jogador[linha - 2][coluna] == 0) {
                            jogador[linha][coluna] = 6;
                            jogador[linha - 1][coluna] = 5;
                            jogador[linha - 2][coluna] = 4;
                            quantNavioMedio--;
                        } else {
                            repetir = 1;
                        }
                    } else if(tipo == 3 && linha > 2) {
                        if(jogador[linha][coluna] == 0 && jogador[linha - 1][coluna] == 0 && jogador[linha - 2][coluna] == 0 && jogador[linha - 3][coluna] == 0) {
                            jogador[linha][coluna] = 6;
                            jogador[linha - 1][coluna] = 5;
                            jogador[linha - 2][coluna] = 5;
                            jogador[linha - 3][coluna] = 4;
                            quantNavioGrande--;
                        } else {
                            repetir = 1;
                        }
                    }
                }
            } while(repetir == 1);

            leitura(jogador);
        }
    }

    //passa a matriz int[][] mapa para a formatação que será apresentada na tela
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

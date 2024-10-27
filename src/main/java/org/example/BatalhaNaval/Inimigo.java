package org.example.BatalhaNaval;

import java.util.Random;

public class Inimigo {
    Random random = new Random();

    private int[][] inimigo = new int[5][10]; // local onde armazena os locais dos navios do inimigo
    private int[][] guerra = new int[5][10]; // local onde armazena os locais dos navios do jogador para conferir o acerto
    private int[][] proximo = new int[2][50]; // savará todos as casas selecionadas na dificuldade Dificil()
    private int[] ultimo = new int[2]; // salvará o último acerto para o modo médio
    private int repetir = 0, linha = 0, coluna = 0, pontuacao = 0, inicio = 1;

    public int[][] getInimigo() {
        return inimigo;
    }

    public int[][] getGuerra() {
        return guerra;
    }

    public void setGuerra(int[][] guerra) {
        this.guerra = guerra;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void Enemy(int dificuldade) {
        if(inicio == 1) {
            ultimo[0] = -1; // linha
            ultimo[1] = -1; // coluna

            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 50; j++) {
                    proximo[i][j] = -1;
                }
            }

            inicio = 0;
        }

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
    private void Facil() {
        // ele randomiza os lugares que vai selecionar, não tendo ordem assim
        do {
            repetir = 0;
            linha = random.nextInt(5);
            coluna = random.nextInt(10);

            if(guerra[linha][coluna] != 7 || guerra[linha][coluna] != 8) {
                Verificar(linha, coluna);
            } else {
                repetir = 1;
            }
        } while(repetir == 1);

    }

    private void Medio() {
        /*
         ele randomiza um local, mas ao achar um local com navio, ele vai rodar em volta procurando a outra
         parte até ir para os 4 sentidos, depois volta a randomizar
         */
        do {
            repetir = 0;

            if((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);

                Verificar(linha, coluna);
            } else {
                if(ultimo[0] > 0 && ultimo[1] > 0 && ultimo[0] < 4 && ultimo[1] < 4) {
                    if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                        Verificar(ultimo[0] - 1, ultimo[1]);
                    } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                        Verificar(ultimo[0], ultimo[1] - 1);
                    } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                        Verificar(ultimo[0], ultimo[1] + 1);
                    } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                        Verificar(ultimo[0] + 1, ultimo[1]);
                    } else {
                        ultimo[0] = -1;
                        ultimo[1] = -1;
                        repetir = 1;
                    }
                } else {
                    if(ultimo[0] == 0 && ultimo[1] == 0) { // canto superior esquerdo
                        if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[0] == 0 && ultimo[1] == 9) { // canto superior direito
                        if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[0] == 4 && ultimo[1] == 0) { // canto inferior esquerdo
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[0] == 4 && ultimo[1] == 9) { // canto inferior direito
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[0] == 0) { // linha superior
                        if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[0] == 4) { // linha inferior
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[1] == 0) { // coluna esquerda
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    } else if(ultimo[1] == 9) { // coluna direita
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ultimo[0] = -1;
                            ultimo[1] = -1;
                            repetir = 1;
                        }
                    }
                }
            }
        } while(repetir == 1);
    }

    private void Dificil() {
        /*
        ele randomiza um local, mas ao achar um local com navio, ele vai rodar em volta procurando a outra parte até ir para os 4 sentidos,
        após isso ele procura o ponto mais longe dos outros acertos e seleciona ele
         */

        do {
            repetir = 0;

            if((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);

                Verificar(linha, coluna);
            } else {
                if(ultimo[0] > 0 && ultimo[1] > 0 && ultimo[0] < 4 && ultimo[1] < 4) {
                    if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                        Verificar(ultimo[0] - 1, ultimo[1]);
                    } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                        Verificar(ultimo[0], ultimo[1] - 1);
                    } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                        Verificar(ultimo[0], ultimo[1] + 1);
                    } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                        Verificar(ultimo[0] + 1, ultimo[1]);
                    } else {
                        ProximcaCasa();
                    }
                } else {
                    if(ultimo[0] == 0 && ultimo[1] == 0) { // canto superior esquerdo
                        if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[0] == 0 && ultimo[1] == 9) { // canto superior direito
                        if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[0] == 4 && ultimo[1] == 0) { // canto inferior esquerdo
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[0] == 4 && ultimo[1] == 9) { // canto inferior direito
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[0] == 0) { // linha superior
                        if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[0] == 4) { // linha inferior
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[1] == 0) { // coluna esquerda
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] + 1] != 7 && guerra[ultimo[0]][ultimo[1] + 1] != 8) { // sentido leste
                            Verificar(ultimo[0], ultimo[1] + 1);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    } else if(ultimo[1] == 9) { // coluna direita
                        if(guerra[ultimo[0] - 1][ultimo[1]] != 7 && guerra[ultimo[0] - 1][ultimo[1]] != 8) { // sentido norte
                            Verificar(ultimo[0] - 1, ultimo[1]);
                        } else if(guerra[ultimo[0]][ultimo[1] - 1] != 7 && guerra[ultimo[0]][ultimo[1] - 1] != 8) { // sentido oeste
                            Verificar(ultimo[0], ultimo[1]);
                        } else if(guerra[ultimo[0] + 1][ultimo[1]] != 7 && guerra[ultimo[0] + 1][ultimo[1]] != 8) { // sentido sul
                            Verificar(ultimo[0] + 1, ultimo[1]);
                        } else {
                            ProximcaCasa();
                        }
                    }
                }
            }
        } while(repetir == 1);
    }

    private void ProximcaCasa() {
        // vai procurar o maior espaço livre e selecionará a casa localizada no centro dele
    }

    private void Verificar(int linha, int coluna) {
        if(guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if(guerra[linha][coluna] == 1 ||
                guerra[linha][coluna] == 2 ||
                guerra[linha][coluna] == 3 ||
                guerra[linha][coluna] == 4 ||
                guerra[linha][coluna] == 5 ||
                guerra[linha][coluna] == 6)
        {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao++;
        } else if(guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }
    }

    public void Criacao() {
        int linha, coluna, tipo, direcao, quantNavioPequeno = 4, quantNavioMedio = 3, quantNavioGrande = 2;

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
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

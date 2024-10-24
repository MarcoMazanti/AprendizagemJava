package org.example.BatalhaNaval;

import java.util.Random;

public class Inimigo {
    Random random = new Random();

    private int[][] inimigo = new int[5][10]; // local onde armazena os locais dos navios do inimigo
    private int[][] guerra = new int[5][10]; // local onde armazena os locais dos navios do jogador para conferir o acerto
    private int repetir = 0, linha = 0, coluna = 0, pontuacao = 0;

    public int[][] getInimigo() {
        return inimigo;
    }

    public void setInimigo(int[][] inimigo) {
        this.inimigo = inimigo;
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
                pontuacao++;
            } else if(guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
                repetir = 1;
            }
        } while(repetir == 1);

    }

    private void Medio() {
        // ele randomiza um local, mas ao achar um local com navio, ele vai rodar em volta procurando a outra parte até ir para os 4 sentidos, depois volta a randomizar
        do {
            repetir = 0;
            linha = random.nextInt(5);
            coluna = random.nextInt(10);


        } while(repetir == 1);
    }

    private void Dificil() {
        /*
        ele randomiza um local, mas ao achar um local com navio, ele vai rodar em volta procurando a outra parte até ir para os 4 sentidos,
        após isso ele procura o ponto mais longe dos outros acertos e seleciona ele
         */

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

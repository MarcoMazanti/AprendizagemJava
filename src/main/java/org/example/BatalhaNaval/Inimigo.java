package org.example.BatalhaNaval;

import java.util.Random;

abstract class ModelBot {
    public abstract void acertar();
    public abstract void verificar(int linha, int coluna);
    public abstract void criar();
}

class Bot extends ModelBot {
    Criacao criacao = new Criacao();
    Inimigo enemy = new Inimigo();
    Random random = new Random();

    protected int[][] inimigo = new int[5][10]; // Local para os navios do bot
    protected int[] ultimo = new int[2];
    protected int repetir = 0, pontuacao = 0, i, j, linha, coluna, inicio = 1;

    public int[][] getInimigo() {
        return inimigo;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public void acertar() {}

    @Override
    public void verificar(int linha, int coluna) {
        int[][] guerra = enemy.getGuerra();

        // entra mas não altera o valor no enemy.setGuerra(guerra);
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
            System.out.println("Entrou 8");
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            System.out.println("Entrou 7");
            pontuacao++;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }

        enemy.setGuerra(guerra);
    }

    @Override
    public void criar() {
        criacao.criar(inimigo);
    }

    public void inicio() {
        if (inicio == 1) {
            ultimo[0] = -1; // linha
            ultimo[1] = -1; // coluna
            inicio = 0;
        }
    }
}

class BotFacil extends Bot {
    public BotFacil() {
        acertar();
    }

    @Override
    public void acertar() {
        do {
            repetir = 0;
            linha = random.nextInt(5);
            coluna = random.nextInt(10);
            verificar(linha, coluna);
        } while (repetir == 1);
    }
}

class BotMedio extends Bot {
    int[][] guerra = enemy.getGuerra();
    public BotMedio() {
        acertar();
    }

    @Override
    public void acertar() {
        inicio();
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna);
            } else {
                if (ultimo[0] - 1 >= 0) {
                    verificar(ultimo[0] - 1, ultimo[1]);
                } else if (ultimo[1] - 1 >= 0) {
                    verificar(ultimo[0], ultimo[1] - 1);
                } else if (ultimo[1] + 1 <= 9) {
                    verificar(ultimo[0], ultimo[1] + 1);
                } else if (ultimo[0] + 1 <= 4) {
                    verificar(ultimo[0] + 1, ultimo[1]);
                } else {
                    ultimo[0] = -1;
                    ultimo[1] = -1;
                    repetir = 1;
                }
            }
        } while (repetir == 1);
    }

    @Override
    public void verificar(int linha, int coluna) {
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao++;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }

        enemy.setGuerra(guerra);
    }
}

class BotDificil extends Bot {
    int[][] guerra = enemy.getGuerra();

    public BotDificil() {
        acertar();
    }

    @Override
    public void acertar() {
        inicio();
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna);
            } else {
                if (ultimo[0] - 1 >= 0) {
                    verificar(ultimo[0] - 1, ultimo[1]);
                } else if (ultimo[1] - 1 >= 0) {
                    verificar(ultimo[0], ultimo[1] - 1);
                } else if (ultimo[1] + 1 <= 9) {
                    verificar(ultimo[0], ultimo[1] + 1);
                } else if (ultimo[0] + 1 <= 4) {
                    verificar(ultimo[0] + 1, ultimo[1]);
                } else {
                    proximaCasa();
                }
            }
        } while (repetir == 1);
    }

    private void proximaCasa() {
        // Implementação do método proximaCasa()
    }

    @Override
    public void verificar(int linha, int coluna) {
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao++;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }

        enemy.setGuerra(guerra);
    }
}

public class Inimigo {
    private int[][] guerra = new int[5][10];

    public int[][] getGuerra() {
        return guerra;
    }

    public void setGuerra(int[][] guerra) {
        this.guerra = guerra;
    }

    public void Enemy(int dificuldade) {
        Bot bot;

        switch (dificuldade) {
            case 1:
                bot = new BotFacil();
                break;
            case 2:
                bot = new BotMedio();
                break;
            case 3:
                bot = new BotDificil();
                break;
            default:
                throw new IllegalArgumentException("Dificuldade inválida");
        }

        bot.acertar();
    }
}

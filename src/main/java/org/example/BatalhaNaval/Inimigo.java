package org.example.BatalhaNaval;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModelBot {
    public abstract void acertar(int[][] guerra, int[] ultimo, AtomicInteger pontuacao);
    public abstract void verificar(int linha, int coluna, int[][] guerra, int[] ultimo, AtomicInteger pontuacao);
    public abstract void criar();
}

class Bot extends ModelBot {
    Criacao criacao = new Criacao();
    Random random = new Random();

    protected int[][] inimigo = new int[5][10]; // Local para os navios do bot
    protected int repetir = 0, linha, coluna, a = 0, b = 0, c = 0, d = 0;

    public int[][] getInimigo() {
        return inimigo;
    }

    @Override
    public void acertar(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {}

    @Override
    public void verificar(int linha, int coluna, int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        repetir = 0;

        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            pontuacao.incrementAndGet();
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }
    }

    @Override
    public void criar() {
        criacao.criar(inimigo);
    }
}

class BotFacil extends Bot {
    public BotFacil(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        acertar(guerra, ultimo, pontuacao);
    }

    @Override
    public void acertar(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        do {
            repetir = 0;
            linha = random.nextInt(5);
            coluna = random.nextInt(10);
            verificar(linha, coluna, guerra, ultimo, pontuacao);
        } while (repetir == 1);
    }
}

class BotMedio extends Bot {
    public BotMedio(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        acertar(guerra, ultimo, pontuacao);
    }

    @Override
    public void acertar(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna, guerra, ultimo, pontuacao);
            } else {
                if (ultimo[0] - 1 >= 0 && a == 0) {
                    verificar(ultimo[0] - 1, ultimo[1], guerra, ultimo, pontuacao);
                    a = 1;
                } else if (ultimo[1] - 1 >= 0 && b == 0) {
                    verificar(ultimo[0], ultimo[1] - 1, guerra, ultimo, pontuacao);
                    b = 1;
                } else if (ultimo[1] + 1 <= 9 && c == 0) {
                    verificar(ultimo[0], ultimo[1] + 1, guerra, ultimo, pontuacao);
                    c = 1;
                } else if (ultimo[0] + 1 <= 4 && d == 0) {
                    verificar(ultimo[0] + 1, ultimo[1], guerra, ultimo, pontuacao);
                    d = 1;
                } else {
                    ultimo[0] = -1;
                    ultimo[1] = -1;
                    repetir = 1;
                    a = 0;
                    b = 0;
                    c = 0;
                    d = 0;
                }
            }
        } while (repetir == 1);
    }

    @Override
    public void verificar(int linha, int coluna, int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao.incrementAndGet();
            a = 0;
            b = 0;
            c = 0;
            d = 0;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }
    }
}

class BotDificil extends Bot {
    public BotDificil(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        acertar(guerra, ultimo, pontuacao);
    }

    @Override
    public void acertar(int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna, guerra, ultimo, pontuacao);
            } else {
                if (ultimo[0] - 1 >= 0 && a == 0) {
                    verificar(ultimo[0] - 1, ultimo[1], guerra, ultimo, pontuacao);
                    a = 1;
                } else if (ultimo[1] - 1 >= 0 && b == 0) {
                    verificar(ultimo[0], ultimo[1] - 1, guerra, ultimo, pontuacao);
                    b = 1;
                } else if (ultimo[1] + 1 <= 9 && c == 0) {
                    verificar(ultimo[0], ultimo[1] + 1, guerra, ultimo, pontuacao);
                    c = 1;
                } else if (ultimo[0] + 1 <= 4 && d == 0) {
                    verificar(ultimo[0] + 1, ultimo[1], guerra, ultimo, pontuacao);
                    d = 1;
                } else {
                    proximaCasa();
                    a = 0;
                    b = 0;
                    c = 0;
                    d = 0;
                }
            }
        } while (repetir == 1);
    }

    private void proximaCasa() {
        // Implementação do método proximaCasa()
    }

    @Override
    public void verificar(int linha, int coluna, int[][] guerra, int[] ultimo, AtomicInteger pontuacao) {
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao.incrementAndGet();
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }
    }
}

public class Inimigo {
    private int[][] guerra = new int[5][10];
    private int[] ultimo = new int[2];
    private AtomicInteger pontuacao = new AtomicInteger(0); // AtomicInteger permite as outras classes pegarem o valor e alterar e ficar disponível para qualquer classe dps

    public int[][] getGuerra() {
        return guerra;
    }

    public void setGuerra(int[][] guerra) {
        this.guerra = guerra;
    }

    public int getPontuacao() {
        return pontuacao.get();
    }

    public void init() {
        ultimo[0] = -1;
        ultimo[1] = -1;
        pontuacao.set(0);
    }

    public void Enemy(int dificuldade) {
        switch (dificuldade) {
            case 1 -> new BotFacil(guerra, ultimo, pontuacao);
            case 2 -> new BotMedio(guerra, ultimo, pontuacao);
            case 3 -> new BotDificil(guerra, ultimo, pontuacao);
            default -> throw new IllegalArgumentException("Dificuldade inválida");
        }
    }
}

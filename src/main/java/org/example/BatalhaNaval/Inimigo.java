package org.example.BatalhaNaval;

import java.util.Random;

abstract class ModelBot {
    public abstract void acertar(int[][] gurerra);
    public abstract void verificar(int linha, int coluna, int[][] guerra);
    public abstract void criar();
}

class Bot extends ModelBot {
    Criacao criacao = new Criacao();
    Inimigo enemy = new Inimigo();
    Random random = new Random();

    protected int[][] inimigo = new int[5][10]; // Local para os navios do bot
    protected int[] ultimo = new int[2];
    protected int repetir = 0, pontuacao = 0, linha, coluna, inicio = 1, a = 0, b = 0, c = 0, d = 0;

    public int[][] getInimigo() {
        return inimigo;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public void acertar(int[][] guerra) {}

    @Override
    public void verificar(int linha, int coluna, int[][] guerra) {
        repetir = 0;

        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            pontuacao++;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
        }
    }

    @Override
    public void criar() {
        criacao.criar(inimigo);
        inicio();
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
    public BotFacil(int[][] guerra) {
        acertar(guerra);
    }

    @Override
    public void acertar(int[][] guerra) {
        do {
            repetir = 0;
            linha = random.nextInt(5);
            coluna = random.nextInt(10);
            verificar(linha, coluna, guerra);
        } while (repetir == 1);
    }
}

class BotMedio extends Bot {
    public BotMedio(int[][] guerra) {
        acertar(guerra);
    }

    @Override
    public void acertar(int[][] guerra) {
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna, guerra);
            } else {
                if (ultimo[0] - 1 >= 0 && a == 0) {
                    verificar(ultimo[0] - 1, ultimo[1], guerra);
                } else if (ultimo[1] - 1 >= 0 && b == 0) {
                    verificar(ultimo[0], ultimo[1] - 1, guerra);
                } else if (ultimo[1] + 1 <= 9 && c == 0) {
                    verificar(ultimo[0], ultimo[1] + 1, guerra);
                } else if (ultimo[0] + 1 <= 4 && d == 0) {
                    verificar(ultimo[0] + 1, ultimo[1], guerra);
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
    public void verificar(int linha, int coluna, int[][] guerra) {
        if (guerra[linha][coluna] == 0) {
            guerra[linha][coluna] = 8;
        } else if (guerra[linha][coluna] >= 1 && guerra[linha][coluna] <= 6) {
            guerra[linha][coluna] = 7;
            ultimo[0] = linha;
            ultimo[1] = coluna;
            pontuacao++;
            a = 0;
            b = 0;
            c = 0;
            d = 0;
        } else if (guerra[linha][coluna] == 7 || guerra[linha][coluna] == 8) {
            repetir = 1;
            System.out.println("Repetiu");
        }
        System.out.println(ultimo[0] + " " + ultimo[1]);
        System.out.println(a + " " + b + " " + c + " " + d);
    }
}

class BotDificil extends Bot {
    public BotDificil(int[][] guerra) {
        acertar(guerra);
    }

    @Override
    public void acertar(int[][] guerra) {
        do {
            repetir = 0;

            if ((ultimo[0] < 0 && ultimo[1] < 0) || (ultimo[0] > 4 && ultimo[1] > 9)) {
                linha = random.nextInt(5);
                coluna = random.nextInt(10);
                verificar(linha, coluna, guerra);
            } else {
                if (ultimo[0] - 1 >= 0) {
                    verificar(ultimo[0] - 1, ultimo[1], guerra);
                } else if (ultimo[1] - 1 >= 0) {
                    verificar(ultimo[0], ultimo[1] - 1, guerra);
                } else if (ultimo[1] + 1 <= 9) {
                    verificar(ultimo[0], ultimo[1] + 1, guerra);
                } else if (ultimo[0] + 1 <= 4) {
                    verificar(ultimo[0] + 1, ultimo[1], guerra);
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
    public void verificar(int linha, int coluna, int[][] guerra) {
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
        Bot bot = switch (dificuldade) {
            case 1 -> new BotFacil(guerra);
            case 2 -> new BotMedio(guerra);
            case 3 -> new BotDificil(guerra);
            default -> throw new IllegalArgumentException("Dificuldade inválida");
        };
    }
}

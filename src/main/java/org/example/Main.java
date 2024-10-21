package org.example;

import java.util.Scanner;
import org.example.JogoDaVelha.Jogo;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Jogo jogo = new Jogo();

        System.out.println("1 - Jogo da Velha");
        System.out.println("2 - Perguntas Aleatórias");
        System.out.println();

        System.out.print("Digite qual programa deseja executar: ");
        int programa = scan.nextInt();

        switch (programa) {
            case 1:
                jogo.jogoDaVelha();
                break;
            case 2:
                System.out.println("Desculpe ainda não foi desenvolvido.");
        }
    }
}
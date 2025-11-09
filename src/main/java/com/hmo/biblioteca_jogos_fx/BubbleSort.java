package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class BubbleSort {

    public static void ordenarPorTitulo(List<Jogo> jogos, int n) {
        if (n == 1) return;

        for (int i = 0; i < n - 1; i++) {
            if (jogos.get(i).getTitulo()
                    .compareToIgnoreCase(jogos.get(i + 1).getTitulo()) > 0) {

                Jogo temp = jogos.get(i);
                jogos.set(i, jogos.get(i + 1));
                jogos.set(i + 1, temp);
            }
        }
        ordenarPorTitulo(jogos, n - 1);
    }

    public static void ordenarPorTitulo(List<Jogo> jogos) {
        ordenarPorTitulo(jogos, jogos.size());
    }
}

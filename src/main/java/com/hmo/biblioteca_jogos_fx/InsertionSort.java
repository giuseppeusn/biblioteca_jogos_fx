package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class InsertionSort {

    private static void ordenarPorGeneroRecursivo(List<Jogo> jogos, int n) {
        if (n <= 1) return;

        ordenarPorGeneroRecursivo(jogos, n - 1);

        Jogo ultimo = jogos.get(n - 1);
        int j = n - 2;

        while (j >= 0 &&
                jogos.get(j).getGenero().compareToIgnoreCase(ultimo.getGenero()) > 0) {

            jogos.set(j + 1, jogos.get(j));
            j--;
        }

        jogos.set(j + 1, ultimo);
    }

    public static void ordenarPorGenero(List<Jogo> jogos) {
        ordenarPorGeneroRecursivo(jogos, jogos.size());
    }
}

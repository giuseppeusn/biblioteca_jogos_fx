package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class BubbleSort {
    public static void ordenarPorTitulo(List<Jogo> jogos, int n) {
        // se n == 1 a lista ordenada
        if (n == 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            String tituloAtual = jogos.get(i).getTitulo();
            String tituloProximo = jogos.get(i + 1).getTitulo();
            if (tituloAtual.compareToIgnoreCase(tituloProximo) > 0) {
                // Troca os elementos se estiverem fora de ordem
                Jogo temp = jogos.get(i);
                jogos.set(i, jogos.get(i + 1));
                jogos.set(i + 1, temp);
            }
        }
        // Chamada  para ordenar  primeiros elementos
        ordenarPorTitulo(jogos, n - 1);
    }
    //metodo auxiliar sem inserção do n manualmente
    public static void ordenarPorTitulo(List<Jogo> jogos) {
        ordenarPorTitulo(jogos, jogos.size());
    }
}



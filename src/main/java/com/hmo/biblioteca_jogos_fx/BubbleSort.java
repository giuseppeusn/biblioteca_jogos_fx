package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class BubbleSort {

    public static void ordenarPorTitulo(List<Jogo> jogos) {
        int n = jogos.size();
        boolean trocou;

        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                String tituloAtual = jogos.get(j).getTitulo();
                String tituloProximo = jogos.get(j + 1).getTitulo();

                if (tituloAtual.compareToIgnoreCase(tituloProximo) > 0) {
                    // Troca os elementos de posição caso estejam fora de ordem
                    Jogo temp = jogos.get(j);
                    jogos.set(j, jogos.get(j + 1));
                    jogos.set(j + 1, temp);
                    trocou = true; // Indica que houve troca
                }
            }

            // Se nenhuma troca ocorreu ele sai do loop
            if (!trocou) break;
        }
    }
}


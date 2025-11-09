package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class QuickSort {

    // Método público para ordenar os jogos por ano
    public void ordenarPorAnoLancamento(List<Jogo> jogos) {
        if (jogos == null || jogos.size() <= 1)
         return;

        quicksort(jogos, 0, jogos.size() - 1);
    }

    // Implementação recursiva do QuickSort
    private void quicksort(List<Jogo> jogos, int esquerda, int direita) {
        if (esquerda < direita) {
            int p = particao(jogos, esquerda, direita);
            quicksort(jogos, esquerda, p - 1);
            quicksort(jogos, p + 1, direita);
        }
    }

    // Método de partição
    private int particao(List<Jogo> jogos, int esquerda, int direita) {
        Jogo pivot = jogos.get(direita);
        int i = esquerda - 1;

        for (int j = esquerda; j < direita; j++) {
            if (jogos.get(j).getAnoLancamento() <= pivot.getAnoLancamento()) {
                i++;
                trocar(jogos, i, j);
            }
        }

        trocar(jogos, i + 1, direita);
        return i + 1;
    }

    // Método auxiliar para trocar dois elementos da lista
    private void trocar(List<Jogo> jogos, int i, int j) {
        Jogo temp = jogos.get(i);
        jogos.set(i, jogos.get(j));
        jogos.set(j, temp);
    }
}

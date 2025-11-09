package com.hmo.biblioteca_jogos_fx;

import java.util.List;

public class InsertionSort{
    private static void ordenarPorGeneroRecursivo(List<Jogo> jogos, int n) {
        // se restar apenas um elemento já está ordenado
        if (n <= 1) {
            return;
        }

        // ordena os n-1 primeiros elementos
        ordenarPorGeneroRecursivo(jogos, n - 1);

        //  insere o último elemento (posição n-1) na parte ordenada
        Jogo ultimo = jogos.get(n - 1);
        String generoUltimo = ultimo.getGenero();

        int j = n - 2;

        // Move elementos maiores uma posição à frente
        while (j >= 0 && jogos.get(j).getGenero().compareToIgnoreCase(generoUltimo) > 0) {
            jogos.set(j + 1, jogos.get(j));
            j--;
        }

        // Insere o último jogo na posição correta
        jogos.set(j + 1, ultimo);
    }

    //metodo auxiliar que usa apenas a lista como paramentro
    public static void ordenarPorGenero(List<Jogo> jogos) {
        ordenarPorGeneroRecursivo(jogos, jogos.size());
    }
}


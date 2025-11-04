package com.hmo.biblioteca_jogos_fx;

import java.util.ArrayList;
import java.util.List;


public class BibliotecaJogos {
    private static BibliotecaJogos instancia;
    private List<List<Jogo>> tabela;
    private int tamanho;
    private int capacidade;
    private static final double FATOR_CARGA_MAX = 0.75;

    public BibliotecaJogos(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.tabela = new ArrayList<>(capacidadeInicial);
        for (int i = 0; i < capacidadeInicial; i++) {
            tabela.add(new ArrayList<>());
        }
        this.tamanho = 0;
    }

    public List<List<Jogo>> getTabela() {
        return tabela;
    }

    public static BibliotecaJogos getInstancia() {
        if (instancia == null) {
            instancia = new BibliotecaJogos(4);
        }
        return instancia;
    }

    private int hash(String titulo) {
        return Math.abs(titulo.hashCode()) % capacidade;
    }

    public void inserir(Jogo jogo) {
        if (buscar(jogo.getTitulo()) != null) {
            System.out.println("Jogo já existe na tabela: " + jogo.getTitulo());
            return;
        }

        if ((double) tamanho / capacidade > FATOR_CARGA_MAX) {
            redimensionar();
        }

        int indice = hash(jogo.getTitulo());
        tabela.get(indice).add(jogo);
        tamanho++;
    }

    public void remover(String titulo) {
        int indice = hash(titulo);
        List<Jogo> lista = tabela.get(indice);

        for (Jogo j : lista) {
            if (j.getTitulo().equalsIgnoreCase(titulo)) {
                lista.remove(j);
                tamanho--;
                System.out.println("Jogo removido: " + titulo);
                return;
            }
        }
        System.out.println("Jogo não encontrado: " + titulo);
    }

    public Jogo buscar(String titulo) {
        int indice = hash(titulo);
        List<Jogo> lista = tabela.get(indice);

        for (Jogo j : lista) {
            if (j.getTitulo().equalsIgnoreCase(titulo)) {
                return j;
            }
        }
        return null;
    }

    private void redimensionar() {
        int novaCapacidade = capacidade * 2;
        System.out.println("🔄 Redimensionando tabela para " + novaCapacidade + " posições...");

        List<List<Jogo>> novaTabela = new ArrayList<>(novaCapacidade);
        for (int i = 0; i < novaCapacidade; i++) {
            novaTabela.add(new ArrayList<>());
        }

        // Reinsere todos os jogos na nova tabela
        for (List<Jogo> lista : tabela) {
            for (Jogo j : lista) {
                int novoIndice = Math.abs(j.getTitulo().hashCode()) % novaCapacidade;
                novaTabela.get(novoIndice).add(j);
            }
        }

        this.tabela = novaTabela;
        this.capacidade = novaCapacidade;
    }

    public void exibirTabela() {
        for (int i = 0; i < tabela.size(); i++) {
            System.out.print(i + ": ");
            for (Jogo j : tabela.get(i)) {
                System.out.print(j.getTitulo() + " -> ");
            }
            System.out.println();
        }
    }
}
package com.hmo.biblioteca_jogos_fx;

import java.util.Objects;

public class Jogo {
  private String titulo;
  private String capa;
  private String genero;
  private int anoLancamento;

  public Jogo(String titulo, String capa, String genero, int anoLancamento) {
    this.titulo = titulo;
    this.capa = capa;
    this.genero = genero;
    this.anoLancamento = anoLancamento;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getCapa() {
    return capa;
  }

  public String getGenero() {
    return genero;
  }

  public int getAnoLancamento() {
    return anoLancamento;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setCapa(String capa) {
    this.capa = capa;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public void setAnoLancamento(int anoLancamento) {
    this.anoLancamento = anoLancamento;
  }

  @Override
    public String toString() {
        return String.format("🎮 %s (%d) - Gênero: %s | Capa: %s", titulo, anoLancamento, genero, capa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(titulo.toLowerCase(), jogo.titulo.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase());
    }
}

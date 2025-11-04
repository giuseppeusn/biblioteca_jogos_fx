package com.hmo.biblioteca_jogos_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class UIController {

    @FXML private FlowPane flowJogos;
    @FXML private Button btnOrdenarTitulo;
    @FXML private Button btnOrdenarGenero;
    @FXML private Button btnOrdenarAno;
    @FXML private Button btnAdicionar;

    private static BibliotecaJogos bibliotecaJogos = BibliotecaJogos.getInstancia();

    @FXML
    public void initialize() {
        
        carregarJogosExemplo();
        exibirJogos();
    }

    private void carregarJogosExemplo() {
        bibliotecaJogos.inserir(new Jogo("The Witcher 3", getClass().getResource("/img/the-witcher-3.jpg").toExternalForm(), "RPG", 2015));
        bibliotecaJogos.inserir(new Jogo("Hollow Knight", getClass().getResource("/img/hollow-knight.jpg").toExternalForm(), "Metroidvania", 2017));
        bibliotecaJogos.inserir(new Jogo("God Of War", getClass().getResource("/img/god-of-war.jpg").toExternalForm(), "Ação", 2018));
        bibliotecaJogos.inserir(new Jogo("Resident Evil 4", getClass().getResource("/img/resident-evil-4.jpg").toExternalForm(), "Ação", 2005));
    }

    private void exibirJogos() {
        flowJogos.getChildren().clear();

        for (var lista : bibliotecaJogos.getTabela()) {
            for (Jogo j : lista) {
                VBox card = criarCardJogo(j);
                flowJogos.getChildren().add(card);
            }
        }
    }

    private VBox criarCardJogo(Jogo jogo) {
        ImageView capa = new ImageView(new Image(jogo.getCapa(), 267, 400, false, false));
        Label titulo = new Label(jogo.getTitulo());
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label ano = new Label(String.valueOf(jogo.getAnoLancamento()));

        VBox card = new VBox(5, capa, titulo, ano);
        card.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-border-color: #ccc; -fx-border-radius: 5;");
        return card;
    }

    @FXML
    private void abrirTelaCadastro() throws IOException {
        Stage stage = (Stage) btnAdicionar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/CadastroView.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    public void abrirTelaPrincipal() throws IOException {
        Stage stage = (Stage) btnAdicionar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
        stage.setScene(new Scene(root));
    }
}

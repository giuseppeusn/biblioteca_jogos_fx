package com.hmo.biblioteca_jogos_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

public class UIController {

    @FXML private FlowPane flowJogos;
    @FXML private Button btnOrdenarTitulo;
    @FXML private Button btnOrdenarGenero;
    @FXML private Button btnOrdenarAno;
    @FXML private Button btnAdicionar;
    @FXML private TextField campoBusca;

    private static BibliotecaJogos bibliotecaJogos = BibliotecaJogos.getInstancia();

    @FXML
    public void initialize() {
        carregarJogosExemplo();
        exibirJogos();

        // Adiciona listener para busca dinâmica
        campoBusca.textProperty().addListener((obs, antigo, novoValor) -> {
            if (novoValor == null || novoValor.trim().isEmpty()) {
                exibirJogos(); // Se campo vazio, mostra todos
            } else {
                exibirBusca(novoValor.trim());
            }
        });
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

    private void exibirBusca(String termo) {
        flowJogos.getChildren().clear();

        Jogo resultados = bibliotecaJogos.buscar(termo);

        if (resultados == null) {
            Label vazio = new Label("Nenhum jogo encontrado.");
            vazio.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
            flowJogos.getChildren().add(vazio);
        } else {
            VBox card = criarCardJogo(resultados);
            flowJogos.getChildren().add(card);
        }
    }

    private void removerJogo(Jogo jogo) {
        Alert confirmacao = new Alert(AlertType.CONFIRMATION);
        confirmacao.setTitle("Remover Jogo");
        confirmacao.setHeaderText("Deseja realmente remover \"" + jogo.getTitulo() + "\"?");
        confirmacao.setContentText("Essa ação não pode ser desfeita.");

        confirmacao.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                bibliotecaJogos.remover(jogo.getTitulo());
                exibirJogos();

                Alert alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Jogo Removido");
                alerta.setHeaderText(null);
                alerta.setContentText("O jogo \"" + jogo.getTitulo() + "\" foi removido com sucesso!");
                alerta.showAndWait();
            }
        });
    }

    private VBox criarCardJogo(Jogo jogo) {
        ImageView capa = new ImageView(new Image(jogo.getCapa(), 267, 400, false, false));
        Label titulo = new Label(jogo.getTitulo());
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label ano = new Label(String.valueOf(jogo.getAnoLancamento()));

        Button btnRemover = new Button("Remover");
        btnRemover.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        btnRemover.setOnAction(e -> removerJogo(jogo));

        VBox card = new VBox(5, capa, titulo, ano, btnRemover);
        card.setStyle("""
            -fx-padding: 10;
            -fx-alignment: center;
            -fx-border-color: #ccc;
            -fx-border-radius: 5;
            -fx-background-color: #f9f9f9;
            -fx-background-radius: 5;
        """);

        return card;
    }

    @FXML
    private void abrirTelaCadastro() throws IOException {
        Stage stage = (Stage) btnAdicionar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/CadastroView.fxml"));
        stage.setScene(new Scene(root, 900, 700));
    }

    @FXML
    public void abrirTelaPrincipal() throws IOException {
        Stage stage = (Stage) btnAdicionar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/UIView.fxml"));
        stage.setScene(new Scene(root, 900, 700));
    }

    @FXML
    private void ordenarPorTitulo() {}

    @FXML
    private void ordenarPorGenero() {}

    @FXML
    private void ordenarPorAno() {}
}

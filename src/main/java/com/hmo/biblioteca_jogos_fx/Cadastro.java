package com.hmo.biblioteca_jogos_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;

public class Cadastro {

    @FXML private TextField campoTitulo;
    @FXML private TextField campoGenero;
    @FXML private TextField campoAno;
    @FXML private ImageView previewImagem;

    private String caminhoImagemSelecionada;

    @FXML
    private void selecionarImagem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar imagem da capa");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );

        File arquivo = fileChooser.showOpenDialog(new Stage());
        if (arquivo != null) {
            caminhoImagemSelecionada = arquivo.toURI().toString();
            previewImagem.setImage(new Image(caminhoImagemSelecionada));
        }
    }

    @FXML
    private void salvarJogo() {
        try {
            String titulo = campoTitulo.getText();
            String genero = campoGenero.getText();
            int ano = Integer.parseInt(campoAno.getText());

            if (titulo.isEmpty() || genero.isEmpty() || caminhoImagemSelecionada == null) {
                throw new IllegalArgumentException("Preencha todos os campos e selecione uma imagem.");
            }

            Jogo novoJogo = new Jogo(titulo, caminhoImagemSelecionada, genero, ano);

            BibliotecaJogos.getInstancia().inserir(novoJogo);

            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("Jogo cadastrado com sucesso!");
            alerta.showAndWait();

            voltarTelaPrincipal();
        } catch (Exception e) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText("Erro ao salvar jogo");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    private void voltarTelaPrincipal() throws IOException {
        Stage stage = (Stage) campoTitulo.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/UIView.fxml"));
        stage.setScene(new Scene(root, 900, 700));
    }
}


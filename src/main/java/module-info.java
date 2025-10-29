module com.hmo.biblioteca_jogos_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.hmo.biblioteca_jogos_fx to javafx.fxml;
    exports com.hmo.biblioteca_jogos_fx;
}
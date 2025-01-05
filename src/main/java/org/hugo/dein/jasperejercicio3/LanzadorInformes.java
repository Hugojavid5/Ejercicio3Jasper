package org.hugo.dein.jasperejercicio3;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LanzadorInformes extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LanzadorInformes.class.getResource("/fxml/pantalla.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Supermercado!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
package org.hugo.dein.jasperejercicio3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que lanza la aplicación de informes.
 * Extiende de la clase {@link Application} para crear la ventana principal de la aplicación.
 */
public class LanzadorInformes extends Application {

    /**
     * Metodo que inicializa la ventana principal de la aplicación.
     * Carga el archivo FXML correspondiente y establece el título y la escena para el escenario.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LanzadorInformes.class.getResource("/fxml/pantalla.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Supermercado!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo principal que lanza la aplicación.
     * Inicia la ejecución del ciclo de vida de JavaFX.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}

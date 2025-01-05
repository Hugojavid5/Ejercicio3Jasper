package org.hugo.dein.jasperejercicio3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hugo.dein.jasperejercicio3.BBDD.ConexionBBDD;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class VentanaController {

    @FXML
    private Button btAgruparPorSeccion;

    @FXML
    private Button btGraficoProductos;

    @FXML
    private Button btListarEnTabla;

    @FXML
    private Button btListarProductos;

    @FXML
    void agrupadosPorSeccion(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/agrupadosPorSeccion.jasper", parameters);
    }

    @FXML
    void graficoProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/GraficoUnidades.jasper", parameters);
    }

    @FXML
    void listarEnTabla(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/tablaDeProductos.jasper", parameters);
    }

    @FXML
    void listarProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/listarProductos.jasper", parameters);
    }

    private void generarReporte(String reportePath, Map<String, Object> parameters) {
        try {
            ConexionBBDD db = new ConexionBBDD();
            InputStream reportStream = getClass().getResourceAsStream(reportePath);

            if (reportStream == null) {
                System.out.println("El archivo no está ahí: " + reportePath);
                return;
            }

            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            mostrarError("Error en la base de datos", "No se pudo conectar a la base de datos o generar el informe.");
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        // Crear una ventana emergente de tipo "error"
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // No queremos un encabezado
        alert.setContentText(mensaje); // El mensaje que queremos mostrar
        alert.showAndWait(); // Mostrar el mensaje y esperar a que el usuario lo cierre
    }

}
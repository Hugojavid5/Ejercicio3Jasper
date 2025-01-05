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

/**
 * Controlador de la ventana principal de la aplicación.
 * Este controlador gestiona la interacción con los botones de la interfaz gráfica y genera los informes usando JasperReports.
 */
public class VentanaController {

    @FXML
    private Button btAgruparPorSeccion;

    @FXML
    private Button btGraficoProductos;

    @FXML
    private Button btListarEnTabla;

    @FXML
    private Button btListarProductos;

    /**
     * Maneja el evento cuando se hace clic en el botón para generar el reporte agrupado por sección.
     * Crea los parámetros necesarios y llama al metodo {@link #generarReporte(String, Map)} para generar el informe.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void agrupadosPorSeccion(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/agrupadosPorSeccion.jasper", parameters);
    }

    /**
     * Maneja el evento cuando se hace clic en el botón para generar el gráfico de productos.
     * Crea los parámetros necesarios y llama al metodo {@link #generarReporte(String, Map)} para generar el informe.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void graficoProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/GraficoUnidades.jasper", parameters);
    }

    /**
     * Maneja el evento cuando se hace clic en el botón para generar el reporte de productos en una tabla.
     * Crea los parámetros necesarios y llama al metodo {@link #generarReporte(String, Map)} para generar el informe.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void listarEnTabla(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/tablaDeProductos.jasper", parameters);
    }

    /**
     * Maneja el evento cuando se hace clic en el botón para listar productos.
     * Crea los parámetros necesarios y llama al metodo {@link #generarReporte(String, Map)} para generar el informe.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void listarProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
        generarReporte("/Jasper/listarProductos.jasper", parameters);
    }

    /**
     * Genera un reporte utilizando JasperReports.
     * Carga el archivo del reporte, llena el reporte con los parámetros y muestra el informe en una ventana de vista previa.
     *
     * @param reportePath La ruta al archivo Jasper (.jasper) que contiene el reporte.
     * @param parameters Los parámetros que se deben pasar al reporte.
     */
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

    /**
     * Muestra una ventana emergente de error con el título y el mensaje proporcionados.
     *
     * @param titulo El título de la ventana emergente.
     * @param mensaje El mensaje que se muestra en la ventana emergente.
     */
    private void mostrarError(String titulo, String mensaje) {
        // Crear una ventana emergente de tipo "error"
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // No queremos un encabezado
        alert.setContentText(mensaje); // El mensaje que queremos mostrar
        alert.showAndWait(); // Mostrar el mensaje y esperar a que el usuario lo cierre
    }

}

package org.hugo.dein.jasperejercicio3.BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de gestionar la conexión con la base de datos.
 * Proporciona métodos para establecer y cerrar una conexión a la base de datos
 * utilizando los parámetros almacenados en un archivo de propiedades.
 */
public class ConexionBBDD {

    private static Connection connection;

    /**
     * Constructor que establece una conexión con la base de datos.
     * Carga los parámetros de conexión desde un archivo de propiedades
     * y configura la conexión utilizando {@link DriverManager}.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
    public ConexionBBDD() throws SQLException {
        Properties connConfig = loadProperties();
        String url = connConfig.getProperty("dburl");
        connection = DriverManager.getConnection(url, connConfig);
        connection.setAutoCommit(true);
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return La conexión actual a la base de datos.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Cierra la conexión con la base de datos, si está abierta.
     * Asigna {@code null} a la conexión después de cerrarla.
     *
     * @return {@code null} si la conexión ha sido cerrada correctamente, o la conexión
     *         si no se pudo cerrar.
     * @throws SQLException Si ocurre un error al intentar cerrar la conexión.
     */
    public Connection closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null; // Se asigna null después de cerrar
        }
        return connection;
    }

    /**
     * Carga las propiedades de configuración desde un archivo de propiedades.
     * El archivo debe contener parámetros de conexión para la base de datos.
     *
     * @return Un objeto {@link Properties} con los parámetros de configuración.
     * @throws RuntimeException Si ocurre un error al cargar el archivo de propiedades.
     */
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("configuration.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
    }
}

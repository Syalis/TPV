package montes.gutierrez.borja.TPV.dao.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona las conexiones con la BBDD.
 *
 * @author Borja Montes Gutiérrez
 */
public class ConnectionFactory {

    /**
     * Variables
     */
    private static String url;
    private static String username;
    private static String password;
    private static ConnectionFactory factory;

    /**
     * Método para conectarse con la BBDD.
     *
     * @throws InstantiationException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     * @throws IllegalAccessException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     * @throws ClassNotFoundException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     * @throws SQLException Excepción lanzada por posibles fallos en la
     * sentencia o servidor.
     */
    public ConnectionFactory() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.url = "jdbc:mysql://localhost:3306/tpv?autoReconnect=true&useSSL=false";
        this.username = "root";
        this.password = "root";
    }

    /**
     * Método para abrir la conexión con la BBDD.
     *
     * @return DriverManager con la URL de conexión.
     * @throws SQLException Excepción lanzada por posibles fallos en la
     * sentencia o servidor.
     * @throws InstantiationException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     * @throws IllegalAccessException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     * @throws ClassNotFoundException Excepción lanzada por posibles fallos en
     * la sentencia o servidor.
     */
    public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (factory == null) {
            factory = new ConnectionFactory();
        }
        return DriverManager.getConnection(factory.url, factory.username, factory.password);
    }

    /**
     * Método para cerrar la conexión con la BBDD.
     *
     * @param connection Connection de la BBDD que se cierra.
     */
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ;
        }
    }
}

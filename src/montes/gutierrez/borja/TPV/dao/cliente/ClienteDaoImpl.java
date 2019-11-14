package montes.gutierrez.borja.TPV.dao.cliente;

import montes.gutierrez.borja.TPV.dao.connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Cliente;

/**
 * Clase que define los métodos de acceso a la BBDD e implementa la interfaz
 * ClienteDao.
 *
 * @author Borja Montes Gutiérrez
 */
public class ClienteDaoImpl implements ClienteDao {

    /**
     * Método para guadar un cliente. Al realizar la operación recuperaremos la
     * clave y se la asigna una id.
     *
     * @param cliente Cliente que queremos insertar.
     */
    @Override
    public void insert(Cliente cliente) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el cliente en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "insert into cliente (dni, name, firstSurname, "
                    + "secondSurname, address, phoneNumber) "
                    + "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Sentencias SQL
            statement.setString(1, cliente.getDNI());
            statement.setString(2, cliente.getNameClient());
            statement.setString(3, cliente.getFirstSurname());
            statement.setString(4, cliente.getSecondSurname());
            statement.setString(5, cliente.getAddress());
            statement.setString(6, cliente.getPhoneNumber());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método para actualizar un cliente. Se actualiza todos los valores menos
     * el id.
     *
     * @param cliente Cliente que queremos actualizar.
     */
    @Override
    public void update(Cliente cliente) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el cliente en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "update cliente set dni = ?, name = ?, "
                    + "firstSurname = ?, secondSurname = ?, address = ?,"
                    + " phoneNumber = ?  where idClient = ?");
            //Sentencias SQL
            statement.setString(1, cliente.getDNI());
            statement.setString(2, cliente.getNameClient());
            statement.setString(3, cliente.getFirstSurname());
            statement.setString(4, cliente.getSecondSurname());
            statement.setString(5, cliente.getAddress());
            statement.setString(6, cliente.getPhoneNumber());
            statement.setInt(7, cliente.getIdClient());
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método que eliminar un cliente a partir de su ID.
     *
     * @param Id int identificador único del cliente a eliminar.
     */
    @Override
    public void delete(int Id) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para borrar un cliente de la BBDD
            PreparedStatement statement = connection.prepareStatement("delete from cliente where idClient=?");
            //Sentencias SQL
            statement.setInt(1, Id);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método para listar todos los clientes existentes en la BBDD
     *
     * @return ArrayList<Cliente> estructura con los clientes.
     */
    @Override
    public ArrayList<Cliente> findAllCliente() {
        ArrayList<Cliente> result = new ArrayList<>();
        //Valores que debemos de insertar para listar todos los clientes de la BBDD
        String sql = "select * from cliente";
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = connect(resultSet);
                result.add(cliente);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
        return result;
    }

    /**
     * Método para editar un cliente. Se edita todos los valores menos su ID.
     *
     * @param cliente Cliente que queremos editar.
     */
    @Override
    public void edit(Cliente cliente) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para editar un cliente de la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "edit into cliente (dni, name, firstSurname, "
                    + "secondSurname, address, phoneNumber) "
                    + "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Setencias SQL
            statement.setString(1, cliente.getDNI());
            statement.setString(2, cliente.getNameClient());
            statement.setString(3, cliente.getFirstSurname());
            statement.setString(4, cliente.getSecondSurname());
            statement.setString(5, cliente.getAddress());
            statement.setString(6, cliente.getPhoneNumber());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método que enlaza el ResultSet con el resultado de una consulta creando
     * un Cliente con los datos correspondientes.
     *
     * @param rs ResultSet de la consulta.
     * @return Cliente cliente con los datos cargados.
     * @throws SQLException Excepción lanzada por posibles fallos en la
     * sentencia o servidor.
     */
    private Cliente connect(ResultSet rs) throws SQLException {
        return new Cliente(rs.getInt("idClient"),rs.getString("DNI"), rs.getString("name"), rs.getString("firstSurname"),
                rs.getString("secondSurname"), rs.getString("address"), rs.getString ("phoneNumber"));
    }

}

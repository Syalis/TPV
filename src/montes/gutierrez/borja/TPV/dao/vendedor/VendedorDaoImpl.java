package montes.gutierrez.borja.TPV.dao.vendedor;

import montes.gutierrez.borja.TPV.dao.connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Vendedor;

/**
 * Clase que define los métodos de acceso a la BBDD e implementa la interfaz
 * VendedorDao.
 *
 * @author Borja Montes Gutiérrez
 */
public class VendedorDaoImpl implements VendedorDao {

    /**
     * Método para guadar un vendedor. Al realizar la operación recuperaremos la
     * clave y se la asigna una id.
     *
     * @param vendedor Vendedor que queremos insertar.
     */
    @Override
    public void insert(Vendedor vendedor) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el vendedor en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "insert into vendedor (nameSeller, firstSurname, "
                    + "secondSurname) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Sentencias SQL
            statement.setString(1, vendedor.getNameSeller());
            statement.setString(2, vendedor.getFirstSurname());
            statement.setString(3, vendedor.getSecondSurname());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                vendedor.setIdSeller(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método para actualizar un vendedor. Se actualiza todos los valores menos
     * el id.
     *
     * @param vendedor Vendedor que queremos actualizar.
     */
    @Override
    public void update(Vendedor vendedor) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el vendedor en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "update vendedor set nameSeller = ?, "
                    + "firstSurname = ?, secondSurname = ? where idSeller = ?");
            //Sentencias SQL
            statement.setString(1, vendedor.getNameSeller());
            statement.setString(2, vendedor.getFirstSurname());
            statement.setString(3, vendedor.getSecondSurname());
            statement.setInt(4, vendedor.getIdSeller());
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método que eliminar un vendedor a partir de su ID.
     *
     * @param Id int identificador único del vendedor a eliminar.
     */
    @Override
    public void delete(int Id) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para borrar un vendedor de la BBDD
            PreparedStatement statement = connection.prepareStatement("delete from vendedor where idSeller=?");
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
     * Método para listar todos los vendedors existentes en la BBDD
     *
     * @return ArrayList<Vendedor> estructura con los vendedores.
     */
    @Override
    public ArrayList<Vendedor> findAllVendedor() {
        ArrayList<Vendedor> result = new ArrayList<>();
        //Valores que debemos de insertar para listar todos los vendedores de la BBDD
        String sql = "select * from vendedor";
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vendedor vendedor = connect(resultSet);
                result.add(vendedor);
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
     * Método para editar un vendedor. Se edita todos los valores menos su ID.
     *
     * @param vendedor Vendedor que queremos editar.
     */
    @Override
    public void edit(Vendedor vendedor) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para editar un vendedor de la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "edit into vendedor (nameSeller, firstSurname, "
                    + "secondSurname) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Setencias SQL
            statement.setString(1, vendedor.getNameSeller());
            statement.setString(2, vendedor.getFirstSurname());
            statement.setString(3, vendedor.getSecondSurname());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                vendedor.setIdSeller(generatedKeys.getInt(1));
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
     * un Vendedor con los datos correspondientes.
     *
     * @param rs ResultSet de la consulta.
     * @return Vendedor vendedor con los datos cargados.
     * @throws SQLException Excepción lanzada por posibles fallos en la
     * sentencia o servidor.
     */
    private Vendedor connect(ResultSet rs) throws SQLException {
        return new Vendedor(rs.getInt("idSeller"), rs.getString("nameSeller"), rs.getString("firstSurname"),
                rs.getString("secondSurname"));
    }

}

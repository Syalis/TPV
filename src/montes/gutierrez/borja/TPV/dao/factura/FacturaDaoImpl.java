package montes.gutierrez.borja.TPV.dao.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import montes.gutierrez.borja.TPV.dao.connectionFactory.ConnectionFactory;
import montes.gutierrez.borja.TPV.model.Factura;

/**
 * Clase que define los métodos de acceso a la BBDD e implementa la interfaz
 * FacturaDao.
 *
 * @author Borja Montes Gutiérrez
 */
public class FacturaDaoImpl implements FacturaDao {

    /**
     * Método para guadar una factura. Al realizar la operación recuperaremos la
     * clave y se la asigna una id.
     *
     * @param factura Factura que queremos insertar.
     */
    @Override
    public void insert(Factura factura) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar la factura en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "insert into factura (dateBill, idClient, totalBill, "
                    + "discount, iva, totalAPay) values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Sentencias SQL
            statement.setString(1, factura.getDateBill());
            statement.setInt(2, factura.getIdClient());
            statement.setInt(3, factura.getTotalBill());
            statement.setFloat(4, factura.getDiscount());
            statement.setDouble(5, factura.getIva());
            statement.setInt(6, factura.getTotalAPay());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                factura.setCodeBill(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método para actualizar una factura. Se actualiza todos los valores menos
     * el id.
     *
     * @param factura Factura que queremos actualizar.
     */
    @Override
    public void update(Factura factura) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar la factura en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "update factura set dateBill = ?, idClient = ?, "
                    + "totalBill = ?, discount = ?, iva = ?, totalAPay = ?"
                    + " where codeBill = ?");
            //Sentencias SQL
            statement.setString(1, factura.getDateBill());
            statement.setInt(2, factura.getIdClient());
            statement.setInt(3, factura.getTotalBill());
            statement.setFloat(4, factura.getDiscount());
            statement.setDouble(5, factura.getIva());
            statement.setInt(6, factura.getTotalAPay());
            statement.setInt(7, factura.getCodeBill());
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método que eliminar una factura a partir de su ID.
     *
     * @param Id int identificador único de la factura a eliminar.
     */
    @Override
    public void delete(int Id) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para borrar una factura de la BBDD
            PreparedStatement statement = connection.prepareStatement("delete from factura where codeBill=?");
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
     * Método para listar todos los productos existentes en la BBDD
     *
     * @return ArrayList<Factura> estructura con las facturas.
     */
    @Override
    public ArrayList<Factura> findAllFactura() {
        ArrayList<Factura> result = new ArrayList<>();
        //Valores que debemos de insertar para listar todas las factura de la BBDD
        String sql = "select * from factura";
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Factura factura = connect(resultSet);
                result.add(factura);
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
     * Método para editar una factura. Se edita todos los valores menos su ID.
     *
     * @param factura Factura que queremos editar.
     */
    @Override
    public void edit(Factura factura) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para editar una factura de la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "edit into factura (dateBill, idClient, totalBill, "
                    + "discount, iva, totalAPay) values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Setencias SQL
            statement.setString(1, factura.getDateBill());
            statement.setInt(2, factura.getIdClient());
            statement.setInt(3, factura.getTotalBill());
            statement.setFloat(4, factura.getDiscount());
            statement.setDouble(5, factura.getIva());
            statement.setInt(6, factura.getTotalAPay());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                factura.setCodeBill(generatedKeys.getInt(1));
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
     * un Producto con los datos correspondientes.
     *
     * @param rs ResultSet de la consulta.
     * @return Producto producto con los datos cargados.
     * @throws SQLException Excepción lanzada por posibles fallos en la
     * sentencia o servidor.
     */
    private Factura connect(ResultSet rs) throws SQLException {
        return new Factura(rs.getInt("codeBill"),
                rs.getString("dateBill"), rs.getInt("idClient"),
                rs.getInt("totalBill"),
                rs.getInt("discount"), rs.getInt("iva"),rs.getInt("totalAPay"));
    }

    
    @Override
    public ArrayList<Factura> searchBillByKeyword(String keyWord) {
        ArrayList<Factura> result = new ArrayList<>();

        String sql = "select * from factura"
                + " where codeBill like '%"
                + keyWord.trim()
                + "%'";
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Factura factura = new Factura();
                factura.setCodeBill(resultSet.getInt("codeBill"));
                factura.setIdClient(resultSet.getInt("idClient"));
                factura.setTotalBill(resultSet.getInt("totalBill"));
                factura.setDiscount(resultSet.getFloat("discount"));
                factura.setIva(resultSet.getInt("iva"));
                factura.setTotalAPay(resultSet.getInt("totalAPay"));
                result.add(factura);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection);
        }

        return result;
    }
}

package montes.gutierrez.borja.TPV.dao.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import montes.gutierrez.borja.TPV.dao.connectionFactory.ConnectionFactory;
import montes.gutierrez.borja.TPV.model.Producto;

/**
 * Clase que define los métodos de acceso a la BBDD e implementa la interfaz
 * ProductoDao.
 *
 * @author Borja Montes Gutiérrez
 */
public class ProductoDaoImpl implements ProductoDao {

    /**
     * Método para guadar un producto. Al realizar la operación recuperaremos la
     * clave y se la asigna una id.
     *
     * @param producto Producto que queremos insertar.
     */
    @Override
    public void insert(Producto producto) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el producto en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "insert into producto (nameProduct, descriptionProduct, priceProduct, "
                    + "stockProduct) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Sentencias SQL
            statement.setString(1, producto.getNameProduct());
            statement.setString(2, producto.getDescriptionProduct());
            statement.setString(3, producto.getPriceProduct());
            statement.setString(4, producto.getStockProduct());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setCodeProduct(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método para actualizar un producto. Se actualiza todos los valores menos
     * el id.
     *
     * @param producto Producto que queremos actualizar.
     */
    @Override
    public void update(Producto producto) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para registar el producto en la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "update producto set nameProduct = ?, descriptionProduct = ?, "
                    + "priceProduct = ?, stockProduct = ? where codeProduct = ?");
            //Sentencias SQL
            statement.setString(1, producto.getNameProduct());
            statement.setString(2, producto.getDescriptionProduct());
            statement.setString(3, producto.getPriceProduct());
            statement.setString(4, producto.getStockProduct());
            statement.setInt(7, producto.getCodeProduct());
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Desconexión con la BBDD
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Método que eliminar un producto a partir de su ID.
     *
     * @param Id int identificador único del producto a eliminar.
     */
    @Override
    public void delete(int Id) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para borrar un producto de la BBDD
            PreparedStatement statement = connection.prepareStatement("delete from producto where codeProduct=?");
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
     * @return ArrayList<Producto> estructura con los productos.
     */
    @Override
    public ArrayList<Producto> findAllProducto() {
        ArrayList<Producto> result = new ArrayList<>();
        //Valores que debemos de insertar para listar todos los productos de la BBDD
        String sql = "select * from producto";
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Producto producto = connect(resultSet);
                result.add(producto);
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
     * Método para editar un producto. Se edita todos los valores menos su ID.
     *
     * @param producto Productos que queremos editar.
     */
    @Override
    public void edit(Producto producto) {
        Connection connection = null;
        try {
            //Conexión con la BBDD
            connection = ConnectionFactory.getConnection();
            //Valores que debemos de insertar para editar un producto de la BBDD
            PreparedStatement statement = connection.prepareStatement(
                    "edit into producto (nameProduct, descriptionProduct, priceProduct, "
                    + "stockProduct)values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Setencias SQL
            statement.setString(1, producto.getNameProduct());
            statement.setString(2, producto.getDescriptionProduct());
            statement.setString(3, producto.getPriceProduct());
            statement.setString(4, producto.getStockProduct());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setCodeProduct(generatedKeys.getInt(1));
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
    private Producto connect(ResultSet rs) throws SQLException {
        return new Producto(rs.getInt("codeProduct"),
                rs.getString("nameProduct"), rs.getString("descriptionProduct"),
                rs.getString("priceProduct"),
                rs.getString("stockProduct"));
    }

    @Override
    public ArrayList<Producto> searchProductsByKeyword(String keyWord) {
        ArrayList<Producto> result = new ArrayList<>();

        String sql = "select * from producto"
                + " where descriptionProduct like '%"
                + keyWord.trim()
                + "%'";
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodeProduct(resultSet.getInt("codeProduct"));
                producto.setNameProduct(resultSet.getString("nameProduct"));
                producto.setPriceProduct(resultSet.getString("priceProduct"));
                producto.setStockProduct(resultSet.getString("stockProduct"));
                result.add(producto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
        return result;
    }

}

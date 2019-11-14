package montes.gutierrez.borja.TPV.dao.producto;

import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Producto;

/**
 * Interfaz que define los métodos para hacer las peticiones a la BBDD.
 *
 * @author Borja Montes Gutiérrez
 */
public interface ProductoDao {

    /**
     * Método para guardar un producto pasado como parámetro.
     *
     * @param producto objeto de la clase Producto a guardar.
     */
    public void insert(Producto producto);

    /**
     * Método para actualizar un producto pasado como parámetro.
     *
     * @param producto objeto de la clase Producto a actualizar.
     */
    public void update(Producto producto);

    /**
     * Método para eliminar un producto a través de su ID.
     *
     * @param Id int identificador único del Producto a eliminar.
     */
    public void delete(int Id);

    /**
     * Método para listar todos los productos.
     *
     * @return ArrayList<Producto> array con los Producto.
     */
    public ArrayList<Producto> findAllProducto();

    /**
     * Método para editar un producto pasado como parámetro.
     *
     * @param producto objeto de la clase Producto a editar.
     */
    public void edit(Producto producto);
    
    /**
     * Método para buscar un producto pasado como parámetro.
     * 
     * @param keyWord parámero que se le pasa para buscar un producto
     * @return un producto
     */
    public ArrayList<Producto> searchProductsByKeyword(String keyWord);

}

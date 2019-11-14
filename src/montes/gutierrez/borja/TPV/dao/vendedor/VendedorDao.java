package montes.gutierrez.borja.TPV.dao.vendedor;

import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Vendedor;

/**
 * Interfaz que define los métodos para hacer las peticiones a la BBDD.
 *
 * @author Borja Montes Gutiérrez
 */
public interface VendedorDao {

    /**
     * Método para guardar un vendedor pasado como parámetro.
     *
     * @param vendedor objeto de la clase Vendedor a guardar.
     */
    public void insert(Vendedor vendedor);

    /**
     * Método para actualizar un vendedor pasado como parámetro.
     *
     * @param vendedor objeto de la clase Vendedor a actualizar.
     */
    public void update(Vendedor vendedor);

    /**
     * Método para eliminar un vendedor a través de su ID.
     *
     * @param Id int identificador único del vendedor a eliminar.
     */
    public void delete(int Id);

    /**
     * Método para listar todos los vendedores.
     *
     * @return ArrayList<Vendedor> array con los vendedores.
     */
    public ArrayList<Vendedor> findAllVendedor();

    /**
     * Método para editar un vendedor pasado como parámetro.
     *
     * @param vendedor objeto de la clase Vendedor a editar.
     */
    public void edit(Vendedor vendedor);

}

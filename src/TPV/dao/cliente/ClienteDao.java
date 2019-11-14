package montes.gutierrez.borja.TPV.dao.cliente;

import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Cliente;

/**
 * Interfaz que define los métodos para hacer las peticiones a la BBDD.
 *
 * @author Borja Montes Gutiérrez
 */
public interface ClienteDao {

    /**
     * Método para guardar un cliente pasado como parámetro.
     *
     * @param cliente objeto de la clase Cliente a guardar.
     */
    public void insert(Cliente cliente);

    /**
     * Método para actualizar un cliente pasado como parámetro.
     *
     * @param cliente objeto de la clase Cliente a actualizar.
     */
    public void update(Cliente cliente);

    /**
     * Método para eliminar un cliente a través de su ID.
     *
     * @param Id int identificador único del cliente a eliminar.
     */
    public void delete(int Id);

    /**
     * Método para listar todos los clientes.
     *
     * @return ArrayList<Cliente> array con los clientes.
     */
    public ArrayList<Cliente> findAllCliente();

    /**
     * Método para editar un cliente pasado como parámetro.
     *
     * @param cliente objeto de la clase Cliente a editar.
     */
    public void edit(Cliente cliente);

}

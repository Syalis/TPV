package montes.gutierrez.borja.TPV.dao.factura;

import java.util.ArrayList;
import montes.gutierrez.borja.TPV.model.Factura;

/**
 * Interfaz que define los métodos para hacer las peticiones a la BBDD.
 *
 * @author Borja Montes Gutiérrez
 */
public interface FacturaDao {

    /**
     * Método para guardar una factura pasado como parámetro.
     *
     * @param factura objeto de la clase Factura a guardar.
     */
    public void insert(Factura factura);

    /**
     * Método para actualizar una factura pasado como parámetro.
     *
     * @param factura objeto de la clase Factura a actualizar.
     */
    public void update(Factura factura);

    /**
     * Método para eliminar una factura a través de su ID.
     *
     * @param Id int identificador único del Factura a eliminar.
     */
    public void delete(int Id);

    /**
     * Método para listar todos las facturas.
     *
     * @return ArrayList<Factura> array con las facturas.
     */
    public ArrayList<Factura> findAllFactura();

    /**
     * Método para editar un factura pasado como parámetro.
     *
     * @param factura objeto de la clase Factura a editar.
     */
    public void edit(Factura factura);

    /**
     * Método para buscar una factura pasado como parámetro.
     *
     * @param keyWord parámero que se le pasa para buscar un producto
     * @return un producto
     */
    public ArrayList<Factura> searchBillByKeyword(String keyWord);

}

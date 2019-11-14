package montes.gutierrez.borja.TPV.acciones.productos;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import montes.gutierrez.borja.TPV.dao.producto.ProductoDaoImpl;
import montes.gutierrez.borja.TPV.model.Producto;
import montes.gutierrez.borja.TPV.vistas.productos.PanelBajaProducto;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de baja producto.
 *
 * @author Borja Montes Gutiérrez
 */
public class BajaProductos {

    /**
     * Variables
     */
    private PanelBajaProducto panel;
    private ProductoDaoImpl productoDao;

    /**
     * Constructor del controlador que recibe el panel de baja producto como
     * parámetro para poder acceder a los componentes y a los datos que el
     * usuario introduce en ellos.
     *
     * @param panel PanelBajaProducto panel con el formulario de baja.
     */
    public BajaProductos(PanelBajaProducto panel) {
        this.panel = panel;
        this.productoDao = new ProductoDaoImpl();
    }

    /**
     * Método que elimina el elemento seleccionado de la BBDD.
     */
    public void delete() {
        Producto producto = connect();
        productoDao.delete(producto.getCodeProduct());
        cargar();
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto producto.
     *
     * @return producto producto nuevo.
     */
    private Producto connect() {
        Producto producto = (Producto) panel.getLstProducts().getSelectedValue();
        return producto;
    }

    /**
     * Método que carga los productos desde la BBDD y los carga en el modelo de
     * la lista creado.
     */
    public void cargar() {
        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.findAllProducto();
        JList lista = panel.getLstProducts();
        DefaultListModel<Producto> model = new DefaultListModel<>();
        productos.stream().forEach((producto) -> {
            model.addElement(producto);
        });
        lista.setModel(model);
    }

}

package montes.gutierrez.borja.TPV.acciones.productos;

import montes.gutierrez.borja.TPV.dao.producto.ProductoDaoImpl;
import montes.gutierrez.borja.TPV.model.Producto;
import montes.gutierrez.borja.TPV.vistas.productos.PanelAltaProducto;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de alta producto.
 *
 * @author Borja Montes Gutiérrez
 */
public class AltaProductos {

    /**
     * Variables
     */
    private final PanelAltaProducto panel;
    private ProductoDaoImpl productoDao;

    /**
     * Constructor del controlador que recibe el panel de alta producto como
     * parámetro para poder acceder a los componentes y a los datos que el
     * usuario introduce en ellos.
     *
     * @param panel PanelAltaProducto con el formulario de alta.
     */
    public AltaProductos(PanelAltaProducto panel) {
        this.panel = panel;
        this.productoDao = new ProductoDaoImpl();
    }

    /**
     * Método para dar de alta a un producto. Enlazamos los valores en un nuevo
     * objeto producto y, a través de la clase de DAO, los almacenamos en la
     * BBDD.
     */
    public void save() {
        Producto producto = connect();
        productoDao.insert(producto);
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto producto.
     *
     * @return producto producto nuevo.
     */
    private Producto connect() {
        Producto producto = new Producto();
        producto.setNameProduct(panel.getTxtNameProduct().getText());
        producto.setDescriptionProduct(panel.getTxtDescriptionProduct().getText());
        producto.setPriceProduct(panel.getTxtPriceProduct().getText());
        producto.setStockProduct(panel.getTxtStockProduct().getText());
        return producto;
    }
}

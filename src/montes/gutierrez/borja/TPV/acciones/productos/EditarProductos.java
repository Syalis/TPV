package montes.gutierrez.borja.TPV.acciones.productos;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import montes.gutierrez.borja.TPV.dao.producto.ProductoDaoImpl;
import montes.gutierrez.borja.TPV.model.Producto;
import montes.gutierrez.borja.TPV.vistas.productos.PanelEditarProducto;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de editar producto.
 *
 * @author Borja Montes Gutiérrez
 */
public class EditarProductos {

    /**
     * Variables
     */
    private PanelEditarProducto panel;
    private ProductoDaoImpl productoDao;

    /**
     * Constructor del controlador que recibe el panel de editar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelEditarProducto con el formulario de editar.
     */
    public EditarProductos(PanelEditarProducto panel) {
        this.panel = panel;
        this.productoDao = new ProductoDaoImpl();
    }

    /**
     * Método que actualiza los datos de un producto. Enlazamos los valores en
     * un nuevo objeto producto y, a través de la clase de DAO, los actualizamos
     * en la BBDD.
     */
    public void update() {
        Producto producto = connect();
        productoDao.update(producto);
        cargar();

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

    /**
     * Método que carga todos los producto de la BBDD y los introduce en el
     * modelo de datos del JComboBox.
     */
    public void cargar() {
        panel.getCmbProducts().removeAllItems();
        ArrayList<Producto> productos = productoDao.findAllProducto();
        productos.stream().forEach((producto) -> {
            ((DefaultComboBoxModel<Producto>) panel.getCmbProducts().getModel()).
                    addElement(producto);
        });
    }

    /**
     * Método que resetea los valores introducidos en los campos del panel
     * PanelEditarProducto.
     */
    public void reset() {
        panel.getTxtNameProduct().setText("");
        panel.getTxtDescriptionProduct().setText("");
        panel.getTxtPriceProduct().setText("");
        panel.getTxtStockProduct().setText("");
    }
}

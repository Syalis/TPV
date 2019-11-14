package montes.gutierrez.borja.TPV.acciones.productos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import montes.gutierrez.borja.TPV.dao.producto.ProductoDaoImpl;
import montes.gutierrez.borja.TPV.model.Producto;
import montes.gutierrez.borja.TPV.vistas.productos.PanelListarProducto;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de listar producto.
 *
 * @author Borja Montes Gutiérrez
 */
public class ListarProductos {

    /**
     * Variables
     */
    private PanelListarProducto panel;
    private ProductoDaoImpl productoDao;

    /**
     * Constructor del controlador que recibe el panel de listar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelListarProducto con el formulario de listar.
     */
    public ListarProductos(PanelListarProducto panel) {
        this.panel = panel;
        this.productoDao = new ProductoDaoImpl();
    }

    /**
     * Método que carga todos los productos y los inserta en el modelo de datos
     * de la tabla existente en el panel.
     */
    public void listarProducto() {
        panel.getjTableProducts().setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Nombre", "Descripción", "Precio", "Stock"}));
        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.findAllProducto();
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) panel.getjTableProducts().getModel()).addRow(
                    producto.toObjectArray());
        });

    }

}

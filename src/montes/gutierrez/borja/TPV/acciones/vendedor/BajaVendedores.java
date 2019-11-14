package montes.gutierrez.borja.TPV.acciones.vendedor;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import montes.gutierrez.borja.TPV.dao.vendedor.VendedorDaoImpl;
import montes.gutierrez.borja.TPV.model.Vendedor;
import montes.gutierrez.borja.TPV.vistas.vendedor.PanelBajaVendedor;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de baja vendedor.
 *
 * @author Borja Montes Gutiérrez
 */
public class BajaVendedores {

    /**
     * Variables
     */
    private PanelBajaVendedor panel;
    private VendedorDaoImpl vendedorDao;

    /**
     * Constructor del controlador que recibe el panel de borrar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelBajaVendedor panel con el formulario de baja.
     */
    public BajaVendedores(PanelBajaVendedor panel) {
        this.panel = panel;
        this.vendedorDao = new VendedorDaoImpl();
    }

    /**
     * Método que elimina el elemento seleccionado de la BBDD.
     */
    public void delete() {
        Vendedor vendedor = connect();
        vendedorDao.delete(vendedor.getIdSeller());
        cargar();
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto vendedor.
     *
     * @return vendedor Vendedor nuevo.
     */
    private Vendedor connect() {
        Vendedor vendedor = (Vendedor) panel.getLstVendedores().getSelectedValue();
        return vendedor;
    }

    /**
     * Método que carga los vendedor desde la BBDD y los carga en el modelo de
     * la lista creado.
     */
    public void cargar() {
        ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) vendedorDao.findAllVendedor();
        JList lista = panel.getLstVendedores();
        DefaultListModel<Vendedor> model = new DefaultListModel<>();
        vendedores.stream().forEach((vendedor) -> {
            model.addElement(vendedor);
        });
        lista.setModel(model);
    }

}

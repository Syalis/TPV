package montes.gutierrez.borja.TPV.acciones.vendedor;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import montes.gutierrez.borja.TPV.dao.vendedor.VendedorDaoImpl;
import montes.gutierrez.borja.TPV.model.Vendedor;
import montes.gutierrez.borja.TPV.vistas.vendedor.PanelEditarVendedor;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de editar vendedor.
 *
 * @author Borja Montes Gutiérrez
 */
public class EditarVendedores {

    /**
     * Variables
     */
    private PanelEditarVendedor panel;
    private VendedorDaoImpl vendedorDao;

    /**
     * Constructor del controlador que recibe el panel de editar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelEditarCliente con el formulario de editar.
     */
    public EditarVendedores(PanelEditarVendedor panel) {
        this.panel = panel;
        this.vendedorDao = new VendedorDaoImpl();
    }

    /**
     * Método que actualiza los datos de un vendedor. Enlazamos los valores en
     * un nuevo objeto vendedor y, a través de la clase de DAO, los actualizamos
     * en la BBDD.
     */
    public void update() {
        Vendedor cliente = connect();
        vendedorDao.update(cliente);
        cargar();

    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto vendedor.
     *
     * @return cliente cliente nuevo.
     */
    private Vendedor connect() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNameSeller(panel.getTxtName().getText());
        vendedor.setFirstSurname(panel.getTxtFirstSurname().getText());
        vendedor.setSecondSurname(panel.getTxtSecondSurname().getText());
        return vendedor;
    }

    /**
     * Método que carga todos los vendedores de la BBDD y los introduce en el
     * modelo de datos del JComboBox.
     */
    public void cargar() {
        panel.getCmbClientes().removeAllItems();
        ArrayList<Vendedor> vendedores = vendedorDao.findAllVendedor();
        vendedores.stream().forEach((vendedor) -> {
            ((DefaultComboBoxModel<Vendedor>) panel.getCmbClientes().getModel()).
                    addElement(vendedor);
        });
    }

    /**
     * Método que resetea los valores introducidos en los campos del panel
     * EditarVendedor.
     */
    public void reset() {
        panel.getTxtName().setText("");
        panel.getTxtFirstSurname().setText("");
        panel.getTxtSecondSurname().setText("");
    }
}

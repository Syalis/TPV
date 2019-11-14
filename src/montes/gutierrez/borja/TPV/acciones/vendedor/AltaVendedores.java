package montes.gutierrez.borja.TPV.acciones.vendedor;

import montes.gutierrez.borja.TPV.dao.vendedor.VendedorDaoImpl;
import montes.gutierrez.borja.TPV.model.Vendedor;
import montes.gutierrez.borja.TPV.vistas.vendedor.PanelAltaVendedor;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de alta vendedor
 *
 * @author Borja Montes Gutiérrez
 */
public class AltaVendedores {

    /**
     * Variables
     */
    private final PanelAltaVendedor panel;
    private VendedorDaoImpl vendedorDao;

    /**
     * Constructor del controlador que recibe el panel de alta como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelAltaVendedor con el formulario de alta.
     */
    public AltaVendedores(PanelAltaVendedor panel) {
        this.panel = panel;
        this.vendedorDao = new VendedorDaoImpl();
    }

    /**
     * Método para dar de alta a un vendedor. Enlazamos los valores en un nuevo
     * objeto vendedor y, a través de la clase de DAO, los almacenamos en la
     * BBDD.
     */
    public void save() {
        Vendedor vendedor = connect();
        vendedorDao.insert(vendedor);
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto vendedor.
     *
     * @return vendedor Vendedor nuevo.
     */
    private Vendedor connect() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNameSeller(panel.getTxtName().getText());
        vendedor.setFirstSurname(panel.getTxtFirstSurname().getText());
        vendedor.setSecondSurname(panel.getTxtSecondSurname().getText());
        return vendedor;
    }

}

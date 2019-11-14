package montes.gutierrez.borja.TPV.acciones.clientes;

import montes.gutierrez.borja.TPV.dao.cliente.ClienteDaoImpl;
import montes.gutierrez.borja.TPV.model.Cliente;
import montes.gutierrez.borja.TPV.vistas.clientes.PanelAltaCliente;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de Alta Cliente
 *
 * @author Borja Montes Gutiérrez
 */
public class AltaClientes {

    /**
     * Variables
     */
    private final PanelAltaCliente panel;
    private ClienteDaoImpl clienteDao;

    /**
     * Constructor del controlador que recibe el panel de alta como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelAltaCliente con el formulario de alta.
     */
    public AltaClientes(PanelAltaCliente panel) {
        this.panel = panel;
        this.clienteDao = new ClienteDaoImpl();
    }

    /**
     * Método para dar de alta a un cliente. Enlazamos los valores en un nuevo
     * objeto cliente y, a través de la clase de DAO, los almacenamos en la
     * BBDD.
     */
    public void save() {
        Cliente cliente = connect();
        clienteDao.insert(cliente);
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto cliente.
     *
     * @return cliente cliente nuevo.
     */
    private Cliente connect() {
        Cliente cliente = new Cliente();
        cliente.setNameClient(panel.getTxtName().getText());
        cliente.setFirstSurname(panel.getTxtFirstSurname().getText());
        cliente.setSecondSurname(panel.getTxtSecondSurname().getText());
        cliente.setDNI(panel.getTxtDNI().getText());
        cliente.setAddress(panel.getTxtAddress().getText());
        cliente.setPhoneNumber(panel.getTxtPhoneNumber().getText());
        return cliente;
    }

}

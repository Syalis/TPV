package montes.gutierrez.borja.TPV.acciones.clientes;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import montes.gutierrez.borja.TPV.dao.cliente.ClienteDaoImpl;
import montes.gutierrez.borja.TPV.model.Cliente;
import montes.gutierrez.borja.TPV.vistas.clientes.PanelEditarCliente;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de editar cliente.
 *
 * @author Borja Montes Gutiérrez
 */
public class EditarClientes {

    /**
     * Variables
     */
    private PanelEditarCliente panel;
    private ClienteDaoImpl clienteDao;

    /**
     * Constructor del controlador que recibe el panel de editar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelEditarCliente con el formulario de editar.
     */
    public EditarClientes(PanelEditarCliente panel) {
        this.panel = panel;
        this.clienteDao = new ClienteDaoImpl();
    }

    /**
     * Método que actualiza los datos de un cliente. Enlazamos los valores en un
     * nuevo objeto cliente y, a través de la clase de DAO, los actualizamos en
     * la BBDD.
     */
    public void update() {
        Cliente cliente = connect();
        clienteDao.update(cliente);
        cargar();

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

    /**
     * Método que carga todos los clientes de la BBDD y los introduce en el
     * modelo de datos del JComboBox.
     */
    public void cargar() {
        panel.getCmbClientes().removeAllItems();
        ArrayList<Cliente> clientes = clienteDao.findAllCliente();
        clientes.stream().forEach((cliente) -> {
            ((DefaultComboBoxModel<Cliente>) panel.getCmbClientes().getModel()).
                    addElement(cliente);
        });
    }

    /**
     * Método que resetea los valores introducidos en los campos del panel
     * EditarCliente.
     */
    public void reset() {
        panel.getTxtName().setText("");
        panel.getTxtFirstSurname().setText("");
        panel.getTxtSecondSurname().setText("");
        panel.getTxtDNI().setText("");
        panel.getTxtAddress().setText("");
        panel.getTxtPhoneNumber().setText("");
    }
}

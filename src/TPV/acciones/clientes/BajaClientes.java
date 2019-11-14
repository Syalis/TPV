package montes.gutierrez.borja.TPV.acciones.clientes;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import montes.gutierrez.borja.TPV.dao.cliente.ClienteDaoImpl;
import montes.gutierrez.borja.TPV.model.Cliente;
import montes.gutierrez.borja.TPV.vistas.clientes.PanelBajaCliente;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de baja cliente.
 *
 * @author Borja Montes Gutiérrez
 */
public class BajaClientes {

    /**
     * Variables
     */
    private PanelBajaCliente panel;
    private ClienteDaoImpl clienteDao;

    /**
     * Constructor del controlador que recibe el panel de borrar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelBajaCliente panel con el formulario de baja.
     */
    public BajaClientes(PanelBajaCliente panel) {
        this.panel = panel;
        this.clienteDao = new ClienteDaoImpl();
    }

    /**
     * Método que elimina el elemento seleccionado de la BBDD.
     */
    public void delete() {
        Cliente cliente = connect();
        clienteDao.delete(cliente.getIdClient());
        cargar();
    }

    /**
     * Método que enlaza los valores introducidos en los campos del panel con
     * las propiedades del objeto cliente.
     *
     * @return cliente cliente nuevo.
     */
    private Cliente connect() {
        Cliente cliente = (Cliente) panel.getLstClientes().getSelectedValue();
        return cliente;
    }

    /**
     * Método que carga los clientes desde la BBDD y los carga en el modelo de
     * la lista creado.
     */
    public void cargar() {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDao.findAllCliente();
        JList lista = panel.getLstClientes();
        DefaultListModel<Cliente> model = new DefaultListModel<>();
        clientes.stream().forEach((cliente) -> {
            model.addElement(cliente);
        });
        lista.setModel(model);
    }

}

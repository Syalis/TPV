package montes.gutierrez.borja.TPV.acciones.clientes;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import montes.gutierrez.borja.TPV.dao.cliente.ClienteDaoImpl;
import montes.gutierrez.borja.TPV.model.Cliente;
import montes.gutierrez.borja.TPV.vistas.clientes.PanelListarCliente;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de listar cliente.
 *
 * @author Borja Montes Gutiérrez
 */
public class ListarClientes {

    /**
     * Variables
     */
    private PanelListarCliente panel;
    private ClienteDaoImpl clienteDao;

    /**
     * Constructor del controlador que recibe el panel de listar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelListarCliente con el formulario de listar.
     */
    public ListarClientes(PanelListarCliente panel) {
        this.panel = panel;
        this.clienteDao = new ClienteDaoImpl();
    }

    /**
     * Método que carga todos los clientes y los inserta en el modelo de datos
     * de la tabla existente en el panel.
     */
    public void listarCliente() {
        panel.getjTableClientes().setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "DNI", "Nombre", "Primer Apellido",
                    " Segundo Apellido", "Dirección", "Teléfono"}));
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDao.findAllCliente();
        clientes.stream().forEach((cliente) -> {
            ((DefaultTableModel) panel.getjTableClientes().getModel()).addRow(
                    cliente.toObjectArray());
        });

    }

}

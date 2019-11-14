package montes.gutierrez.borja.TPV.acciones.vendedor;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import montes.gutierrez.borja.TPV.dao.vendedor.VendedorDaoImpl;
import montes.gutierrez.borja.TPV.model.Vendedor;
import montes.gutierrez.borja.TPV.vistas.vendedor.PanelListarVendedor;

/**
 * Clase de la capa controlador. Atiende las peticiones realizadas sobre el
 * panel de listar vendedores.
 *
 * @author Borja Montes Gutiérrez
 */
public class ListarVendedores {

    /**
     * Variables
     */
    private PanelListarVendedor panel;
    private VendedorDaoImpl vendedorDao;

    /**
     * Constructor del controlador que recibe el panel de listar como parámetro
     * para poder acceder a los componentes y a los datos que el usuario
     * introduce en ellos.
     *
     * @param panel PanelListarCliente con el formulario de listar.
     */
    public ListarVendedores(PanelListarVendedor panel) {
        this.panel = panel;
        this.vendedorDao = new VendedorDaoImpl();
    }

    /**
     * Método que carga todos los vendedores y los inserta en el modelo de datos
     * de la tabla existente en el panel.
     */
    public void listarVendedor() {
        panel.getjTableVendedores().setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Nombre", "Primer Apellido", " Segundo Apellido"}));
        ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) vendedorDao.findAllVendedor();
        vendedores.stream().forEach((cliente) -> {
            ((DefaultTableModel) panel.getjTableVendedores().getModel()).addRow(
                    cliente.toObjectArray());
        });

    }

}

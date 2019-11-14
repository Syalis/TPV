package montes.gutierrez.borja.TPV.vistas.tpv;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import montes.gutierrez.borja.TPV.dao.cliente.ClienteDaoImpl;
import montes.gutierrez.borja.TPV.dao.factura.FacturaDaoImpl;
import montes.gutierrez.borja.TPV.dao.producto.ProductoDaoImpl;
import montes.gutierrez.borja.TPV.dao.vendedor.VendedorDaoImpl;
import montes.gutierrez.borja.TPV.model.Cliente;
import montes.gutierrez.borja.TPV.model.Factura;
import montes.gutierrez.borja.TPV.model.Producto;
import montes.gutierrez.borja.TPV.model.Vendedor;
import montes.gutierrez.borja.TPV.vistas.principalFrame.PrincipalFrame;

/**
 * Clase que define el panel TPV.
 *
 * @author Borja Montes Gutiérrez
 */
public final class Panel_TPV extends javax.swing.JPanel {

    /**
     * Variables
     */
    private PrincipalFrame frame;
    private ClienteDaoImpl clienteDao;
    private ProductoDaoImpl productoDao;
    private VendedorDaoImpl vendedorDao;
    private FacturaDaoImpl facturaDao;
    private DefaultTableModel modelCantidad;
    private DefaultTableModel modelDtoPrecio;
    private DefaultTableModel modelProducto;
    private DefaultTableModel modelSubTotal;
    private DefaultTableModel modelLeche;
    private DefaultTableModel model;

    /**
     * Constructor por defecto que inicializa los valores.
     *
     * @param frame principalFrame donde se va a dibujar el panel del TPV.
     */
    public Panel_TPV(PrincipalFrame frame) {
        this.frame = frame;
        initComponents();
        cargarClientesTPV();
        fechaTPV();
        cargarVendedoresTPV();
    }

    /**
     * Método para mostrar la fecha. Enlazamos la hora del sistema y la
     * insertamos en un JTextField en el formato: dd/mm/aaaa
     */
    public void fechaTPV() {
        Calendar fecha = new GregorianCalendar();
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);
        txtDate.setText(day + " / " + (month + 1) + " / " + year);
    }

    /**
     * Método que carga todos los clientes e insertarlos en un JComboBox.
     */
    public void cargarClientesTPV() {
        this.clienteDao = new ClienteDaoImpl();
        getCmbClientes().removeAllItems();
        ArrayList<Cliente> clientes = clienteDao.findAllCliente();
        clientes.stream().forEach((cliente) -> {
            ((DefaultComboBoxModel<Cliente>) getCmbClientes().getModel()).
                    addElement(cliente);
        });
    }

    /**
     * Método que carga todos los vendedores e insertarlos en un JComboBox.
     */
    public void cargarVendedoresTPV() {
        this.vendedorDao = new VendedorDaoImpl();
        getCmbSeller().removeAllItems();
        ArrayList<Vendedor> vendedores = vendedorDao.findAllVendedor();
        vendedores.stream().forEach((vendedor) -> {
            ((DefaultComboBoxModel<Vendedor>) getCmbSeller().getModel()).
                    addElement(vendedor);
        });
    }

    /**
     * Método para calcular el total de todos los productos listados. Calculamos
     * el total y lo insertamos en un JTextField.
     */
    private void Total() {
        double t = 0;
        double p = 0;
        if (jTableSubTotal.getRowCount() > 0) {
            for (int i = 0; i < jTableSubTotal.getRowCount(); i++) {
                p = Double.parseDouble(jTableSubTotal.getValueAt(i, 0).toString());
                t += p;
            }
            txtTotal.setText("" + t);

        }
    }

    /**
     * Método para borrar los productos listados.
     */
    public void borrarDatos() {

        modelCantidad = (DefaultTableModel) this.jTableCantidad.getModel();
        modelProducto = (DefaultTableModel) this.jTableProducto.getModel();
        modelDtoPrecio = (DefaultTableModel) this.jTableDtoPrecio.getModel();
        modelSubTotal = (DefaultTableModel) this.jTableSubTotal.getModel();
        modelLeche = (DefaultTableModel) this.jTableLeche.getModel();

        int fila1 = jTableCantidad.getSelectedRow();
        int fila2 = jTableProducto.getSelectedRow();
        int fila3 = jTableDtoPrecio.getSelectedRow();
        int fila4 = jTableSubTotal.getSelectedRow();
        int fila5 = jTableLeche.getSelectedRow();

        if (fila1 >= 0 && fila2 >= 0 && fila3 >= 0 && fila4 >= 0 && fila5 >= 0) {
            modelCantidad.removeRow(fila1);
            modelProducto.removeRow(fila2);
            modelDtoPrecio.removeRow(fila3);
            modelSubTotal.removeRow(fila4);
            modelLeche.removeRow(fila5);
            txtTotal.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Seleciona todos los campos de las tablas");
        }
    }

    /**
     * Getter del JComboBox con los clientes.
     *
     * @return cmbClientes.
     */
    public JComboBox getCmbClientes() {
        return cmbClientes;
    }

    /**
     * Getter del JComboBox con los vendedores.
     *
     * @return cmbSeller.
     */
    public JComboBox getCmbSeller() {
        return cmbSeller;
    }

    /**
     * Getter del JTextField del código del clientes.
     *
     * @return txtCodeClient.
     */
    public JTextField getTxtCodeClient() {
        return txtCodeClient;
    }

    /**
     * Getter del JTextField con el nombre de los clientes.
     *
     * @return txtNameClient.
     */
    public JTextField getTxtNameClient() {
        return txtNameClient;
    }

    /**
     * Getter del JTable con la cantidad de los productos.
     *
     * @return jTableCantidad.
     */
    public JTable getjTableCantidad() {
        return jTableCantidad;
    }

    /**
     * Getter del JTable con el Dto en los precios de los productos.
     *
     * @return jTableDtoPrecio.
     */
    public JTable getjTableDtoPrecio() {
        return jTableDtoPrecio;
    }

    /**
     * Getter del JTable con el productos.
     *
     * @return jTableProducto.
     */
    public JTable getjTableProducto() {
        return jTableProducto;
    }

    /**
     * Getter del JTable con el Sub Total de los productos.
     *
     * @return jTableDtoPrecio.
     */
    public JTable getjTableSubTotal() {
        return jTableSubTotal;
    }

    /**
     * Getter del JTable de la leche.
     *
     * @return jTableLeche.
     */
    public JTable getjTableLeche() {
        return jTableLeche;
    }

    /**
     * Getter del JTextField con la cantidad el precio total de todos los
     * productos listados.
     *
     * @return txtTotal.
     */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNTicket = new javax.swing.JLabel();
        txtNTicket = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        lblSeller = new javax.swing.JLabel();
        cmbSeller = new javax.swing.JComboBox<>();
        lblCodeClient = new javax.swing.JLabel();
        txtCodeClient = new javax.swing.JTextField();
        lblNameClient = new javax.swing.JLabel();
        txtNameClient = new javax.swing.JTextField();
        panelDescriptionProduct = new javax.swing.JScrollPane();
        jTableCantidad = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnCE = new javax.swing.JButton();
        btnPrice = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<>();
        btnCafeSolo = new javax.swing.JButton();
        btnCafeLargo = new javax.swing.JButton();
        btnCafeSemiLargo = new javax.swing.JButton();
        btnCafeSoloCorto = new javax.swing.JButton();
        btnCafeMitad = new javax.swing.JButton();
        btnCafeEntreCorto = new javax.swing.JButton();
        btnCafeCorto = new javax.swing.JButton();
        btnCafeSombra = new javax.swing.JButton();
        btnCafeNube = new javax.swing.JButton();
        panelDescriptionProduct1 = new javax.swing.JScrollPane();
        jTableProducto = new javax.swing.JTable();
        panelDescriptionProduct2 = new javax.swing.JScrollPane();
        jTableDtoPrecio = new javax.swing.JTable();
        panelDescriptionProduct3 = new javax.swing.JScrollPane();
        jTableSubTotal = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnC = new javax.swing.JButton();
        panelDescriptionProduct4 = new javax.swing.JScrollPane();
        jTableLeche = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(770, 700));

        lblNTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNTicket.setText("Nº Ticket");

        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("Fecha");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblSeller.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeller.setText("Seleccione vendedor:");

        lblCodeClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodeClient.setText("Código Cliente");

        txtCodeClient.setEditable(false);
        txtCodeClient.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblNameClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameClient.setText("Nombre Cliente");

        txtNameClient.setEditable(false);
        txtNameClient.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNameClient.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTableCantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableCantidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelDescriptionProduct.setViewportView(jTableCantidad);

        lblTotal.setText("TOTAL");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn0.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btnCE.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btnCE.setText("CE");
        btnCE.setMaximumSize(new java.awt.Dimension(39, 23));
        btnCE.setMinimumSize(new java.awt.Dimension(39, 23));
        btnCE.setPreferredSize(new java.awt.Dimension(45, 45));
        btnCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCEActionPerformed(evt);
            }
        });

        btnPrice.setText("Precio Total");
        btnPrice.setPreferredSize(new java.awt.Dimension(69, 23));
        btnPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPriceActionPerformed(evt);
            }
        });

        btnDelete.setText("Eliminar Producto");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        jLabel1.setText("Seleccione cliente:");

        cmbClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbClientesItemStateChanged(evt);
            }
        });

        btnCafeSolo.setBackground(new java.awt.Color(102, 102, 102));
        btnCafeSolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/solo.jpg"))); // NOI18N
        btnCafeSolo.setToolTipText("CAFÉ SOLO");
        btnCafeSolo.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeSoloActionPerformed(evt);
            }
        });

        btnCafeLargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/largo.jpg"))); // NOI18N
        btnCafeLargo.setToolTipText("CAFÉ LARGO");
        btnCafeLargo.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeLargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeLargoActionPerformed(evt);
            }
        });

        btnCafeSemiLargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/semiLargo.jpg"))); // NOI18N
        btnCafeSemiLargo.setToolTipText("CAFÉ SEMI LARGO");
        btnCafeSemiLargo.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeSemiLargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeSemiLargoActionPerformed(evt);
            }
        });

        btnCafeSoloCorto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/soloCorto.jpg"))); // NOI18N
        btnCafeSoloCorto.setToolTipText("CAFÉ SOLO CORTO");
        btnCafeSoloCorto.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeSoloCorto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeSoloCortoActionPerformed(evt);
            }
        });

        btnCafeMitad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/mitad.jpg"))); // NOI18N
        btnCafeMitad.setToolTipText("CAFÉ MITAD");
        btnCafeMitad.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeMitad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeMitadActionPerformed(evt);
            }
        });

        btnCafeEntreCorto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/entreCorto.jpg"))); // NOI18N
        btnCafeEntreCorto.setToolTipText("CAFÉ ENTRE CORTO");
        btnCafeEntreCorto.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeEntreCorto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeEntreCortoActionPerformed(evt);
            }
        });

        btnCafeCorto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/corto.jpg"))); // NOI18N
        btnCafeCorto.setToolTipText("CAFÉ CORTO");
        btnCafeCorto.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeCorto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeCortoActionPerformed(evt);
            }
        });

        btnCafeSombra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/sombra.jpg"))); // NOI18N
        btnCafeSombra.setToolTipText("CAFÉ SOMBRA");
        btnCafeSombra.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeSombra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeSombraActionPerformed(evt);
            }
        });

        btnCafeNube.setIcon(new javax.swing.ImageIcon(getClass().getResource("/montes/gutierrez/borja/TPV/imagenes/nube.jpg"))); // NOI18N
        btnCafeNube.setToolTipText("CAFÉ NUBE");
        btnCafeNube.setPreferredSize(new java.awt.Dimension(70, 70));
        btnCafeNube.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafeNubeActionPerformed(evt);
            }
        });

        jTableProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelDescriptionProduct1.setViewportView(jTableProducto);

        jTableDtoPrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableDtoPrecio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dto. Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelDescriptionProduct2.setViewportView(jTableDtoPrecio);

        jTableSubTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableSubTotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelDescriptionProduct3.setViewportView(jTableSubTotal);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("€");

        btnC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnC.setText("C");
        btnC.setPreferredSize(new java.awt.Dimension(45, 45));
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        jTableLeche.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableLeche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Con / Sin Leche"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelDescriptionProduct4.setViewportView(jTableLeche);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(lblSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(panelDescriptionProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(panelDescriptionProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(panelDescriptionProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(panelDescriptionProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(panelDescriptionProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCafeSolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeSoloCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCafeLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeMitad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeSombra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCafeSemiLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeEntreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCafeNube, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtCodeClient, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(lblCodeClient, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(lblNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblDate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblCodeClient))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblNameClient)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodeClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDescriptionProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDescriptionProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDescriptionProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDescriptionProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDescriptionProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCafeSolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeSoloCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCafeLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeMitad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeSombra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCafeSemiLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeEntreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCafeNube, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPriceActionPerformed
        Total();
    }//GEN-LAST:event_btnPriceActionPerformed

    private void cmbClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClientesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Cliente cliente = (Cliente) cmbClientes.getSelectedItem();
            txtCodeClient.setText("" + cliente.getIdClient());
            txtNameClient.setText(cliente.getNameClient() + " "
                    + cliente.getFirstSurname() + " "
                    + cliente.getSecondSurname());

        }
    }//GEN-LAST:event_cmbClientesItemStateChanged

    private void btnCafeSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeSoloActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe solo solo");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("1");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeSoloActionPerformed

    private void btnCafeLargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeLargoActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe largo");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("2");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeLargoActionPerformed

    private void btnCafeSemiLargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeSemiLargoActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe semi largo");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("3");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeSemiLargoActionPerformed

    private void btnCafeSoloCortoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeSoloCortoActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe solo corto");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("4");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeSoloCortoActionPerformed

    private void btnCafeMitadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeMitadActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe mitad");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("5");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeMitadActionPerformed

    private void btnCafeEntreCortoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeEntreCortoActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe entre corto");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("6");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeEntreCortoActionPerformed

    private void btnCafeCortoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeCortoActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe corto");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("7");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeCortoActionPerformed

    private void btnCafeSombraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeSombraActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe sombra");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("8");
        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeSombraActionPerformed

    private void btnCafeNubeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafeNubeActionPerformed
        this.productoDao = new ProductoDaoImpl();
        model = (DefaultTableModel) this.jTableCantidad.getModel();
        Object nuevo[] = {model.getRowCount() + 1, "", ""};
        model.addRow(nuevo);

        ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.searchProductsByKeyword("cafe nube");
        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableProducto.getModel()).addRow(
                    producto.toObjectArrayTPVNameProduct());
        });

        productos.stream().forEach((producto) -> {
            ((DefaultTableModel) jTableSubTotal.getModel()).addRow(
                    producto.toObjectArrayTPVPriceProduct());
        });

        this.facturaDao = new FacturaDaoImpl();
        ArrayList<Factura> facturas = (ArrayList<Factura>) facturaDao.searchBillByKeyword("9");

        facturas.stream().forEach((factura) -> {
            ((DefaultTableModel) jTableDtoPrecio.getModel()).addRow(
                    factura.toObjectArrayTPVFactura());
        });

        int resp = JOptionPane.showConfirmDialog(null, "¿CON LECHE?");
        if (JOptionPane.OK_OPTION == resp) {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"CON LECHE"};
            model.addRow(item);
        } else {
            model = (DefaultTableModel) this.jTableLeche.getModel();
            Object item[] = {"SIN LECHE"};
            model.addRow(item);
        }
    }//GEN-LAST:event_btnCafeNubeActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        borrarDatos();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        txtNTicket.setText(txtNTicket.getText() + "0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCEActionPerformed
        String codigo;
        codigo = txtNTicket.getText();
        if (codigo.length() > 0) {
            codigo = codigo.substring(0, codigo.length() - 1);
            txtNTicket.setText(codigo);
        }
    }//GEN-LAST:event_btnCEActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        txtNTicket.setText("");
    }//GEN-LAST:event_btnCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCE;
    private javax.swing.JButton btnCafeCorto;
    private javax.swing.JButton btnCafeEntreCorto;
    private javax.swing.JButton btnCafeLargo;
    private javax.swing.JButton btnCafeMitad;
    private javax.swing.JButton btnCafeNube;
    private javax.swing.JButton btnCafeSemiLargo;
    private javax.swing.JButton btnCafeSolo;
    private javax.swing.JButton btnCafeSoloCorto;
    private javax.swing.JButton btnCafeSombra;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrice;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JComboBox<String> cmbSeller;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable jTableCantidad;
    private javax.swing.JTable jTableDtoPrecio;
    private javax.swing.JTable jTableLeche;
    private javax.swing.JTable jTableProducto;
    private javax.swing.JTable jTableSubTotal;
    private javax.swing.JLabel lblCodeClient;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNTicket;
    private javax.swing.JLabel lblNameClient;
    private javax.swing.JLabel lblSeller;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JScrollPane panelDescriptionProduct;
    private javax.swing.JScrollPane panelDescriptionProduct1;
    private javax.swing.JScrollPane panelDescriptionProduct2;
    private javax.swing.JScrollPane panelDescriptionProduct3;
    private javax.swing.JScrollPane panelDescriptionProduct4;
    private javax.swing.JTextField txtCodeClient;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtNTicket;
    private javax.swing.JTextField txtNameClient;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

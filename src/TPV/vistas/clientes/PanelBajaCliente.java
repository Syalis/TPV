package montes.gutierrez.borja.TPV.vistas.clientes;

import montes.gutierrez.borja.TPV.vistas.principalFrame.PrincipalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import montes.gutierrez.borja.TPV.acciones.clientes.BajaClientes;

/**
 * Clase que define el panel para dar de baja clientes.
 *
 * @author Borja Montes Gutiérrez
 */
public class PanelBajaCliente extends javax.swing.JPanel {

    /**
     * Variable
     */
    private BajaClientes bajaClientes;

    /**
     * Constructor por defecto que inicializa los valores.
     *
     * @param frame PrincipalFrame donde se va a dibujar el panel de borrar
     * clientes.
     */
    public PanelBajaCliente(PrincipalFrame frame) {
        initComponents();
        bajaClientes = new BajaClientes(this);
        bajaClientes.cargar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        scrollPanel = new javax.swing.JScrollPane();
        lstClientes = new javax.swing.JList();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Panel Baja Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        setPreferredSize(new java.awt.Dimension(500, 400));

        btnEliminar.setText("Borrar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lstClientes.setPreferredSize(new java.awt.Dimension(400, 290));
        scrollPanel.setViewportView(lstClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        bajaClientes.delete();
        JOptionPane.showMessageDialog(this, "Cliente borrado");
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Getter de la lista (JList) de clientes.
     *
     * @return lstClientes.
     */
    public JList getLstClientes() {
        return lstClientes;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JList lstClientes;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}

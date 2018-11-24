/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Cliente;
import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author tingo
 */
public class frmEfetuarVenda extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmEfetuarVenda
     */
    public frmEfetuarVenda() {
        initComponents();
        this.setLocation(350, 20);
    }

    public void pesquisaDinamicaCliente() {
        List<Cliente> listaCliente = new ArrayList<>();
        ClienteDAO dao = new ClienteDAO();
        listaCliente = dao.read(txtPesquisaCliente.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaCliente.getText().isEmpty()) {
            listModel.clear();
            jList1.setModel(listModel);
            jList1.setVisible(false);
        } else {
            jList1.setVisible(true);
            for (Cliente percorrer : listaCliente) {
                listModel.addElement(percorrer.getIdcliente() + " - " + percorrer.toString());
            }
            jList1.setModel(listModel);
        }
    }
    
    public void selecionaCliente(){
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        String a = jList1.getSelectedValue();
        String id[] = a.split(" - ");
        int idCliente = Integer.parseInt(id[0]);
        cliente.setIdcliente(idCliente);

        txtIdCliente.setText(String.valueOf(cliente.getIdcliente()));
        cliente.setNome(dao.read(idCliente).getNome());
        
        jList1.setVisible(false);
        txtPesquisaCliente.setEnabled(false);
        txtPesquisaCliente.setText(cliente.getNome());
    }
    
    public void habilitaPesquisa(){
        txtPesquisaCliente.setEnabled(true);
        txtPesquisaCliente.setText("");
        txtIdCliente.setText("");
        txtPesquisaCliente.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPesquisaCliente = new javax.swing.JTextField();
        jList1 = new javax.swing.JList<>();
        txtIdCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setText("Cliente");

        txtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaClienteKeyReleased(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });

        txtIdCliente.setEnabled(false);

        jLabel2.setText("Id");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(txtIdCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 272, Short.MAX_VALUE))
                    .addComponent(jList1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jList1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaClienteKeyReleased
        pesquisaDinamicaCliente();
    }//GEN-LAST:event_txtPesquisaClienteKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        selecionaCliente();
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        habilitaPesquisa();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtPesquisaCliente;
    // End of variables declaration//GEN-END:variables
}

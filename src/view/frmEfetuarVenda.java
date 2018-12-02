package view;

import bean.Cliente;
import bean.ItensVenda;
import bean.Produto;
import bean.Venda;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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
        jListClientes.setVisible(false);
        jListaCupomFiscal.setVisible(false);
        dataHoje();
    }
    
    public final void dataHoje() {
        txtDataVenda.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataVenda.setDate(Calendar.getInstance().getTime());
        txtDataVenda.setEnabled(false);
    }

    public void pesquisaDinamicaCliente() {
        List<Cliente> listaCliente = new ArrayList<>();
        ClienteDAO dao = new ClienteDAO();
        listaCliente = dao.read(txtPesquisaCliente.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaCliente.getText().isEmpty()) {
            listModel.clear();
            jListClientes.setModel(listModel);
            jListClientes.setVisible(false);
        } else {
            jListClientes.setVisible(true);
            for (Cliente percorrer : listaCliente) {
                listModel.addElement(percorrer.getIdcliente() + " - " + percorrer.toString());
            }
            jListClientes.setModel(listModel);
        }
    }
    
    public void selecionaCliente(){
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        String a = jListClientes.getSelectedValue();
        String id[] = a.split(" - ");
        int idCliente = Integer.parseInt(id[0]);
        cliente.setIdcliente(idCliente);

        txtIdCliente.setText(String.valueOf(cliente.getIdcliente()));
        cliente.setNome(dao.read(idCliente).getNome());
        
        jListClientes.setVisible(false);
        txtPesquisaCliente.setEnabled(false);
        txtPesquisaCliente.setText(cliente.getNome());
    }
    
    public void novaVenda(){
        Venda v = new Venda();
        VendaDAO daov = new VendaDAO();
        ClienteDAO daoc = new ClienteDAO();
        v.setCliente(daoc.read(Integer.parseInt(txtIdCliente.getText())));
        v.setDatavenda(txtDataVenda.getDate());
        v.setIdcliente(v.getCliente().getIdcliente());
        int ultimaVenda = daov.create(v);
        txtNumeroVenda.setText(String.valueOf(ultimaVenda));
        txtPesquisaCliente.setEnabled(false);
        jButtonNovaVenda.setEnabled(false);
        jButtonLimpaPesquisaCliente.setEnabled(false);
    }
    
    public void inserirProduto(){
        
    }
    
    public void pesquisaDinamicaProduto() {
        List<Produto> listaProduto = new ArrayList<>();
        ProdutoDAO dao = new ProdutoDAO();
        listaProduto = dao.read(txtPesquisaProduto.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaProduto.getText().isEmpty()) {
            listModel.clear();
            jListaCupomFiscal.setModel(listModel);
            jListaCupomFiscal.setVisible(false);
        } else {
            jListaCupomFiscal.setVisible(true);
            for (Produto percorrer : listaProduto) {
                listModel.addElement(percorrer.getIdproduto()+ " - " + percorrer.toString());
            }
            jListaCupomFiscal.setModel(listModel);
        }
    }
    
    public void selecionaProduto(){
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        
        String a = jListaCupomFiscal.getSelectedValue();
        String id[] = a.split(" - ");
        int idProduto = Integer.parseInt(id[0]);

        produto.setIdproduto(idProduto);
        produto.setNome(dao.read(idProduto).getNome());
        produto.setPreco(dao.read(idProduto).getPreco());
        
        txtIdProduto.setText(String.valueOf(produto.getIdproduto()));
        txtPesquisaProduto.setEnabled(false);
        txtPesquisaProduto.setText(produto.getNome());
        txtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        
        jListaCupomFiscal.setVisible(false);
    }
    
    public void habilitaPesquisaCliente(){
        txtPesquisaCliente.setEnabled(true);
        txtPesquisaCliente.setText("");
        txtIdCliente.setText("");
        txtPesquisaCliente.requestFocus();
    }
    
    public void habilitaPesquisaProduto(){
        txtPesquisaProduto.setEnabled(true);
        txtPesquisaProduto.setText("");
        txtPrecoProduto.setText("");
        txtIdProduto.setText("");
        txtPesquisaProduto.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jButtonLimpaPesquisaCliente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jListClientes = new javax.swing.JList<>();
        txtPesquisaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtPesquisaProduto = new javax.swing.JTextField();
        jListaProdutos = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        txtPrecoProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        LimpaPesquisaProduto = new javax.swing.JButton();
        txtIdProduto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDataVenda = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jButtonNovaVenda = new javax.swing.JToggleButton();
        txtNumeroVenda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jListaCupomFiscal = new javax.swing.JList<>();
        jToggleButton1 = new javax.swing.JToggleButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);

        jLabel2.setText("Id");

        jLabel1.setText("Pesquisa Cliente");

        txtIdCliente.setEnabled(false);

        jButtonLimpaPesquisaCliente.setText("Limpar");
        jButtonLimpaPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpaPesquisaClienteActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jListClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListClientes.setDoubleBuffered(true);
        jListClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListClientesMouseClicked(evt);
            }
        });
        jPanel1.add(jListClientes, java.awt.BorderLayout.CENTER);

        txtPesquisaCliente.setMinimumSize(new java.awt.Dimension(6, 23));
        txtPesquisaCliente.setPreferredSize(new java.awt.Dimension(6, 23));
        txtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaClienteKeyReleased(evt);
            }
        });
        jPanel1.add(txtPesquisaCliente, java.awt.BorderLayout.PAGE_START);

        jLabel3.setText("Produto");

        jPanel2.setLayout(new java.awt.BorderLayout());

        txtPesquisaProduto.setMinimumSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.setPreferredSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaProdutoKeyReleased(evt);
            }
        });
        jPanel2.add(txtPesquisaProduto, java.awt.BorderLayout.PAGE_START);

        jListaProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListaProdutos.setDoubleBuffered(true);
        jListaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaProdutosMouseClicked(evt);
            }
        });
        jPanel2.add(jListaProdutos, java.awt.BorderLayout.CENTER);

        jLabel4.setText("Preço");

        txtPrecoProduto.setEnabled(false);

        jLabel5.setText("Quantidade");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        LimpaPesquisaProduto.setText("...");
        LimpaPesquisaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpaPesquisaProdutoActionPerformed(evt);
            }
        });

        txtIdProduto.setEnabled(false);

        jLabel6.setText("Id");

        jLabel7.setText("Data Venda");

        jButtonNovaVenda.setText("Nova Venda");
        jButtonNovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaVendaActionPerformed(evt);
            }
        });

        txtNumeroVenda.setEnabled(false);

        jLabel8.setText("Número da venda");

        jListaCupomFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListaCupomFiscal.setDoubleBuffered(true);
        jListaCupomFiscal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaCupomFiscalMouseClicked(evt);
            }
        });

        jToggleButton1.setText("Inserir");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonNovaVenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLimpaPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jListaCupomFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LimpaPesquisaProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jToggleButton1)
                                .addGap(12, 12, 12)))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonLimpaPesquisaCliente)
                                .addComponent(jButtonNovaVenda)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LimpaPesquisaProduto)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jListaCupomFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaProdutoKeyReleased
        pesquisaDinamicaProduto();
    }//GEN-LAST:event_txtPesquisaProdutoKeyReleased

    private void jListClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListClientesMouseClicked
        selecionaCliente();
    }//GEN-LAST:event_jListClientesMouseClicked

    private void jButtonLimpaPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpaPesquisaClienteActionPerformed
        habilitaPesquisaCliente();
    }//GEN-LAST:event_jButtonLimpaPesquisaClienteActionPerformed

    private void txtPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaClienteKeyReleased
        pesquisaDinamicaCliente();
    }//GEN-LAST:event_txtPesquisaClienteKeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void LimpaPesquisaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpaPesquisaProdutoActionPerformed
        habilitaPesquisaProduto();
    }//GEN-LAST:event_LimpaPesquisaProdutoActionPerformed

    private void jButtonNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaVendaActionPerformed
        if(txtPesquisaCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Erro", "Selecione o cliente", JOptionPane.ERROR_MESSAGE);
        }else{
            novaVenda();
        }
    }//GEN-LAST:event_jButtonNovaVendaActionPerformed

    private void jListaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaProdutosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jListaProdutosMouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(txtPrecoProduto.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Erro", "Digite a quantidade", JOptionPane.ERROR_MESSAGE);
        }else{
            inserirProduto();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jListaCupomFiscalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaCupomFiscalMouseClicked
        selecionaProduto();
    }//GEN-LAST:event_jListaCupomFiscalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LimpaPesquisaProduto;
    private javax.swing.JButton jButtonLimpaPesquisaCliente;
    private javax.swing.JToggleButton jButtonNovaVenda;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListClientes;
    private javax.swing.JList<String> jListaCupomFiscal;
    private javax.swing.JList<String> jListaProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private org.jdesktop.swingx.JXDatePicker txtDataVenda;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtNumeroVenda;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtPesquisaProduto;
    private javax.swing.JTextField txtPrecoProduto;
    // End of variables declaration//GEN-END:variables
}

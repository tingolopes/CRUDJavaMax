package view;

import bean.Cliente;
import bean.ItensVenda;
import bean.Produto;
import bean.Venda;
import dao.ClienteDAO;
import dao.ItensVendaDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import tablemodel.CupomFiscalTableModel;
import tablemodel.ItensVendaTableModel;

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
        jListaProdutos.setVisible(false);
        tblItensDaVenda.setVisible(false);
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

    public void selecionaCliente() {
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

    public void novaVenda() {
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

    public void inserirProduto() {
        ItensVenda iv = new ItensVenda();
        ItensVendaDAO dao = new ItensVendaDAO();
        ProdutoDAO daop = new ProdutoDAO();
        iv.setIdvenda(Integer.parseInt(txtNumeroVenda.getText()));
        iv.setProduto(daop.read(Integer.parseInt(txtIdProduto.getText())));
        iv.setQtdproduto(Integer.parseInt(txtQtdProd.getText()));
        dao.create(iv);
        listarItensDaVenda(iv.getIdvenda());
        habilitaPesquisaProduto();
    }

    public void listarItensDaVenda(Integer pesquisaPorId) {
        CupomFiscalTableModel modelo = new CupomFiscalTableModel();
        ItensVendaDAO dao = new ItensVendaDAO();
        modelo.setListaVenda(dao.read(String.valueOf(pesquisaPorId)));
        tblItensDaVenda.setModel(modelo);
        tblItensDaVenda.setVisible(true);
    }

    public void pesquisaDinamicaProduto() {
        List<Produto> listaProduto = new ArrayList<>();
        ProdutoDAO dao = new ProdutoDAO();
        listaProduto = dao.read(txtPesquisaProduto.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaProduto.getText().isEmpty()) {
            listModel.clear();
            jListaProdutos.setModel(listModel);
            jListaProdutos.setVisible(false);
        } else {
            jListaProdutos.setVisible(true);
            for (Produto percorrer : listaProduto) {
                listModel.addElement(percorrer.getIdproduto() + " - " + percorrer.toString());
            }
            jListaProdutos.setModel(listModel);
        }
    }

    public void selecionaProduto() {
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();

        String a = jListaProdutos.getSelectedValue();
        String id[] = a.split(" - ");
        int idProduto = Integer.parseInt(id[0]);

        produto.setIdproduto(idProduto);
        produto.setNome(dao.read(idProduto).getNome());
        produto.setPreco(dao.read(idProduto).getPreco());

        txtIdProduto.setText(String.valueOf(produto.getIdproduto()));
        txtPesquisaProduto.setEnabled(false);
        txtPesquisaProduto.setText(produto.getNome());
        txtPrecoProduto.setText(String.valueOf(produto.getPreco()));

        jListaProdutos.setVisible(false);
    }

    public void habilitaPesquisaCliente() {
        txtPesquisaCliente.setEnabled(true);
        txtPesquisaCliente.setText("");
        txtIdCliente.setText("");
        txtPesquisaCliente.requestFocus();
    }

    public void habilitaPesquisaProduto() {
        txtPesquisaProduto.setEnabled(true);
        txtPesquisaProduto.setText("");
        txtPrecoProduto.setText("");
        txtIdProduto.setText("");
        txtQtdProd.setText("");
        txtPesquisaProduto.requestFocus();
    }

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
        jLabel4 = new javax.swing.JLabel();
        txtPrecoProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQtdProd = new javax.swing.JTextField();
        LimpaPesquisaProduto = new javax.swing.JButton();
        txtIdProduto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDataVenda = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jButtonNovaVenda = new javax.swing.JToggleButton();
        txtNumeroVenda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButtonInserirItemVenda = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jListaProdutos = new javax.swing.JList<>();
        txtPesquisaProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItensDaVenda = new javax.swing.JTable();

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

        jLabel4.setText("Preço");

        txtPrecoProduto.setEnabled(false);

        jLabel5.setText("Quantidade");

        txtQtdProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdProdActionPerformed(evt);
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

        jButtonInserirItemVenda.setText("Inserir");
        jButtonInserirItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirItemVendaActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        jListaProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListaProdutos.setDoubleBuffered(true);
        jListaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaProdutosMouseClicked(evt);
            }
        });
        jPanel2.add(jListaProdutos, java.awt.BorderLayout.CENTER);

        txtPesquisaProduto.setMinimumSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.setPreferredSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaProdutoKeyReleased(evt);
            }
        });
        jPanel2.add(txtPesquisaProduto, java.awt.BorderLayout.PAGE_START);

        tblItensDaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblItensDaVenda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonNovaVenda)
                        .addGap(5, 5, 5)
                        .addComponent(jButtonLimpaPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(362, 362, 362)
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(LimpaPesquisaProduto)
                                .addGap(6, 6, 6)
                                .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtQtdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jButtonInserirItemVenda)))))
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLimpaPesquisaCliente)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNovaVenda))))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LimpaPesquisaProduto)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQtdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)
                        .addComponent(jButtonInserirItemVenda))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
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

    private void txtQtdProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdProdActionPerformed

    private void LimpaPesquisaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpaPesquisaProdutoActionPerformed
        habilitaPesquisaProduto();
    }//GEN-LAST:event_LimpaPesquisaProdutoActionPerformed

    private void jButtonNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaVendaActionPerformed
        if (txtPesquisaCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            novaVenda();
        }
    }//GEN-LAST:event_jButtonNovaVendaActionPerformed

    private void jListaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaProdutosMouseClicked
        selecionaProduto();
    }//GEN-LAST:event_jListaProdutosMouseClicked

    private void jButtonInserirItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirItemVendaActionPerformed
        if (txtQtdProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a quantidade", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtNumeroVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Clique em Nova Venda primeiro", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtPrecoProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pesquise e selecione um produto primeiro", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            inserirProduto();
        }
    }//GEN-LAST:event_jButtonInserirItemVendaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LimpaPesquisaProduto;
    private javax.swing.JToggleButton jButtonInserirItemVenda;
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
    private javax.swing.JList<String> jListaProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItensDaVenda;
    private org.jdesktop.swingx.JXDatePicker txtDataVenda;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtNumeroVenda;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtPesquisaProduto;
    private javax.swing.JTextField txtPrecoProduto;
    private javax.swing.JTextField txtQtdProd;
    // End of variables declaration//GEN-END:variables
}

package view;

import bean.Categoria;
import bean.Produto;
import connection.ConectaDB;
import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import tablemodel.ProdutoTableModel;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmProduto extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public frmProduto(Integer idadmin) {
        initComponents();
        txtIdAdmin.setText(String.valueOf(idadmin));
        this.setLocation(350, 20);
        conn = ConectaDB.conecta();
        listarProduto();
        populaJComboBox();
        dataHoje();
        
        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.NORTH);
        add(jPanel2);
        jPanel2.setLayout(new FlowLayout());
    }

    public final void dataHoje() {
        txtDataCadastro.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataCadastro.setDate(Calendar.getInstance().getTime());
    }

    public final void populaJComboBox() {
        cmbCategoria.removeAllItems();
        CategoriaDAO dao = new CategoriaDAO();
        cmbCategoria.addItem("Selecione...");
        dao.read().forEach((c) -> {
            cmbCategoria.addItem(c);
        });
    }

    public void cadastrarProduto() {
        Produto p = new Produto();
        ProdutoDAO dao = new ProdutoDAO();
        p.setNome(txtNome.getText());
        p.setPreco(Double.parseDouble(txtPreco.getText()));
        p.setDatacadastro(txtDataCadastro.getDate());
        p.setIdadmin(Integer.parseInt(txtIdAdmin.getText()));
        Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
        p.setIdcategoria(categoria.getIdcategoria());
        dao.create(p);
        
    }

    public final void listarProduto() {
        ProdutoTableModel modelo = new ProdutoTableModel();
        ProdutoDAO dao = new ProdutoDAO();
        modelo.setListaProduto(dao.read());
        tblProduto.setModel(modelo);
        ajustaTabela();
    }
    
    public void ajustaTabela() {
        //seta tamanho das colunas
        tblProduto.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblProduto.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblProduto.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblProduto.getColumnModel().getColumn(3).setPreferredWidth(250);

        //configura centralizaçao das colunas
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblProduto.getColumnModel().getColumn(0).setCellRenderer(centralizado);
    }

    public void editarProduto() {
        if (tblProduto.getSelectedRow() != -1) {
            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            p.setNome(txtNome.getText());
            p.setPreco(Double.parseDouble(txtPreco.getText()));
            p.setDatacadastro(txtDataCadastro.getDate());
            p.setIdadmin(Integer.parseInt(txtIdAdmin.getText()));

            if (cmbCategoria.getSelectedItem().equals("Selecione...")) {
                p.setIdcategoria(Integer.valueOf(txtIdCategoria.getText()));
                //System.out.println(" Manteve a opção "+cmbCategoria.getSelectedItem()+" e entrou no IF");
            } else {
                Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
                p.setIdcategoria(categoria.getIdcategoria());
                //System.out.println("Entrou no ELSE e setou o ID em "+p.getIdcategoria());
            }

            p.setIdproduto((int) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
            dao.update(p);
            limparCampos(0);
            pesquisarProduto();
            populaJComboBox();
        }
    }

    public void deletarProduto() {
        if (tblProduto.getSelectedRow() != -1) {
            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            p.setIdproduto((int) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
            dao.delete(p);
        }
        limparCampos();
    }

    public void pesquisarProduto() {
        ProdutoTableModel modelo = new ProdutoTableModel();
        ProdutoDAO dao = new ProdutoDAO();
        modelo.setListaProduto(dao.read(txtPesquisar.getText()));
        tblProduto.setModel(modelo);
        ajustaTabela();
    }

    public void selecionaProduto() {
        ProdutoDAO dao = new ProdutoDAO();
        int linhaSelecionada = tblProduto.getSelectedRow();
        int idProduto = (int) tblProduto.getModel().getValueAt(linhaSelecionada, 0);
        Produto p = dao.read(idProduto);
        txtIdProduto.setText(String.valueOf(p.getIdproduto()));
        txtNome.setText(p.getNome());
        txtPreco.setText(String.valueOf(p.getPreco()));
        txtIdCategoria.setText(String.valueOf(p.getIdcategoria()));
        txtDataCadastro.setDate(p.getDatacadastro());
    }

    public void limparCampos() {
        txtIdProduto.setText(null);
        txtNome.setText(null);
        txtPreco.setText(null);
        txtIdCategoria.setText(null);
        txtPesquisar.setText(null);
        listarProduto();
        populaJComboBox();
        dataHoje();
    }

    public void limparCampos(Integer all) {
        txtIdProduto.setText(null);
        txtNome.setText(null);
        txtPreco.setText(null);
        txtIdCategoria.setText(null);
        populaJComboBox();
        dataHoje();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        txtDataCadastro = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        txtIdAdmin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Cadastrar = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Produtos");
        setMinimumSize(new java.awt.Dimension(630, 610));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(630, 610));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por NOME ou CATEGORIA"));

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        tblProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProdutoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        jLabel1.setText("Cod. Produto");

        txtIdProduto.setEditable(false);

        jLabel2.setText("Nome");

        jLabel3.setText("Preço");

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });

        jLabel4.setText("Data Cadastro");

        txtIdAdmin.setEditable(false);

        jLabel5.setText("ID Admin");

        jLabel6.setText("Cod. Categoria");

        txtIdCategoria.setEditable(false);

        jLabel7.setText("Categoria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(142, 142, 142))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                    .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(cmbCategoria, 0, 493, Short.MAX_VALUE))
                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))))
                    .addContainerGap()))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdAdmin, txtIdCategoria, txtIdProduto});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 415, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addContainerGap()))
        );

        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        Limpar.setText("Limpar");
        Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cadastrar)
                    .addGap(18, 18, 18)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cadastrar, Editar, Excluir, Limpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Cadastrar)
                        .addComponent(Editar)
                        .addComponent(Excluir)
                        .addComponent(Limpar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cadastrar, Editar, Excluir, Limpar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        if (cmbCategoria.getSelectedItem().equals("Selecione...")) {
            JOptionPane.showMessageDialog(null, "Favor selecionar uma categoria");
        } else {
            if (txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Favor preencher o nome do produto");
            } else {
                if (txtPreco.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O preço não pode ficar em branco");
                } else {
                    if (!txtPreco.getText().matches("[0-9\\.]+")) {
                        JOptionPane.showMessageDialog(null, "O preço não pode conter letras, espaço ou vírgula");
                    } else {
                        try {
                            cadastrarProduto();
                            limparCampos();
                            listarProduto();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O preço só pode conter 1 (um) ponto");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_CadastrarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarProduto();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        selecionaProduto();
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_LimparActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor preencher o nome do produto");
        } else {
            if (txtPreco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O preço não pode ficar em branco");
            } else {
                if (!txtPreco.getText().matches("[0-9\\.]+")) {
                    JOptionPane.showMessageDialog(null, "O preço não pode conter letras, espaço ou vírgula");
                } else {
                    try {
                        editarProduto();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "O preço só pode conter 1 (um) ponto");
                    }
                }
            }
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        if (txtIdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor selecionar um produto");
        } else {
            deletarProduto();
        }
    }//GEN-LAST:event_ExcluirActionPerformed

    private void tblProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdutoKeyReleased
        selecionaProduto();
    }//GEN-LAST:event_tblProdutoKeyReleased

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton Limpar;
    private javax.swing.JComboBox<Object> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    private org.jdesktop.swingx.JXDatePicker txtDataCadastro;
    private javax.swing.JTextField txtIdAdmin;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}

package view;

import bean.Categoria;
import connection.ConectaDB;
import dao.CategoriaDAO;
import java.sql.*;
import javax.swing.JOptionPane;
import tablemodel.CategoriaTableModel;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmCategoria extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public frmCategoria() {
        initComponents();
        this.setLocation(400, 100);
        conn = ConectaDB.conecta();
        listarCategoria();
    }

    public void listarCategoria() {
        CategoriaTableModel modelo = new CategoriaTableModel();
        CategoriaDAO dao = new CategoriaDAO();
        modelo.setListaCategorias(dao.read());
        tblCategoria.setModel(modelo);
    }

    public void cadastrarCategoria() {
        Categoria c = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();

        c.setCategoria(txtNome.getText());
        c.setDescricao(txtDescricao.getText());

        dao.create(c);
        limparCampos();
        listarCategoria();
    }

    public void editarCategoria() {
        if (tblCategoria.getSelectedRow() != -1) {
            Categoria c = new Categoria();
            CategoriaDAO dao = new CategoriaDAO();
            c.setCategoria(txtNome.getText());
            c.setDescricao(txtDescricao.getText());
            c.setIdcategoria((int) (tblCategoria.getValueAt(tblCategoria.getSelectedRow(), 0)));
            dao.update(c);
        }
        limparCampos(0);
        pesquisarCategoria();
    }

    public void deletarCategoria() {
        if (tblCategoria.getSelectedRow() != -1) {
            Categoria c = new Categoria();
            CategoriaDAO dao = new CategoriaDAO();
            c.setIdcategoria((int) tblCategoria.getValueAt(tblCategoria.getSelectedRow(), 0));
            dao.delete(c);
        }
        listarCategoria();
    }

    public void pesquisarCategoria() {
        CategoriaTableModel modelo = new CategoriaTableModel();
        CategoriaDAO dao = new CategoriaDAO();
        modelo.setListaCategorias(dao.read(txtPesquisar.getText()));
        tblCategoria.setModel(modelo);
    }

    public void selecionaItem() {
        CategoriaDAO dao = new CategoriaDAO();
        int seleciona = tblCategoria.getSelectedRow();
        int idCategoria = (int) tblCategoria.getModel().getValueAt(seleciona, 0);
        Categoria categoria = dao.read(idCategoria);
        txtIdCategoria.setText(String.valueOf(categoria.getIdcategoria()));
        txtNome.setText(categoria.getCategoria());
        txtDescricao.setText(categoria.getDescricao());
    }

    public void limparCampos() {
        txtIdCategoria.setText(null);
        txtNome.setText(null);
        txtDescricao.setText(null);
        txtPesquisar.setText(null);
    }

    public void limparCampos(Integer all) {
        txtIdCategoria.setText(null);
        txtNome.setText(null);
        txtDescricao.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        Cadastrar = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Categorias");

        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });
        tblCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCategoriaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategoria);

        jLabel1.setText("Código");

        txtIdCategoria.setEditable(false);

        jLabel2.setText("Nome");

        jLabel3.setText("Descrição");

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por nome"));

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Cadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDescricao))))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cadastrar, Editar, Excluir, Limpar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar)
                    .addComponent(Editar)
                    .addComponent(Excluir)
                    .addComponent(Limpar))
                .addGap(83, 83, 83))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cadastrar, Editar, Excluir, Limpar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        if (txtNome.getText().isEmpty() || txtDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
        } else {
            cadastrarCategoria();
        }
    }//GEN-LAST:event_CadastrarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarCategoria();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMouseClicked
        selecionaItem();
    }//GEN-LAST:event_tblCategoriaMouseClicked

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_LimparActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        if (txtIdCategoria.getText().isEmpty() || txtNome.getText().isEmpty() || txtDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
        } else {
            editarCategoria();
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        if (txtIdCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor selecionar uma categoria");
        } else {
            deletarCategoria();
            limparCampos();
        }
    }//GEN-LAST:event_ExcluirActionPerformed

    private void tblCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCategoriaKeyReleased
        selecionaItem();
    }//GEN-LAST:event_tblCategoriaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton Limpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}

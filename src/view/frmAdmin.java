package view;

import bean.Admin;
import connection.ConectaDB;
import dao.AdminDAO;
import java.sql.*;
import javax.swing.JOptionPane;
import tablemodel.AdminTableModel;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmAdmin extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public frmAdmin() {
        initComponents();
        this.setLocation(400, 100);
        conn = ConectaDB.conecta();
        listarAdmin();
    }

    public void cadastrarAdmin() {
        Admin a = new Admin();
        AdminDAO dao = new AdminDAO();

        a.setNome(txtNome.getText());
        a.setLogin(txtLogin.getText());
        a.setSenha(txtSenha.getText());

        dao.create(a);

        limparCampos();
        listarAdmin();
    }

    public final void listarAdmin() {
        AdminTableModel modelo = new AdminTableModel();
        AdminDAO dao = new AdminDAO();
        modelo.setListaAdmins(dao.read());
        tblAdmin.setModel(modelo);
    }

    public void editarAdmin() {
        if (tblAdmin.getSelectedRow() != -1) {
            Admin a = new Admin();
            AdminDAO dao = new AdminDAO();
            a.setNome(txtNome.getText());
            a.setLogin(txtLogin.getText());
            a.setSenha(txtSenha.getText());
            a.setIdadmin((int) (tblAdmin.getValueAt(tblAdmin.getSelectedRow(), 0)));
            dao.update(a);
        }
        limparCampos(0);
        pesquisarAdmin();
    }

    public void deletarAdmin() {
        if (tblAdmin.getSelectedRow() != -1) {
            Admin a = new Admin();
            AdminDAO dao = new AdminDAO();

            a.setIdadmin((int) (tblAdmin.getValueAt(tblAdmin.getSelectedRow(), 0)));
            dao.delete(a);
        }
        limparCampos();
        listarAdmin();
    }

    public void pesquisarAdmin() {
        AdminTableModel modelo = new AdminTableModel();
        AdminDAO dao = new AdminDAO();
        modelo.setListaAdmins(dao.read(txtPesquisar.getText()));
        tblAdmin.setModel(modelo);
    }

    public void selecionaItem() {
        AdminDAO dao = new AdminDAO();        
        int seleciona = tblAdmin.getSelectedRow();
        int idAdmin = (int)tblAdmin.getModel().getValueAt(seleciona, 0);
        Admin admin = dao.read(idAdmin);
        txtIdAdmin.setText(String.valueOf(admin.getIdadmin()));
        txtNome.setText(admin.getNome());
        txtLogin.setText(admin.getLogin());
        txtSenha.setText(admin.getSenha());
        
    }

    public void limparCampos() {
        txtIdAdmin.setText(null);
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtPesquisar.setText(null);
        listarAdmin();
    }
    public void limparCampos(Integer all) {
        txtIdAdmin.setText(null);
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIdAdmin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        Cadastrar = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Administradores");

        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        tblAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAdminKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblAdmin);

        jLabel1.setText("Código");

        txtIdAdmin.setEditable(false);

        jLabel2.setText("Nome");

        jLabel3.setText("Login");

        jLabel4.setText("Senha");

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
                                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNome))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 61, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar)
                    .addComponent(Editar)
                    .addComponent(Excluir)
                    .addComponent(Limpar))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cadastrar, Editar, Excluir, Limpar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        if (txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
        } else {
            cadastrarAdmin();
        }
    }//GEN-LAST:event_CadastrarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarAdmin();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked
        selecionaItem();
    }//GEN-LAST:event_tblAdminMouseClicked

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_LimparActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        if (txtIdAdmin.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor preencha todos os campos");
        } else {
            editarAdmin();
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        if (txtIdAdmin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor selecionar um Admin");
        } else {
            deletarAdmin();
        }
    }//GEN-LAST:event_ExcluirActionPerformed

    private void tblAdminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAdminKeyReleased
        selecionaItem();
    }//GEN-LAST:event_tblAdminKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton Limpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTextField txtIdAdmin;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}

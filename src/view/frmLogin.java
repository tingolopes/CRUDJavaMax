package view;

import java.sql.*;
import connection.ConectaDB;
import javax.swing.JOptionPane;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmLogin extends javax.swing.JFrame implements Autenticacao{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public frmLogin() {
        initComponents();
        this.setLocationRelativeTo(null); //centro da tela
        conn = ConectaDB.conecta();
        populaJComboBox();
    }
    
    @Override
    public final void populaJComboBox(){
        String sql = "select login from admin order by login";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cmbUsuario.addItem(rs.getString("login"));
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    @Override
    public void logar() {
        String sql = "SELECT idadmin, login, senha FROM admin WHERE login = ? AND senha = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, (String) cmbUsuario.getSelectedItem());
            ps.setString(2, txtSenha.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer idadmin = rs.getInt("idadmin");
                frmPrincipal frm = new frmPrincipal(idadmin);
                frm.setVisible(true); //torna visivel
                dispose(); //fecha o form de login (quem chamou)
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos");
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login de usuário");
        setResizable(false);

        jLabel1.setText("Usuario");

        jLabel2.setText("Senha");

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setText("LOGIN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(cmbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenha))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        logar();
    }//GEN-LAST:event_txtSenhaActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
   
}

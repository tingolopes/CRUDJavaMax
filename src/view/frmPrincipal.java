
package view;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmPrincipal extends javax.swing.JFrame{

    private Integer idadmin;
    public frmPrincipal(Integer idadmin) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); //maximizado
        this.idadmin = idadmin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Cadastro = new javax.swing.JMenu();
        menuAdmin = new javax.swing.JMenuItem();
        menuCategoria = new javax.swing.JMenuItem();
        menuProduto = new javax.swing.JMenuItem();
        Sair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        Cadastro.setText("Cadastro");

        menuAdmin.setText("Admin");
        menuAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAdminActionPerformed(evt);
            }
        });
        Cadastro.add(menuAdmin);

        menuCategoria.setText("Categoria");
        menuCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCategoriaActionPerformed(evt);
            }
        });
        Cadastro.add(menuCategoria);

        menuProduto.setText("Produto");
        menuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProdutoActionPerformed(evt);
            }
        });
        Cadastro.add(menuProduto);

        jMenuBar1.add(Cadastro);

        Sair.setText("Sair");
        Sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SairMouseClicked(evt);
            }
        });
        jMenuBar1.add(Sair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_SairMouseClicked

    private void menuAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAdminActionPerformed
        frmAdmin form = new frmAdmin();
        form.setVisible(true);
        jDesktopPane1.add(form);
    }//GEN-LAST:event_menuAdminActionPerformed

    private void menuCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCategoriaActionPerformed
        frmCategoria form = new frmCategoria();
        form.setVisible(true);
        jDesktopPane1.add(form);
    }//GEN-LAST:event_menuCategoriaActionPerformed

    private void menuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProdutoActionPerformed
        frmProduto form = new frmProduto(idadmin);
        form.setVisible(true);
        jDesktopPane1.add(form);
    }//GEN-LAST:event_menuProdutoActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Cadastro;
    private javax.swing.JMenu Sair;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuAdmin;
    private javax.swing.JMenuItem menuCategoria;
    private javax.swing.JMenuItem menuProduto;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Admin;
import connection.ConectaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class AdminDAO {

    public void create(Admin a) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        //String sql = "INSERT INTO admin (nome, login, senha) VALUES(?,?,?)";
        String sql = "INSERT INTO admin (nome, login, senha) select ?,?,? "
                + "where not exists (select 1 from admin where login = ?) limit 1";

//      INSERT INTO admin (nome, login, senha) SELECT 'admin2','admin2','admin2' 
//      WHERE NOT EXISTS (SELECT 1 FROM admin WHERE login = 'admin2') LIMIT 1
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getLogin());
            ps.setString(3, a.getSenha());
            ps.setString(4, a.getLogin());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 0) {
                JOptionPane.showMessageDialog(null, "Login já cadastrado escolha outro", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }

    public List<Admin> read() {
        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Admin> listaAdmin = new ArrayList<>();

        try {
            ps = conn.prepareStatement("select idadmin, nome, login, senha from admin order by nome");
            rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setNome(rs.getString("nome"));
                admin.setLogin(rs.getString("login"));
                admin.setSenha(rs.getString("senha"));
                listaAdmin.add(admin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaAdmin;
    }

    public List<Admin> read(String pesquisaPorNome) {
        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Admin> listaAdmin = new ArrayList<>();

        try {
            ps = conn.prepareStatement("select idadmin, nome, login, senha from admin where nome like ? order by nome");
            ps.setString(1, "%" + pesquisaPorNome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setNome(rs.getString("nome"));
                admin.setLogin(rs.getString("login"));
                admin.setSenha(rs.getString("senha"));

                listaAdmin.add(admin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaAdmin;
    }

    public Admin read(Integer pesquisaPorId) {
        Admin admin = new Admin();
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select idadmin, nome, login, senha from admin where idadmin = ? order by nome");
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setNome(rs.getString("nome"));
                admin.setLogin(rs.getString("login"));
                admin.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return admin;
    }

    public void update(Admin a) {
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update admin set nome = ?, login = ?, senha = ? where idadmin = ?");
            ps.setString(1, a.getNome());
            ps.setString(2, a.getLogin());
            ps.setString(3, a.getSenha());
            ps.setInt(4, a.getIdadmin());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }

    public void delete(Admin a) {
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM admin WHERE idadmin = ?");
            ps.setInt(1, a.getIdadmin());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é permitido excluir um Administrador\nque possui produtos associados");
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }
}

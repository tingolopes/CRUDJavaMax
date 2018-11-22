/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Cliente;
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
 * @author Bravo
 */
public class clienteDAO {
    public void create(Cliente a) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        //String sql = "INSERT INTO admin (nome, login, senha) VALUES(?,?,?)";
        String sql = "INSERT INTO cliente (nome) VALUES(?)";

//      INSERT INTO admin (nome, login, senha) SELECT 'admin2','admin2','admin2' 
//      WHERE NOT EXISTS (SELECT 1 FROM admin WHERE login = 'admin2') LIMIT 1
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nome já cadastrado escolha outro", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }

    public List<Cliente> read() {
        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            ps = conn.prepareStatement("select idcliente, nome from cliente order by nome");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                listaCliente.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaCliente;
    }

    public List<Cliente> read(String pesquisaPorNome) {
        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            ps = conn.prepareStatement("select idcliente, nome from cliente where nome like ? order by nome");
            ps.setString(1, "%" + pesquisaPorNome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));

                listaCliente.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaCliente;
    }

    public Cliente read(Integer pesquisaPorId) {
        Cliente cliente = new Cliente();
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select idcliente, nome from cliente where idcliente = ? order by nome");
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return cliente;
    }

    public void update(Cliente a) {
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update cliente set nome = ? where idcliente = ?");
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getIdcliente());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }

    public void delete(Cliente a) {
        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            ps.setInt(1, a.getIdcliente());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é permitido excluir um Cliente\nque possui compras realizadas");
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }
}

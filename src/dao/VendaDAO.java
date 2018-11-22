/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Venda;
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
public class VendaDAO {
    public void create(Venda v) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        String sql = "INSERT INTO venda (datavenda, idcliente) VALUES(?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            java.sql.Date dataSql = new java.sql.Date(v.getDatavenda().getTime());
            ps.setDate(1, dataSql);
            ps.setInt(2, v.getIdcliente());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 1) {
                JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro!!! Venda não realizada", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }

    public List<Venda> read() {
        //pesquisa mais completa
        String sql = "Select v.cidvenda ID, v.datavenda, v.idcliente "
                + "From venda v "
                + "inner JOIN cliente c "
                + "On v.idcliente = c.idcliente "
                + "order by nome";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setIdvenda(rs.getInt("ID"));
                venda.setDatavenda(rs.getDate("datavenda"));
                venda.setIdcliente(rs.getInt("idcliente"));
                vendas.add(venda);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return vendas;
    }

    public Venda read(Integer pesquisaPorId) {
        //pesquisa mais completa
        String sql = "Select v.idvenda, v.datavenda, p.idcliente "
                + "From venda v "
                + "inner JOIN cliente c "
                + "On v.idcliente = c.idcliente "
                + "where v.idvenda = ? "
                + "order by datavenda";

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Venda venda = new Venda();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();

            while (rs.next()) {
                venda.setIdvenda(rs.getInt("idvenda"));
                venda.setDatavenda(rs.getDate("datavenda"));
                venda.setIdcliente(rs.getInt("idcliente"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return venda;
    }

    public void delete(Venda v) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM venda WHERE idvenda = ?");
            ps.setInt(1, v.getIdvenda());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }
    
}

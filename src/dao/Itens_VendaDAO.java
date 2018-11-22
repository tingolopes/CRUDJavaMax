/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Itens_Venda;
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
public class Itens_VendaDAO {
    public void create(Itens_Venda iv) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        String sql = "INSERT INTO itens_venda (qtd_produto, idproduto, idvenda) VALUES(?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, iv.getQtdproduto());
            ps.setInt(2, iv.getIdproduto());
            ps.setInt(3, iv.getIdvenda());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 1) {
                JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro!!! Item não adicionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }

    public List<Itens_Venda> read() {
        //pesquisa mais completa
        String sql = "Select iv.idvenda ID, p.idproduto, v.idcliente "
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

    public void delete(Itens_Venda v) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM itens_venda WHERE idvenda = ?");
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

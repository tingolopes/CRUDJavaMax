/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ItensVenda;
import bean.Produto;
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
public class ItensVendaDAO {

    public void create(ItensVenda iv) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        String sql = "INSERT INTO itensvenda (idvenda, idproduto, qtdproduto) VALUES(?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, iv.getIdvenda());
            ps.setInt(2, iv.getProduto().getIdproduto());
            ps.setInt(3, iv.getQtdproduto());
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

    public double readSomaCupom(Integer pesquisaPorId) {
        Double soma = 0.0;
        String sql = "select sum(iv.qtdproduto * p.preco) soma "
                + "from itensvenda iv "
                + "INNER JOIN venda v on iv.idvenda = v.idvenda "
                + "INNER JOIN produto p ON iv.idproduto = p.idproduto "
                + "WHERE v.idvenda = ?";

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("soma");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soma;
    }

    public List<ItensVenda> read() {
        //pesquisa mais completa
        String sql = "Select iv.idvenda, p.nome, iv.qtdproduto "
                + "From itensvenda iv "
                + "inner JOIN produto p "
                + "On iv.idproduto = p.idproduto "
                + "order by p.nome";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ItensVenda> listaDeItens = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                ItensVenda itens = new ItensVenda();
                Produto produto = new Produto();

                itens.setIdvenda(rs.getInt("idvenda"));

                //aqui tem que dar pra fazer numa linha só
                produto.setNome(rs.getString("nome"));
                itens.setProduto(produto);
                //aqui tem que dar pra fazer numa linha só

                itens.setQtdproduto(rs.getInt("qtdproduto"));
                listaDeItens.add(itens);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaDeItens;
    }

    public List<ItensVenda> read(Integer pesquisaPorId) {
        //pesquisa mais completa
        String sql = "Select iv.idvenda, p.nome, iv.qtdproduto "
                + "From itensvenda iv "
                + "inner JOIN produto p "
                + "On iv.idproduto = p.idproduto "
                + "where iv.idvenda = ? "
                + "order by p.nome";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ItensVenda> listaDeItens = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();

            while (rs.next()) {

                ItensVenda itens = new ItensVenda();
                Produto produto = new Produto();

                itens.setIdvenda(rs.getInt("idvenda"));

                //aqui tem que dar pra fazer numa linha só
                produto.setNome(rs.getString("nome"));
                itens.setProduto(produto);
                //aqui tem que dar pra fazer numa linha só

                itens.setQtdproduto(rs.getInt("qtdproduto"));
                listaDeItens.add(itens);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaDeItens;
    }

    public List<ItensVenda> read(String pesquisaPorId) {
        //pesquisa mais completa
        String sql = "select p.nome, iv.qtdproduto, p.preco, (iv.qtdproduto * p.preco) soma "
                + "from itensvenda iv "
                + "INNER JOIN venda v on iv.idvenda = v.idvenda "
                + "INNER JOIN produto p ON iv.idproduto = p.idproduto "
                + "WHERE v.idvenda = ?";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ItensVenda> listaDeItens = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(pesquisaPorId));
            rs = ps.executeQuery();

            while (rs.next()) {

                ItensVenda itens = new ItensVenda();
                Produto produto = new Produto();

                //aqui tem que dar pra fazer numa linha só
                produto.setNome(rs.getString("p.nome"));
                itens.setProduto(produto);
                //aqui tem que dar pra fazer numa linha só

                itens.setQtdproduto(rs.getInt("iv.qtdproduto"));

                produto.setPreco(rs.getDouble("p.preco"));
                itens.setProduto(produto);
                //aqui tem que dar pra fazer numa linha só

                itens.setSoma(rs.getDouble("soma"));
                listaDeItens.add(itens);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return listaDeItens;
    }

    public void delete(ItensVenda v) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM itensvenda WHERE idvenda = ?");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConectaDB;
import bean.Produto;
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
public class ProdutoDAO {

    public void create(Produto p) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        String sql = "INSERT INTO produto (nome, preco, datacadastro, idadmin, idcategoria) select ?,?,?,?,? "
                + "where not exists (select 1 from produto where nome like ?) limit 1";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            java.sql.Date dataSql = new java.sql.Date(p.getDatacadastro().getTime());
            ps.setDate(3, dataSql);
            ps.setInt(4, p.getIdadmin());
            ps.setInt(5, p.getIdcategoria());
            ps.setString(6, p.getNome());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 0) {
                JOptionPane.showMessageDialog(null, "Produto já cadastrado, escolha outro nome", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }

    public List<Produto> read() {
        //pesquisa mais completa
        String sql = "Select p.idproduto ID, p.nome Nome, p.preco Preço, "
                + "p.datacadastro, p.idadmin, p.idcategoria, c.categoria Categoria "
                + "From produto p "
                + "inner JOIN categoria c "
                + "On p.idcategoria = c.idcategoria "
                + "order by nome";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setIdproduto(rs.getInt("ID"));
                produto.setNome(rs.getString("Nome"));
                produto.setPreco(rs.getDouble("Preço"));
                produto.setDatacadastro(rs.getDate("datacadastro"));
                produto.setIdadmin(rs.getInt("idadmin"));
                produto.setIdcategoria(rs.getInt("idcategoria"));
                produto.setNomeCategoria(rs.getString("Categoria"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> read(String pesquisaPorNome) {
        //pesquisa mais completa
        String sql = "Select p.idproduto, p.nome, p.preco, "
                + "p.datacadastro, p.idadmin, p.idcategoria, c.categoria "
                + "From produto p "
                + "inner JOIN categoria c "
                + "On p.idcategoria = c.idcategoria "
                + "where (p.nome like ? or c.categoria like ?) " //pesquisa por nome do produto ou categoria
                + "order by nome";

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);              //na mesma pesquisa
            ps.setString(1, "%" + pesquisaPorNome + "%"); //pesquisa o nome do produto
            ps.setString(2, "%" + pesquisaPorNome + "%"); //pesquisa a categoria
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDatacadastro(rs.getDate("datacadastro"));
                produto.setIdadmin(rs.getInt("idadmin"));
                produto.setIdcategoria(rs.getInt("idcategoria"));
                produto.setNomeCategoria(rs.getString("categoria"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public Produto read(Integer pesquisaPorId) {
        //pesquisa mais completa
        String sql = "Select p.idproduto, p.nome, p.preco, "
                + "p.datacadastro, p.idadmin, p.idcategoria, c.categoria "
                + "From produto p "
                + "inner JOIN categoria c "
                + "On p.idcategoria = c.idcategoria "
                + "where p.idproduto = ? "
                + "order by nome";

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Produto produto = new Produto();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();

            while (rs.next()) {
                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDatacadastro(rs.getDate("datacadastro"));
                produto.setIdadmin(rs.getInt("idadmin"));
                produto.setIdcategoria(rs.getInt("idcategoria"));
                produto.setNomeCategoria(rs.getString("Categoria"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return produto;
    }

    public void update(Produto p) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE produto SET nome = ?, preco = ?, datacadastro = ?, "
                    + "idadmin = ?, idcategoria = ? WHERE idproduto = ?");
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());

            java.sql.Date dataSql = new java.sql.Date(p.getDatacadastro().getTime());
            ps.setDate(3, dataSql);
            ps.setInt(4, p.getIdadmin());
            ps.setInt(5, p.getIdcategoria());
            ps.setInt(6, p.getIdproduto());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }

    public void delete(Produto p) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM produto WHERE idproduto = ?");
            ps.setInt(1, p.getIdproduto());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }
}

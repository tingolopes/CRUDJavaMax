/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Categoria;
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
public class CategoriaDAO {

    public void create(Categoria c) {

        Connection conn = ConectaDB.conecta();
        PreparedStatement ps = null;

        String sql = "INSERT INTO categoria (categoria, descricao) select ?,? "
                + "where not exists (select 1 from categoria where categoria like ?) limit 1";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCategoria());
            ps.setString(2, c.getDescricao());
            ps.setString(3, c.getCategoria());
            ps.executeUpdate();

            if (ps.getUpdateCount() == 0) {
                JOptionPane.showMessageDialog(null, "Categoria já cadastrada, escolha outro nome", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }
    }

    public List<Categoria> read() {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Categoria> categorias = new ArrayList<>();

        //pesquisa mais completa
        String sql = "Select c.idcategoria, c.categoria, Count(p.idproduto), "
                + "sum(p.preco), descricao "
                + "From produto p "
                + "right JOIN categoria c On c.idcategoria = p.idcategoria "
                + "Group By p.idcategoria, c.categoria "
                + "order by categoria";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria();
                categoria.setIdcategoria(Integer.parseInt(rs.getString("idcategoria")));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setQtdProduto(rs.getInt("Count(p.idproduto)"));
                categoria.setSomaCategoria(rs.getDouble("sum(p.preco)"));
                categoria.setDescricao(rs.getString("descricao"));

                categorias.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return categorias;
    }

    public List<Categoria> read(String pesquisaPorNome) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Categoria> categorias = new ArrayList<>();

        //pesquisa mais completa
        String sql2 = "Select c.idcategoria, c.categoria, Count(p.idproduto), "
                + "sum(p.preco), descricao "
                + "From produto p "
                + "right JOIN categoria c On c.idcategoria = p.idcategoria "
                + "where c.categoria like ? "
                + "Group By p.idcategoria, c.categoria "
                + "order by categoria";

        try {
            ps = conn.prepareStatement(sql2);
            ps.setString(1, "%" + pesquisaPorNome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria();
                categoria.setIdcategoria(Integer.parseInt(rs.getString("idcategoria")));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setQtdProduto(rs.getInt("Count(p.idproduto)"));
                categoria.setSomaCategoria(rs.getDouble("sum(p.preco)"));
                categoria.setDescricao(rs.getString("descricao"));

                categorias.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return categorias;
    }

    public Categoria read(Integer pesquisaPorId) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;
        ResultSet rs = null;

        Categoria categoria = new Categoria();

        //pesquisa mais completa
        String sql2 = "Select c.idcategoria, c.categoria, Count(p.idproduto), "
                + "sum(p.preco), descricao "
                + "From produto p "
                + "right JOIN categoria c On c.idcategoria = p.idcategoria "
                + "where c.idcategoria = ? "
                + "Group By p.idcategoria, c.categoria "
                + "order by categoria";

        try {
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, pesquisaPorId);
            rs = ps.executeQuery();

            while (rs.next()) {
                categoria.setIdcategoria(Integer.parseInt(rs.getString("idcategoria")));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setQtdProduto(rs.getInt("Count(p.idproduto)"));
                categoria.setSomaCategoria(rs.getDouble("sum(p.preco)"));
                categoria.setDescricao(rs.getString("descricao"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaDB.closeConnection(conn, ps, rs);
        }
        return categoria;
    }

    public void update(Categoria c) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE categoria SET categoria = ? , descricao = ? WHERE idcategoria = ?");
            ps.setString(1, c.getCategoria());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getIdcategoria());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }

    public void delete(Categoria c) {

        Connection conn = ConectaDB.conecta();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM categoria WHERE idcategoria = ?");
            ps.setInt(1, c.getIdcategoria());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é permitido excluir uma categoria\nque possui produtos cadastrados");
        } finally {
            ConectaDB.closeConnection(conn, ps);
        }

    }
}

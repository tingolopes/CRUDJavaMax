/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class Categoria {
    private Integer idcategoria;
    private String categoria; //nome da categoria
    private String descricao;
    private Integer qtdProduto;
    private Double somaCategoria;

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getCategoria(); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Double getSomaCategoria() {
        return somaCategoria;
    }

    public void setSomaCategoria(Double somaCategoria) {
        this.somaCategoria = somaCategoria;
    }
    
}

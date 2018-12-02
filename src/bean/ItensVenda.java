/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Bravo
 */
public class ItensVenda {
    private Integer idvenda;
    private Produto produto;
    private Integer qtdproduto;
    private Double soma;

    public Double getSoma() {
        return soma;
    }

    public void setSoma(Double soma) {
        this.soma = soma;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdproduto() {
        return qtdproduto;
    }

    public void setQtdproduto(Integer qtdproduto) {
        this.qtdproduto = qtdproduto;
    }
    
    
    public Integer getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Integer idvenda) {
        this.idvenda = idvenda;
    }
}

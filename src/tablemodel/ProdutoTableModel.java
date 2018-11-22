/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import bean.Produto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class ProdutoTableModel extends AbstractTableModel {

    private List<String> cabecalho;
    private List<Produto> listaProdutos;

    public void setListaProduto(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ProdutoTableModel() {
        cabecalho = new ArrayList<>();
        listaProdutos = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("Nome");
        cabecalho.add("Preço");
        cabecalho.add("Categoria");
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaProdutos.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                //retornar o id
                return listaProdutos.get(rowIndex).getIdproduto();                
            case 1:
                //retornar o nome
                return listaProdutos.get(rowIndex).getNome();
            case 2:
                //retornar o nome
                DecimalFormat df = new DecimalFormat();
                df.applyPattern("R$ #,##0.00");
                return df.format(listaProdutos.get(rowIndex).getPreco());
            case 3:
                //retornar o nome
                return listaProdutos.get(rowIndex).getNomeCategoria();
            default:
                return null;
        }
    }
}

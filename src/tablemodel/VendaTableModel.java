/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import bean.Venda;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bravo
 */
public class VendaTableModel extends AbstractTableModel{
    final List<String> cabecalho;
    private List<Venda> listaVendas;

    public void setListaVenda(List<Venda> listaVendas) {
        this.listaVendas = listaVendas;
    }

    public VendaTableModel() {
        cabecalho = new ArrayList<>();
        listaVendas = new ArrayList<>();

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
        return listaVendas.size();
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
                return listaVendas.get(rowIndex).getIdvenda();                
            case 1:
                //retornar o id
                return listaVendas.get(rowIndex).getIdcliente();
            case 2:
                //retornar o id
                return listaVendas.get(rowIndex).getDatavenda();
            case 3:
                //retornar o nome
                DecimalFormat df = new DecimalFormat();
                df.applyPattern("R$ #,##0.00");
                //return df.format(listaVendass.get(rowIndex).getPreco());
            case 4:
                //retornar o nome
               // return listaProdutos.get(rowIndex).getNomeCategoria();
            default:
                return null;
        }
    }
    
}

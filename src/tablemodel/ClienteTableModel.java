/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import bean.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class ClienteTableModel extends AbstractTableModel {

    private final List<String> cabecalho;
    private List<Cliente> listaClientes;

    public void setListaClientes(List<Cliente> listaCliente) {
        this.listaClientes = listaCliente;
    }

    public ClienteTableModel() {
        cabecalho = new ArrayList<>();
        listaClientes = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("Nome");
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                //retornar o id
                return listaClientes.get(rowIndex).getIdcliente();
            case 1:
                //retornar o nome
                return listaClientes.get(rowIndex).getNome();
            default:
                return null;
        }
    }

}

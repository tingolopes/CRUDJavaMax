/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import bean.Admin;
import java.util.ArrayList;


/**
 *
 * @author Michell
 * @author Kleber
 */
public class AdminTableModel extends AbstractTableModel{
    private final List<String> cabecalho;
    private List<Admin> listaAdmins;

    public void setListaAdmins(List<Admin> listaAdmins) {
        this.listaAdmins = listaAdmins;
    }

    public AdminTableModel() {
        cabecalho = new ArrayList<>();
        listaAdmins = new ArrayList<>();
        
        cabecalho.add("ID");
        cabecalho.add("Nome");
        cabecalho.add("Login");
    }
    
    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaAdmins.size();
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
                return listaAdmins.get(rowIndex).getIdadmin();                
            case 1:
                //retornar o nome
                return listaAdmins.get(rowIndex).getNome();
            case 2:
                //retornar o nome
                return listaAdmins.get(rowIndex).getLogin();
            default:
                return null;
        }
    }
    
    
    
    
    
    
    
    
    
    
}
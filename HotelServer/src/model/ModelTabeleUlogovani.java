/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.Zaposleni;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.ServerKontroler;

/**
 *
 * @author vanja
 */
public class ModelTabeleUlogovani extends AbstractTableModel implements Runnable{
    ArrayList<Zaposleni> lista=new ArrayList<>();
    String[] kolone = {"ID", "Ulogovani", "Korisničko ime"};

    public ModelTabeleUlogovani() {
        lista=ServerKontroler.getInstance().getUlogovaniZaposleni();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z=lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return z.getZaposleniID();
            case 1:
                return z.getIme() + " " + z.getPrezime();
            case 2:
                return z.getUsername();
                
            default:
                return null;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
           lista=ServerKontroler.getInstance().getUlogovaniZaposleni();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ModelTabeleUlogovani.class.getName()).log(Level.SEVERE, null, ex);
            }
            fireTableDataChanged();
        }
    }


  
    
    
}

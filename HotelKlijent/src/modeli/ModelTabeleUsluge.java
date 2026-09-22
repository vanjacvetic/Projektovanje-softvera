/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Usluga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanja
 */
public class ModelTabeleUsluge extends AbstractTableModel implements Runnable {

    private ArrayList<Usluga> lista;
    private String[] kolone = {"Naziv", "Opis", "Cena"};
    private String parametar = "";

    public ModelTabeleUsluge() {
        try {
            lista = KlijentKontroler.getInstance().pretragaUsluge();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleUsluge.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Usluga u = lista.get(row);
        
        switch (column) {
            case 0:
                return u.getNaziv();
            case 1:
                return u.getOpis();
            case 2:
                return u.getCena() + "din";
            

            default:
                return null;
        }
    }

    public Usluga vratiUslugu(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                osveziTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleUsluge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public void osveziTabelu() {
        try {
            lista = KlijentKontroler.getInstance().pretragaUsluge();
            if (!parametar.equals("")) {
                ArrayList<Usluga> novaLista = new ArrayList<>();
                for (Usluga u : lista) {
                    if (u.getNaziv().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(u);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

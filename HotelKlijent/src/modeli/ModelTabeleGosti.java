/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Gost;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanja
 */
public class ModelTabeleGosti extends AbstractTableModel implements Runnable {

    private ArrayList<Gost> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Datum rodjenja", "Telefon", "Mesto"};
    private String parametar = "";

    public ModelTabeleGosti() {
        try {
            lista = KlijentKontroler.getInstance().pretragaGosta();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleGosti.class.getName()).log(Level.SEVERE, null, ex);
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
        Gost g = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch (column) {
            case 0:
                return g.getGostID();
            case 1:
                return g.getIme();
            case 2:
                return g.getPrezime();
            case 3:
                return sdf.format(g.getDatumRodjenja());
            case 4:
                return g.getTelefon();
            case 5:
                return g.getMesto();

            default:
                return null;
        }
    }

    public Gost vratiGosta(int row) {
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
            Logger.getLogger(ModelTabeleGosti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public void osveziTabelu() {
        try {
            lista = KlijentKontroler.getInstance().pretragaGosta();
            if (!parametar.equals("")) {
                ArrayList<Gost> novaLista = new ArrayList<>();
                for (Gost g : lista) {
                    if (g.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || g.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(g);
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

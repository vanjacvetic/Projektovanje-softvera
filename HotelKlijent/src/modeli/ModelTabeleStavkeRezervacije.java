/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.StavkaRezervacije;
import domen.Usluga;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanja
 */
public class ModelTabeleStavkeRezervacije extends AbstractTableModel {

    private ArrayList<StavkaRezervacije> lista;
    private String[] kolone = {"Rb", "Usluga", "Broj dana", "Cena po danu", "Iznos"};
    private int rb = 0;

    public ModelTabeleStavkeRezervacije() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkeRezervacije(ArrayList<StavkaRezervacije> stavkeRezervacije) {
        lista = stavkeRezervacije;
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
        StavkaRezervacije sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getUsluga().getNaziv();
            case 2:
                return sr.getBrojDana();
            case 3:
                return sr.getCena() + "din";
            case 4:
                return sr.getIznos() + "din";

            default:
                return null;
        }
    }

    public ArrayList<StavkaRezervacije> getLista() {
        return lista;
    }

    public void dodajStavku(StavkaRezervacije sr) {
        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRezervacije sr : lista) {
            sr.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public boolean postojiUsluga(Usluga usluga) {
        for (StavkaRezervacije stavkaRezervacije : lista) {
            if (stavkaRezervacije.getUsluga().getUslugaID() == usluga.getUslugaID()) {
                return true;
            }
        }
        return false;
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaRezervacije stavkaRezervacije : lista) {
            ukupanIznos += stavkaRezervacije.getIznos();
        }

        return ukupanIznos;
    }

}

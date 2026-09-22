/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Gost;
import domen.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanja
 */
public class ModelTabeleRezervacije extends AbstractTableModel implements Runnable {

    private ArrayList<Rezervacija> lista;
    private String[] kolone = {"ID", "Gost", "Soba", "Ukupan iznos",
        "Datum prijave", "Datum odjave"};
    private String parametar = "";

    public ModelTabeleRezervacije() {
        try {
            lista = KlijentKontroler.getInstance().pretragaRezervacije(null);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleRezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabeleRezervacije(Gost gost) {
        try {
            lista = KlijentKontroler.getInstance().pretragaRezervacije(gost);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleRezervacije.class.getName()).log(Level.SEVERE, null, ex);
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
        Rezervacija r = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return r.getRezervacijaID();
            case 1:
                return r.getGost();
            case 2:
                return r.getSoba();
            case 3:
                return r.getIznos() + "din";
            case 4:
                return sdf.format(r.getDatumPrijave());
            case 5:
                return sdf.format(r.getDatumOdjave());

            default:
                return null;
        }
    }

    public Rezervacija vratiRezervaciju(int row) {
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
            Logger.getLogger(ModelTabeleRezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public void osveziTabelu() {
        try {
            lista = KlijentKontroler.getInstance().pretragaRezervacije(null);
            if (!parametar.equals("")) {
                ArrayList<Rezervacija> novaLista = new ArrayList<>();
                for (Rezervacija r : lista) {
                    if (r.getGost().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || r.getGost().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(r);
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

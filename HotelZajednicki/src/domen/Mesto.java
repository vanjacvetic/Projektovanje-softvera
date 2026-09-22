/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vanja
 */
public class Mesto extends OpstiDomenskiObjekat {

    private int mestoID;
    private String naziv;
    private String postanskiBroj;

    @Override
    public String toString() {
        return naziv;
    }

    public Mesto(int mestoID, String naziv, String postanskiBroj) {
        this.mestoID = mestoID;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public Mesto() {
    }

    @Override
    public String nazivTabele() {
        return " Mesto ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto(rs.getInt("MestoID"),
                    rs.getString("m.naziv"), rs.getString("postanskiBroj"));

            lista.add(m);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, postanskiBroj) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "', '" + postanskiBroj + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', postanskiBroj = '" + postanskiBroj + "' ";
    }

    @Override
    public String uslov() {
        return " mestoID = " + mestoID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getMestoID() {
        return mestoID;
    }

    public void setMestoID(int mestoID) {
        this.mestoID = mestoID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

}

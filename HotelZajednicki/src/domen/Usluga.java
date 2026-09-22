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
public class Usluga extends OpstiDomenskiObjekat {

    private int uslugaID;
    private String naziv;
    private String opis;
    private double cena;

    @Override
    public String toString() {
        return naziv + " (Cena: " + cena + "din)";
    }

    public Usluga(int uslugaID, String naziv, String opis, double cena) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Usluga() {
    }

    @Override
    public String nazivTabele() {
        return " Usluga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Usluga u = new Usluga(rs.getInt("uslugaID"),
                    rs.getString("u.naziv"), rs.getString("opis"),
                    rs.getDouble("cena"));

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, opis, cena) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', "
                + " " + cena + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " cena = " + cena + " ";
    }

    @Override
    public String uslov() {
        return " uslugaID = " + uslugaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}

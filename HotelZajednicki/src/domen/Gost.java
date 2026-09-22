/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vanja
 */
public class Gost extends OpstiDomenskiObjekat {

    private int gostID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String telefon;
    private Mesto mesto;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Gost(int gostID, String ime, String prezime, Date datumRodjenja, String telefon, Mesto mesto) {
        this.gostID = gostID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public Gost() {
    }

    @Override
    public String nazivTabele() {
        return " Gost ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return " JOIN MESTO M ON (M.MESTOID = G.MESTOID) ";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            Mesto m = new Mesto(rs.getInt("MestoID"),
                    rs.getString("m.naziv"), rs.getString("postanskiBroj"));

            Gost gost = new Gost(rs.getInt("gostID"), rs.getString("g.ime"),
                    rs.getString("g.prezime"), rs.getDate("datumRodjenja"),
                    rs.getString("telefon"), m);

            lista.add(gost);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, datumRodjenja, telefon, mestoID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + new java.sql.Date(datumRodjenja.getTime()) + "', "
                + "'" + telefon + "', " + mesto.getMestoID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " telefon = '" + telefon + "', mestoID ="
                + mesto.getMestoID();
    }

    @Override
    public String uslov() {
        return " gostID = " + gostID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getGostID() {
        return gostID;
    }

    public void setGostID(int gostID) {
        this.gostID = gostID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

}

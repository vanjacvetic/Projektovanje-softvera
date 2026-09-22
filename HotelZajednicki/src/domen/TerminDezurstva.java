/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vanja
 */
public class TerminDezurstva extends OpstiDomenskiObjekat {

    private int terminDezurstvaID;
    private Date datumVremePocetka;
    private Date datumVremeZavrsetka;

    public TerminDezurstva(int terminDezurstvaID, Date datumVremePocetka, Date datumVremeZavrsetka) {
        this.terminDezurstvaID = terminDezurstvaID;
        this.datumVremePocetka = datumVremePocetka;
        this.datumVremeZavrsetka = datumVremeZavrsetka;
    }

    public TerminDezurstva() {
    }

    @Override
    public String nazivTabele() {
        return " TerminDezurstva ";
    }

    @Override
    public String alijas() {
        return " td ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            TerminDezurstva td = new TerminDezurstva(rs.getInt("TerminDezurstvaID"),
                    rs.getDate("datumVremePocetka"), rs.getDate("datumVremeZavrsetka"));

            lista.add(td);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremePocetka, datumVremeZavrsetka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "'" + new Timestamp(datumVremeZavrsetka.getTime()) + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " terminDezurstvaID = " + terminDezurstvaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getTerminDezurstvaID() {
        return terminDezurstvaID;
    }

    public void setTerminDezurstvaID(int terminDezurstvaID) {
        this.terminDezurstvaID = terminDezurstvaID;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public Date getDatumVremeZavrsetka() {
        return datumVremeZavrsetka;
    }

    public void setDatumVremeZavrsetka(Date datumVremeZavrsetka) {
        this.datumVremeZavrsetka = datumVremeZavrsetka;
    }

}

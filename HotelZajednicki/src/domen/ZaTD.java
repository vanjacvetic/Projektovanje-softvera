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
public class ZaTD extends OpstiDomenskiObjekat {

    private TerminDezurstva terminDezurstva;
    private Zaposleni zaposleni;
    private String statusPrisutnosti;

    public ZaTD(TerminDezurstva terminDezurstva, Zaposleni zaposleni, String statusPrisutnosti) {
        this.terminDezurstva = terminDezurstva;
        this.zaposleni = zaposleni;
        this.statusPrisutnosti = statusPrisutnosti;
    }

    public ZaTD() {
    }

    @Override
    public String nazivTabele() {
        return " ZaTD ";
    }

    @Override
    public String alijas() {
        return " ztd ";
    }

    @Override
    public String join() {
        return " JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = ZTD.ZAPOSLENIID)\n"
                + "JOIN TERMINDEZURSTVA TD ON (TD.TERMINDEZURSTVAID = ZTD.TERMINDEZURSTVAID) ";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("zaposleniID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            TerminDezurstva td = new TerminDezurstva(rs.getInt("TerminDezurstvaID"),
                    rs.getDate("datumVremePocetka"), rs.getDate("datumVremeZavrsetka"));

            ZaTD ztd = new ZaTD(td, z, rs.getString("statusPrisutnosti"));

            lista.add(ztd);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TerminDezurstvaID, zaposleniID, statusPrisutnosti) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + zaposleni.getZaposleniID() + ", " + terminDezurstva.getTerminDezurstvaID() + ", "
                + " '" + statusPrisutnosti + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " zaposleniID = " + zaposleni.getZaposleniID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public String getStatusPrisutnosti() {
        return statusPrisutnosti;
    }

    public void setStatusPrisutnosti(String statusPrisutnosti) {
        this.statusPrisutnosti = statusPrisutnosti;
    }

}

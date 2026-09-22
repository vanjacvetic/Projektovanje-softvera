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
public class Rezervacija extends OpstiDomenskiObjekat {

    private int rezervacijaID;
    private Date datumPrijave;
    private Date datumOdjave;
    private double iznos;
    private String soba;
    private Gost gost;
    private Zaposleni zaposleni;
    private ArrayList<StavkaRezervacije> stavkeRezervacije;

    public Rezervacija(int rezervacijaID, Date datumPrijave, Date datumOdjave, double iznos, String soba, Gost gost, Zaposleni zaposleni, ArrayList<StavkaRezervacije> stavkeRezervacije) {
        this.rezervacijaID = rezervacijaID;
        this.datumPrijave = datumPrijave;
        this.datumOdjave = datumOdjave;
        this.iznos = iznos;
        this.soba = soba;
        this.gost = gost;
        this.zaposleni = zaposleni;
        this.stavkeRezervacije = stavkeRezervacije;
    }

    public Rezervacija() {
    }

    @Override
    public String nazivTabele() {
        return " Rezervacija ";
    }

    @Override
    public String alijas() {
        return " rez ";
    }

    @Override
    public String join() {
        return " JOIN GOST G ON (G.GOSTID = REZ.GOSTID)\n"
                + "JOIN MESTO M ON (M.MESTOID = G.MESTOID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = REZ.ZAPOSLENIID) ";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("zaposleniID"),
                    rs.getString("z.Ime"), rs.getString("z.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Mesto m = new Mesto(rs.getInt("MestoID"),
                    rs.getString("m.naziv"), rs.getString("postanskiBroj"));

            Gost gost = new Gost(rs.getInt("gostID"), rs.getString("g.ime"),
                    rs.getString("g.prezime"), rs.getDate("datumRodjenja"),
                    rs.getString("telefon"), m);

            Rezervacija rez = new Rezervacija(rs.getInt("rezervacijaID"), rs.getDate("datumPrijave"),
                    rs.getDate("datumOdjave"), rs.getDouble("iznos"),
                    rs.getString("Soba"), gost, z, new ArrayList<>());

            lista.add(rez);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumPrijave, datumOdjave, iznos, soba, gostID, zaposleniID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumPrijave.getTime()) + "', "
                + "'" + new java.sql.Date(datumOdjave.getTime()) + "', "
                + " " + iznos + ", '" + soba + "', " + gost.getGostID() + ", "
                + zaposleni.getZaposleniID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumPrijave = '" + new java.sql.Date(datumPrijave.getTime()) + "', "
                + "datumOdjave = '" + new java.sql.Date(datumOdjave.getTime()) + "', "
                + "iznos = " + iznos + ", soba = '" + soba + "' ";
    }

    @Override
    public String uslov() {
        return " rezervacijaID = " + rezervacijaID;
    }

    @Override
    public String uslovZaSelect() {
        if (gost == null) {
            return "";
        }
        return " WHERE G.GOSTID = " + gost.getGostID();
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public Date getDatumOdjave() {
        return datumOdjave;
    }

    public void setDatumOdjave(Date datumOdjave) {
        this.datumOdjave = datumOdjave;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ArrayList<StavkaRezervacije> getStavkeRezervacije() {
        return stavkeRezervacije;
    }

    public void setStavkeRezervacije(ArrayList<StavkaRezervacije> stavkeRezervacije) {
        this.stavkeRezervacije = stavkeRezervacije;
    }

    public String getSoba() {
        return soba;
    }

    public void setSoba(String soba) {
        this.soba = soba;
    }

}

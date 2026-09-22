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
public class StavkaRezervacije extends OpstiDomenskiObjekat {

    private Rezervacija rezervacija;
    private int rb;
    private String napomena;
    private double cena;
    private double iznos;
    private int brojDana;
    private Usluga usluga;

    public StavkaRezervacije(Rezervacija rezervacija, int rb, String napomena, double cena, double iznos, int brojDana, Usluga usluga) {
        this.rezervacija = rezervacija;
        this.rb = rb;
        this.napomena = napomena;
        this.cena = cena;
        this.iznos = iznos;
        this.brojDana = brojDana;
        this.usluga = usluga;
    }

    public StavkaRezervacije() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaRezervacije ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN REZERVACIJA REZ ON (REZ.REZERVACIJAID = SR.REZERVACIJAID)\n"
                + " JOIN GOST G ON (G.GOSTID = REZ.GOSTID)\n"
                + "JOIN MESTO M ON (M.MESTOID = G.MESTOID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = REZ.ZAPOSLENIID)\n "
                + "JOIN USLUGA U ON (U.USLUGAID = SR.USLUGAID) ";
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

            Gost gost = new Gost(rs.getInt("gostID"), rs.getString("G.ime"),
                    rs.getString("G.prezime"), rs.getDate("datumRodjenja"),
                    rs.getString("telefon"), m);

            Rezervacija rez = new Rezervacija(rs.getInt("rezervacijaID"), rs.getDate("datumPrijave"),
                    rs.getDate("datumOdjave"), rs.getDouble("iznos"),
                    rs.getString("Soba"), gost, z, new ArrayList<>());

            Usluga u = new Usluga(rs.getInt("uslugaID"),
                    rs.getString("u.naziv"), rs.getString("opis"),
                    rs.getDouble("u.cena"));

            StavkaRezervacije sr = new StavkaRezervacije(rez, rs.getInt("rb"),
                    rs.getString("napomena"), rs.getDouble("sr.cena"),
                    rs.getDouble("iznos"), rs.getInt("brojDana"), u);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (rezervacijaID, rb, napomena, cena, iznos, brojDana, uslugaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + rezervacija.getRezervacijaID() + ", " + rb + ", "
                + "'" + napomena + "', " + cena + ", " + iznos + ", " + brojDana + ", "
                + usluga.getUslugaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " napomena = '" + napomena
                + "', cena = " + cena
                + ", iznos = " + iznos
                + ", brojdana = " + brojDana
                + ", uslugaID = " + usluga.getUslugaID();
    }

    @Override
    public String uslov() {
        return " rezervacijaID = " + rezervacija.getRezervacijaID()
                + " AND rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE REZ.rezervacijaID = " + rezervacija.getRezervacijaID();
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

}

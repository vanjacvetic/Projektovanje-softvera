/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Gost;
import domen.Mesto;
import domen.Rezervacija;
import domen.Usluga;
import domen.Zaposleni;
import java.util.ArrayList;
import so.gost.SODodajGosta;
import so.gost.SOObrisiGosta;
import so.gost.SOPretragaGosta;
import so.gost.SOIzmeniGosta;
import so.login.SOLogin;
import so.mesto.SOVratiSvaMesta;
import so.rezervacija.SODodajRezervaciju;
import so.rezervacija.SOObrisiRezervaciju;
import so.rezervacija.SOPretragaRezervacije;
import so.rezervacija.SOIzmeniRezervaciju;
import so.usluga.SODodajUslugu;
import so.usluga.SOObrisiUslugu;
import so.usluga.SOPretragaUsluge;
import so.usluga.SOIzmeniUslugu;

/**
 *
 * @author vanja
 */
public class ServerKontroler {

    private static ServerKontroler instance;
    private ArrayList<Zaposleni> ulogovaniZaposleni = new ArrayList<>();

    private ServerKontroler() {
    }

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public ArrayList<Zaposleni> getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(ArrayList<Zaposleni> ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        SOLogin so = new SOLogin();
        so.izvrsi(zaposleni);
        return so.getUlogovani();
    }

    public void dodajGosta(Gost gost) throws Exception {
        (new SODodajGosta()).izvrsi(gost);
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws Exception {
        (new SODodajRezervaciju()).izvrsi(rezervacija);
    }

    public void obrisiGosta(Gost gost) throws Exception {
        (new SOObrisiGosta()).izvrsi(gost);
    }

    public void obrisiRezervaciju(Rezervacija rezervacija) throws Exception {
        (new SOObrisiRezervaciju()).izvrsi(rezervacija);
    }

    public void izmeniGosta(Gost gost) throws Exception {
        (new SOIzmeniGosta()).izvrsi(gost);
    }

    public void izmeniRezervaciju(Rezervacija rezervacija) throws Exception {
        (new SOIzmeniRezervaciju()).izvrsi(rezervacija);
    }

    public ArrayList<Gost> pretragaGosta() throws Exception {
        SOPretragaGosta so = new SOPretragaGosta();
        so.izvrsi(new Gost());
        return so.getLista();
    }

    public ArrayList<Rezervacija> pretragaRezervacije(Gost gost) throws Exception {
        SOPretragaRezervacije so = new SOPretragaRezervacije();

        Rezervacija r = new Rezervacija();
        r.setGost(gost);

        so.izvrsi(r);
        return so.getLista();
    }

    public ArrayList<Mesto> vratiSvaMesta() throws Exception {
        SOVratiSvaMesta so = new SOVratiSvaMesta();
        so.izvrsi(new Mesto());
        return so.getLista();
    }

    public void dodajUslugu(Usluga usluga) throws Exception {
        (new SODodajUslugu()).izvrsi(usluga);
    }

    public void obrisiUslugu(Usluga usluga) throws Exception {
        (new SOObrisiUslugu()).izvrsi(usluga);
    }

    public void izmeniUslugu(Usluga usluga) throws Exception {
        (new SOIzmeniUslugu()).izvrsi(usluga);
    }

    public ArrayList<Usluga> pretragaUsluge() throws Exception {
        SOPretragaUsluge so = new SOPretragaUsluge();
        so.izvrsi(new Usluga());
        return so.getLista();
    }

    public void odjava(Zaposleni ulogovani) {
        ulogovaniZaposleni.remove(ulogovani);
    }

   

   

}

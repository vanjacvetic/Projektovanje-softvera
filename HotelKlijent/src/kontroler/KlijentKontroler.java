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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.pomocno.StatusOdgovora;
import transfer.pomocno.Operacije;

/**
 *
 * @author vanja
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    private KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) posaljiZahtev(Operacije.LOGIN, zaposleni);
    }

    public void odjava(Zaposleni ulogovani) throws Exception {
        posaljiZahtev(Operacije.ODJAVA, ulogovani);
    }

    public void dodajGosta(Gost gost) throws Exception {
        posaljiZahtev(Operacije.DODAJ_GOSTA, gost);
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws Exception {
        posaljiZahtev(Operacije.DODAJ_REZERVACIJU, rezervacija);
    }
    
    public void dodajUslugu(Usluga usluga) throws Exception {
        posaljiZahtev(Operacije.DODAJ_USLUGU, usluga);
    }
    
    public void obrisiGosta(Gost gost) throws Exception {
        posaljiZahtev(Operacije.OBRISI_GOSTA, gost);
    }

    public void obrisiRezervaciju(Rezervacija rezervacija) throws Exception {
        posaljiZahtev(Operacije.OBRISI_REZERVACIJU, rezervacija);
    }
   
    public void obrisiUslugu(Usluga usluga) throws Exception {
        posaljiZahtev(Operacije.OBRISI_USLUGU, usluga);
    }
    
    public void izmeniGosta(Gost gost) throws Exception {
        posaljiZahtev(Operacije.IZMENI_GOSTA, gost);
    }

    public void izmeniRezervaciju(Rezervacija rezervacija) throws Exception {
        posaljiZahtev(Operacije.IZMENI_REZERVACIJU, rezervacija);
    }
    
    public void izmeniUslugu(Usluga usluga) throws Exception {
        posaljiZahtev(Operacije.IZMENI_USLUGU, usluga);
    }
    
    public ArrayList<Gost> pretragaGosta() throws Exception {
        return (ArrayList<Gost>) posaljiZahtev(Operacije.PRETRAGA_GOSTA, null);
    }

    public ArrayList<Rezervacija> pretragaRezervacije(Gost gost) throws Exception {
        return (ArrayList<Rezervacija>) posaljiZahtev(Operacije.PRETRAGA_REZERVACIJE, gost);
    }
    
    public ArrayList<Usluga> pretragaUsluge() throws Exception {
        return (ArrayList<Usluga>) posaljiZahtev(Operacije.PRETRAGA_USLUGE, null);
    }
    
    public ArrayList<Mesto> vratiSvaMesta() throws Exception {
        return (ArrayList<Mesto>) posaljiZahtev(Operacije.VRATI_SVA_MESTA, null);
    }

    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(operacija, parametar);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(zahtev);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor odgovor = (ServerskiOdgovor) in.readObject();

        if (odgovor.getStatusOdgovora().equals(StatusOdgovora.Greska)) {
            throw odgovor.getException();
        } else {
            return odgovor.getOdgovor();
        }

    }

}

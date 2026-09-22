/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import kontroler.ServerKontroler;
import domen.Gost;
import domen.Rezervacija;
import domen.Usluga;
import domen.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.pomocno.StatusOdgovora;
import transfer.pomocno.Operacije;

/**
 *
 * @author vanja
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket soket;

    ObradaKlijentskihZahteva(Socket socket) {
        this.soket = socket;
    }

    @Override
    public void run() {
        try {
            while (!soket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
                KlijentskiZahtev zahtev = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor odgovor = handleRequest(zahtev);
                ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
                out.writeObject(odgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handleRequest(KlijentskiZahtev zahtev) {
        ServerskiOdgovor odgovor = new ServerskiOdgovor(null, null, StatusOdgovora.Uspesno);
        try {
            switch (zahtev.getOperacija()) {
                case Operacije.DODAJ_GOSTA:
                    ServerKontroler.getInstance().dodajGosta((Gost) zahtev.getParametar());
                    break;
                case Operacije.DODAJ_REZERVACIJU:
                    ServerKontroler.getInstance().dodajRezervaciju((Rezervacija) zahtev.getParametar());
                    break;
                case Operacije.DODAJ_USLUGU:
                    ServerKontroler.getInstance().dodajUslugu((Usluga) zahtev.getParametar());
                    break;
                case Operacije.OBRISI_GOSTA:
                    ServerKontroler.getInstance().obrisiGosta((Gost) zahtev.getParametar());
                    break;
                case Operacije.OBRISI_REZERVACIJU:
                    ServerKontroler.getInstance().obrisiRezervaciju((Rezervacija) zahtev.getParametar());
                    break;
                case Operacije.OBRISI_USLUGU:
                    ServerKontroler.getInstance().obrisiUslugu((Usluga) zahtev.getParametar());
                    break;
                case Operacije.IZMENI_GOSTA:
                    ServerKontroler.getInstance().izmeniGosta((Gost) zahtev.getParametar());
                    break;
                case Operacije.IZMENI_REZERVACIJU:
                    ServerKontroler.getInstance().izmeniRezervaciju((Rezervacija) zahtev.getParametar());
                    break;
                case Operacije.IZMENI_USLUGU:
                    ServerKontroler.getInstance().izmeniUslugu((Usluga) zahtev.getParametar());
                    break;
                case Operacije.PRETRAGA_GOSTA:
                    odgovor.setOdgovor(ServerKontroler.getInstance().pretragaGosta());
                    break;
                case Operacije.VRATI_SVA_MESTA:
                    odgovor.setOdgovor(ServerKontroler.getInstance().vratiSvaMesta());
                    break;
                case Operacije.PRETRAGA_USLUGE:
                    odgovor.setOdgovor(ServerKontroler.getInstance().pretragaUsluge());
                    break;
                case Operacije.PRETRAGA_REZERVACIJE:
                    odgovor.setOdgovor(ServerKontroler.getInstance().pretragaRezervacije((Gost) zahtev.getParametar()));
                    break;
                case Operacije.LOGIN:
                    Zaposleni zaposleni = (Zaposleni) zahtev.getParametar();
                    Zaposleni z = ServerKontroler.getInstance().login(zaposleni);
                    odgovor.setOdgovor(z);
                    break;
                case Operacije.ODJAVA:
                    Zaposleni ulogovani = (Zaposleni) zahtev.getParametar();
                    ServerKontroler.getInstance().odjava(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            odgovor.setStatusOdgovora(StatusOdgovora.Greska);
            odgovor.setException(ex);
        }
        return odgovor;
    }

}

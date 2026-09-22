/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import kontroler.ServerKontroler;
import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOLogin extends OpstaSistemskaOperacija {

    Zaposleni ulogovani;

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }

        Zaposleni z = (Zaposleni) ado;

        for (Zaposleni zaposleni : ServerKontroler.getInstance().getUlogovaniZaposleni()) {
            if (zaposleni.getUsername().equals(z.getUsername())) {
                throw new Exception("Ovaj zaposleni je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {

        Zaposleni z = (Zaposleni) ado;

        ArrayList<Zaposleni> listaZaposlenih
                = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Zaposleni zaposleni : listaZaposlenih) {
            if (zaposleni.getUsername().equals(z.getUsername())
                    && zaposleni.getPassword().equals(z.getPassword())) {
                ulogovani = zaposleni;
                ServerKontroler.getInstance().getUlogovaniZaposleni().add(zaposleni);
                return;
            }
        }

        throw new Exception("Ne postoji zaposleni sa tim kredencijalima.");

    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

}

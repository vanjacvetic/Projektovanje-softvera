/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Rezervacija;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOObrisiRezervaciju extends OpstaSistemskaOperacija {

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}

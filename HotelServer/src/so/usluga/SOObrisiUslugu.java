/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;


import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Usluga;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOObrisiUslugu extends OpstaSistemskaOperacija {

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}

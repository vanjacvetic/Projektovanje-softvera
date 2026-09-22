/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.gost;

import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Gost;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SODodajGosta extends OpstaSistemskaOperacija {

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Gost)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gost!");
        }

        Gost g = (Gost) ado;

        ArrayList<Gost> gosti = (ArrayList<Gost>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Gost gost : gosti) {
            if (gost.getTelefon().equals(g.getTelefon())) {
                throw new Exception("Gost sa tim telefonom vec postoji!");
            }
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}

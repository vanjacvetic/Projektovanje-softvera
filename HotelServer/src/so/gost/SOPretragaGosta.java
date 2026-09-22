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
public class SOPretragaGosta extends OpstaSistemskaOperacija {

    private ArrayList<Gost> lista;

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Gost)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gost!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        ArrayList<OpstiDomenskiObjekat> gosti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Gost>) (ArrayList<?>) gosti;
    }

    public ArrayList<Gost> getLista() {
        return lista;
    }

}

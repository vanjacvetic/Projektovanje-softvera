/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mesto;

import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Mesto;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOVratiSvaMesta extends OpstaSistemskaOperacija {

    private ArrayList<Mesto> lista;

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        ArrayList<OpstiDomenskiObjekat> mesta = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Mesto>) (ArrayList<?>) mesta;
    }

    public ArrayList<Mesto> getLista() {
        return lista;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOPretragaRezervacije extends OpstaSistemskaOperacija {

    private ArrayList<Rezervacija> lista;

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        ArrayList<OpstiDomenskiObjekat> rezervacije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Rezervacija>) (ArrayList<?>) rezervacije;

        for (Rezervacija trenutnaRezervacija : lista) {
            StavkaRezervacije stavkaRezervacije = new StavkaRezervacije();
            stavkaRezervacije.setRezervacija(trenutnaRezervacija);

            ArrayList<StavkaRezervacije> stavkeTrenutneRezervacije
                    = (ArrayList<StavkaRezervacije>) (ArrayList<?>) DBBroker.getInstance().select(stavkaRezervacije);

            trenutnaRezervacija.setStavkeRezervacije(stavkeTrenutneRezervacije);
        }
    }

    public ArrayList<Rezervacija> getLista() {
        return lista;
    }

}

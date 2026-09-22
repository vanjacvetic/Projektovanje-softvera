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
import java.util.Date;
import java.util.HashMap;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SOIzmeniRezervaciju extends OpstaSistemskaOperacija {

    @Override
    protected void validiraj(OpstiDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija!");
        }

        Rezervacija r = (Rezervacija) ado;
        Date datumPrijave = r.getDatumPrijave();
        Date datumOdjave = r.getDatumOdjave();

        if (datumPrijave.after(datumOdjave) || datumPrijave.equals(datumOdjave)) {
            throw new Exception("Datum prijave mora biti pre datuma odjave!");
        }

        if (datumPrijave.before(new Date()) || datumPrijave.equals(datumOdjave)) {
            throw new Exception("Datum prijave mora biti u buducnosti!");
        }

        if (r.getStavkeRezervacije().isEmpty()) {
            throw new Exception("Rezervacija mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception {
        Rezervacija r = (Rezervacija) ado;

        DBBroker.getInstance().update(r);

        
        ArrayList<StavkaRezervacije> stareStavke
                = (ArrayList<StavkaRezervacije>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaRezervacije(r, 0, null, 0, 0, 0, null));

        HashMap<Integer, StavkaRezervacije> mapaStarih = new HashMap<>();
        for (StavkaRezervacije sr : stareStavke) {
            mapaStarih.put(sr.getRb(), sr);
        }

        HashMap<Integer, StavkaRezervacije> mapaNovih = new HashMap<>();
        for (StavkaRezervacije nova : r.getStavkeRezervacije()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        for (StavkaRezervacije stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        for (StavkaRezervacije nova : r.getStavkeRezervacije()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        for (StavkaRezervacije nova : r.getStavkeRezervacije()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }
    }

}

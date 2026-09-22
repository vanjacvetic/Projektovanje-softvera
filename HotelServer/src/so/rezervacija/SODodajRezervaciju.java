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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author vanja
 */
public class SODodajRezervaciju extends OpstaSistemskaOperacija {

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
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int novaRezID = rs.getInt(1);

        Rezervacija rezervacija = (Rezervacija) ado;
        rezervacija.setRezervacijaID(novaRezID);

        
        for (StavkaRezervacije stavkaRezervacije : rezervacija.getStavkeRezervacije()) {
            stavkaRezervacije.setRezervacija(rezervacija);
            DBBroker.getInstance().insert(stavkaRezervacije);
        }

    }

}

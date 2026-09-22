/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import dbb.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author vanja
 */
public abstract class OpstaSistemskaOperacija {
    
    protected abstract void validiraj(OpstiDomenskiObjekat ado) throws Exception;
    protected abstract void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat ado) throws Exception;

    public void izvrsi(OpstiDomenskiObjekat ado) throws Exception {
        try {
            validiraj(ado);
            izvrsiKonkretnuOperaciju(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}

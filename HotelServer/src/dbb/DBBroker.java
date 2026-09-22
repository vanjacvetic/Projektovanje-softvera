/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import domen.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author vanja
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public ArrayList<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    public PreparedStatement insert(OpstiDomenskiObjekat ado) throws SQLException {
        String naredba = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(OpstiDomenskiObjekat ado) throws SQLException {
        String naredba = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(naredba);
        Statement st = connection.createStatement();
        st.executeUpdate(naredba);
    }

    public void delete(OpstiDomenskiObjekat ado) throws SQLException {
        String naredba = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(naredba);
        Statement st = connection.createStatement();
        st.executeUpdate(naredba);
    }

}

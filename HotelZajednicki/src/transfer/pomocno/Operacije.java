/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.pomocno;

/**
 *
 * @author vanja
 */
public interface Operacije {

    public static final int LOGIN = 0;
    public static final int ODJAVA = 1;

    public static final int DODAJ_GOSTA = 2;
    public static final int OBRISI_GOSTA = 3;
    public static final int IZMENI_GOSTA = 4;
    public static final int PRETRAGA_GOSTA = 5;

    public static final int DODAJ_USLUGU = 6;
    public static final int OBRISI_USLUGU = 7;
    public static final int IZMENI_USLUGU = 8;
    public static final int PRETRAGA_USLUGE = 9;

    public static final int DODAJ_REZERVACIJU = 10;
    public static final int OBRISI_REZERVACIJU = 11;
    public static final int IZMENI_REZERVACIJU = 12;
    public static final int PRETRAGA_REZERVACIJE = 13;

    public static final int VRATI_SVA_MESTA = 14;

}

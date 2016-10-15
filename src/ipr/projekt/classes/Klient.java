/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ipr.projekt.classes;

import java.util.Date;

/**
 *
 * @author Michal
 */
public class Klient {
    private int id = 0;
    private String imie = null;
    private String nazwisko = null;
    private Date dataUr = null;
    private String adres = null;
    private String kod = null;
    private String miasto = null;
    private static Klient instane = new Klient();
    
    Klient(){
        
    }
    
    public void setDaneFull(int id, String imie, String nazwisko, Date dataUr, String adres, String kod, String miasto){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUr = dataUr;
        this.adres = adres;
        this.kod = kod;
        this.miasto = miasto;
    }
    
    public void clearUser(){
        id = 0;
        imie = null;
        nazwisko = null;
        dataUr = null;
        adres = null;
        kod = null;
        miasto = null;
    }
    
    public String getNazwisko(){
        return nazwisko;
    }
    
    public String getImie(){
        return imie;
    }
    
    public static Klient getInstance() {
        return instane;
    }
    
    public int getId(){
        return id;
    }

    public String getAdres() {
        return adres;
    }

    public String getKod() {
        return kod;
    }

    public String getMiasto() {
        return miasto;
    }
    
    public Date getDate(){
        return dataUr;
    }
}

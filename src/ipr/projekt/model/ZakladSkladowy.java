/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.model;

/**
 *
 * @author Michal
 */
public class ZakladSkladowy {

    private int id_meczu;
    float kurs;
    short wynikGosc;
    short wynikGosp;

 public ZakladSkladowy(int id_meczu, float kurs, short wynikGosc, short wynikGosp) {
        this.id_meczu = id_meczu;
        this.kurs = kurs;
        this.wynikGosc = wynikGosc;
        this.wynikGosp = wynikGosp;
    }

    public float getKurs() {
        return kurs;
    }

    public int getIdMeczu() {
        return id_meczu;
    }
    
    public boolean rowne(ZakladSkladowy mecz){
        return this.id_meczu == mecz.id_meczu;
    }

    public String getWynik() {
        return wynikGosp + ":" + wynikGosc;
    }
    
}

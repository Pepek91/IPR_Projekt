/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.model;

import java.util.ArrayList;

/**
 *
 * @author Michal
 */
public class Zaklad {

    private float kursZakladu = 1f;
    private float stawka = 0;
    ArrayList<ZakladSkladowy> mecze = new ArrayList<>();
    private static Zaklad instance = new Zaklad();

    public float getKursZakladu() {
        return kursZakladu;
    }

    public void setKursZakladu() {
        this.kursZakladu = kursZakladu;
    }

    public float getStawka() {
        return stawka;
    }

    public void setStawka(float stawka) {
        this.stawka = stawka;
    }

    public void addZakladSkladowy(ZakladSkladowy mecz) {
        int meczeSize = mecze.size();
        for (int i = 0; i < meczeSize; ++i) {
            if (mecz.rowne(mecze.get(i))) {
                return;
            }
        }
        mecze.add(mecz);
    }

//    public int[] getIdMeczyWZakladach(){
//        int size = mecze.size();
//        int[] id_meczy = new int[size];
//        for(int i = 0; i < size; ++i){
//            id_meczy[i] = mecze.get(i).getIdMeczu();
//        }
//        
//        return id_meczy;
//    }
    public void deleteZakladSkladowy(ZakladSkladowy mecz) {
        int meczeSize = mecze.size();
        for (int i = 0; i < meczeSize; ++i) {
            if (mecz.rowne(mecze.get(i))) {
                mecze.remove(i);
                return;
            }
        }
    }
    
    public void deleteZakladSkladowyById(int id) {
        int meczeSize = mecze.size();
        for (int i = 0; i < meczeSize; ++i) {
            if (id == mecze.get(i).getIdMeczu()) {
                mecze.remove(i);
                return;
            }
        }
    }

    public ZakladSkladowy getMeczById(int id) {
        int meczeSize = mecze.size();
        for (int i = 0; i < meczeSize; ++i) {
            if (mecze.get(i).getIdMeczu() == id) {
                return mecze.get(i);
            }
        }
        return null;
    }
    public boolean zakladIstniejeById(int id){
        int meczeSize = mecze.size();
        for (int i = 0; i < meczeSize; ++i) {
            if (id == mecze.get(i).getIdMeczu()) {
                return true;
            }
        }
        return false;
    }

    public ZakladSkladowy[] getZakladySkladowe() {
        int size = mecze.size();
        ZakladSkladowy[] meczeZwrot = new ZakladSkladowy[size];

        for (int i = 0; i < size; ++i) {
            meczeZwrot[i] = mecze.get(i);
        }

        return meczeZwrot;
    }

    public static Zaklad getInstance() {
        return instance;
    }
}

package com.example.myapplication;

public class ModelOceny {
    private String nazwaPrzedmiotu;
    private int ocena;
    public ModelOceny(String nazwa, int ocena){
        this.nazwaPrzedmiotu=nazwa;
        this.ocena=ocena;
    }
    public ModelOceny(String nazwa){
        this.nazwaPrzedmiotu=nazwa;

    }
    public String getNazwa() {
        return nazwaPrzedmiotu;
    }

    public void setNazwa(String nazwa) {
        this.nazwaPrzedmiotu = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}

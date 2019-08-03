package com.esragungor.biletalma.model;

public class Bilet {

    String filmGorsel;
    String filmAdi;
    String biletNumarasi;
    String biletFiyati;
public Bilet(){}
    public Bilet(String filmGorsel, String filmAdi, String biletNumarasi, String biletFiyati) {
        this.filmGorsel = filmGorsel;
        this.filmAdi = filmAdi;
        this.biletNumarasi = biletNumarasi;
        this.biletFiyati = biletFiyati;
    }

    public String getFilmGorsel() {
        return filmGorsel;
    }

    public void setFilmGorsel(String filmGorsel) {
        this.filmGorsel = filmGorsel;
    }

    public String getFilmAdi() {
        return filmAdi;
    }

    public void setFilmAdi(String _filmadi) {
        this.filmAdi = _filmadi;
    }

    public String getBiletNumarasi() {
        return biletNumarasi;
    }

    public void setBiletNumarasi(String biletNumarasi) {
        this.biletNumarasi = biletNumarasi;
    }

    public String getBiletFiyati() {
        return biletFiyati;
    }

    public void setBiletFiyati(String biletFiyati) {
        this.biletFiyati = biletFiyati;
    }
}

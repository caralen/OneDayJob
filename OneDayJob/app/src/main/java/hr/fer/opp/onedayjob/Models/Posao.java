package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Posao implements Serializable {
    private String posaoId;
    private String poslodavacId;
    private String naslov;
    private String opis;
    private String lokacija;
    private Timestamp vrijeme;
    private long trajanje;
    private int ponudeniNovac;
    private boolean posaoGotov;
    private List<Kategorija> kategorije;

    public Posao(String poslodavacId, String naslov, String opis, String lokacija, Timestamp vrijeme, long trajanje, int ponudeniNovac, List<Kategorija> kategorije) {
        this.poslodavacId = poslodavacId;
        this.naslov = naslov;
        this.opis = opis;
        this.lokacija = lokacija;
        this.vrijeme = vrijeme;
        this.trajanje = trajanje;
        this.ponudeniNovac = ponudeniNovac;
        this.kategorije = kategorije;
    }

    // OVO JE KONSTRUKTOR NAPRAVLJEN SAMO ZA POTREBE TESTIRANJA FEEDA
    public Posao(String naslov, Timestamp vrijeme, String opis) {
        this.naslov = naslov;
        this.vrijeme = vrijeme;
        this.opis = opis;
    }

    public String getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(String posaoId) {
        this.posaoId = posaoId;
    }

    public String getPoslodavacId() {
        return poslodavacId;
    }

    public void setPoslodavacId(String poslodavacId) {
        this.poslodavacId = poslodavacId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Timestamp getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Timestamp vrijeme) {
        this.vrijeme = vrijeme;
    }

    public long getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(long trajanje) {
        this.trajanje = trajanje;
    }

    public int getPonudeniNovac() {
        return ponudeniNovac;
    }

    public void setPonudeniNovac(int ponudeniNovac) {
        this.ponudeniNovac = ponudeniNovac;
    }

    public boolean isPosaoGotov() {
        return posaoGotov;
    }

    public void setPosaoGotov(boolean posaoGotov) {
        this.posaoGotov = posaoGotov;
    }
    public List<Kategorija> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<Kategorija> kategorije) {
        this.kategorije = kategorije;
    }

    @Override
    public String toString() {
        return "Posao: \nNaslov:" + naslov + "\nPoslodavac:" + poslodavacId + "\nid: " + posaoId;
    }
}

package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class Posao implements Serializable {
    private long posaoId;
    private long poslodavacId;
    private long posloprimacId;
    private String naslov;
    private String opis;
    private String lokacija;
    private Date vrijeme;

    public long getPosloprimacId() {
        return posloprimacId;
    }

    public void setPosloprimacId(long posloprimacId) {
        this.posloprimacId = posloprimacId;
    }

    public List<Long> getKategorijeID() {
        return kategorijeID;
    }

    public void setKategorijeID(List<Long> kategorijeID) {
        this.kategorijeID = kategorijeID;
    }

    public boolean isPosaoRezerviran() {
        return posaoRezerviran;
    }

    public void setPosaoRezerviran(boolean posaoRezerviran) {
        this.posaoRezerviran = posaoRezerviran;
    }

    private long trajanje;
    private int ponudeniNovac;
    private boolean posaoGotov;
    private List<Long> kategorijeID;
    private boolean posaoRezerviran;

    public Posao(long posaoId, long poslodavacId, long posloprimacId, String naslov, String opis, String lokacija, Date vrijeme, long trajanje, int ponudeniNovac, boolean posaoGotov, List<Long> kategorijeID, boolean posaoRezerviran) {
        this.posaoId = posaoId;
        this.poslodavacId = poslodavacId;
        this.posloprimacId = posloprimacId;
        this.naslov = naslov;
        this.opis = opis;
        this.lokacija = lokacija;
        this.vrijeme = vrijeme;
        this.trajanje = trajanje;
        this.ponudeniNovac = ponudeniNovac;
        this.posaoGotov = posaoGotov;
        this.kategorijeID = kategorijeID;
        this.posaoRezerviran = posaoRezerviran;
    }

    // OVO JE KONSTRUKTOR NAPRAVLJEN SAMO ZA POTREBE TESTIRANJA FEEDA - BRISI
    public Posao(String naslov, Timestamp vrijeme, String opis) {
        this.naslov = naslov;
        this.vrijeme = vrijeme;
        this.opis = opis;
    }

    public long getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(long posaoId) {
        this.posaoId = posaoId;
    }

    public long getPoslodavacId() {
        return poslodavacId;
    }

    public void setPoslodavacId(long poslodavacId) {
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

    public Date getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Date vrijeme) {
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


    @Override
    public String toString() {
        return "Posao: \nNaslov:" + naslov + "\nPoslodavac:" + poslodavacId + "\nid: " + posaoId;
    }
}

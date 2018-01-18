package hr.fer.opp.onedayjob.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


public class Posao implements Serializable {
    @SerializedName("posaoID")
    private long posaoId;
    @SerializedName("poslodavacID")
    private long poslodavacId;
    @SerializedName("posloprimacID")
    private long posloprimacId;
    @SerializedName("naslov")
    private String naslov;
    @SerializedName("opis")
    private String opis;
    @SerializedName("lokacija")
    private String lokacija;
    @SerializedName("vrijeme")
    private long vrijeme;
    @SerializedName("trajanje")
    private long trajanje;
    @SerializedName("ponudeniNovac")
    private int ponudeniNovac;
    @SerializedName("posaoGotov")
    private boolean posaoGotov;
    @SerializedName("kategorijaID")
    private Long kategorijaID;
    @SerializedName("posaoRezerviran")
    private boolean posaoRezerviran;

    public long getPosloprimacId() {
        return posloprimacId;
    }

    public void setPosloprimacId(long posloprimacId) {
        this.posloprimacId = posloprimacId;
    }


    public boolean isPosaoRezerviran() {
        return posaoRezerviran;
    }

    public void setPosaoRezerviran(boolean posaoRezerviran) {
        this.posaoRezerviran = posaoRezerviran;
    }

    public Posao(long posaoId, long poslodavacId, long posloprimacId, String naslov, String opis, String lokacija, long vrijeme, long trajanje, int ponudeniNovac, boolean posaoGotov, Long kategorijaID, boolean posaoRezerviran) {
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
        this.kategorijaID = kategorijaID;
        this.posaoRezerviran = posaoRezerviran;
    }

    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        this.kategorijaID = kategorijaID;
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

    public long getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(long vrijeme) {
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

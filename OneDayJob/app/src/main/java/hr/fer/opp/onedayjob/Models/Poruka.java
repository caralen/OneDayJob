package hr.fer.opp.onedayjob.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Poruka implements Serializable {
    @SerializedName("porukaID")
    private long porukaId;
    @SerializedName("sadrzaj")
    private String sadrzaj;
    @SerializedName("datum")
    private long datum;
    @SerializedName("posiljateljID")
    private long posiljateljId;
    @SerializedName("primateljID")
    private long primateljId;

    public Poruka(long porukaId, String sadrzaj, long datum, long posiljateljId, long primateljId) {
        this.porukaId = porukaId;
        this.sadrzaj = sadrzaj;
        this.datum = datum;
        this.posiljateljId = posiljateljId;
        this.primateljId = primateljId;
    }

    public long getPorukaId() {
        return porukaId;
    }
    public void setPorukaId(long porukaId) {
        this.porukaId = porukaId;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public long getDatum() {
        return datum;
    }

    public void setDatum(long datum) {
        this.datum = datum;
    }

    public long getPosiljateljId() {
        return posiljateljId;
    }

    public void setPosiljateljId(long posiljateljId) {
        this.posiljateljId = posiljateljId;
    }

    public long getPrimateljId() {
        return primateljId;
    }

    public void setPrimateljId(long primateljId) {
        this.primateljId = primateljId;
    }
}

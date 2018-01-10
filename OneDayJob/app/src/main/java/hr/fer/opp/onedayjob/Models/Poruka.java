package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Poruka implements Serializable {
    private long porukaId;
    private String sadrzaj;
    private Date datum;
    private long posiljateljId;
    private long primateljId;

    public Poruka(long porukaId, String sadrzaj, Date datum, long posiljateljId, long primateljId) {
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
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

package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Poruka implements Serializable {
    private String porukaId;
    private String sadrzaj;
    private Date datum;
    private String posiljateljId;
    private String primateljId;

    public String getPorukaId() {
        return porukaId;
    }

    public void setPorukaId(String porukaId) {
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

    public String getPosiljateljId() {
        return posiljateljId;
    }

    public void setPosiljateljId(String posiljateljId) {
        this.posiljateljId = posiljateljId;
    }

    public String getPrimateljId() {
        return primateljId;
    }

    public void setPrimateljId(String primateljId) {
        this.primateljId = primateljId;
    }
}

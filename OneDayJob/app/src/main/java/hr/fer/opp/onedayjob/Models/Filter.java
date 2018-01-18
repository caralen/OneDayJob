package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;

/**
 * Created by Ivan on 18.1.2018..
 */

public class Filter implements Serializable{

    String kategorija;
    Long kategorijaID;





    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}

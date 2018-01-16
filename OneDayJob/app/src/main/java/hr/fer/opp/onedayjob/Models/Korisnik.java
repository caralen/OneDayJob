package hr.fer.opp.onedayjob.Models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Korisnik implements Serializable {

    @SerializedName("korisnikID")
    private long korisnikID;

    @SerializedName("ime")
    private String ime;

    @SerializedName("prezime")
    private String prezime;

    @SerializedName("email")
    private String email;

    @SerializedName("zaporkaHash")
    private String zaporkaHash;

    @SerializedName("opis")
    private String opis;

    @SerializedName("dob")
    private short dob;

    @SerializedName("datumRegistracije")
    private long datumRegistracije;

    @SerializedName("brojTelefona")
    private String brojTelefona;

    @SerializedName("jeValidiran")
    private boolean jeValidiran;

    @SerializedName("jeAdmin")
    private boolean jeAdmin;

    // TODO hash

    public Korisnik(){

    }

    public Korisnik(long korisnikID, String ime, String prezime, String email, String zaporkaHash, short dob, String opis, long datumRegistracije, String brojTelefona, boolean jeValidiran, boolean jeAdmin) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.zaporkaHash = zaporkaHash;
        this.dob = dob;
        this.opis = opis;
        setDatumRegistracije(datumRegistracije);
        this.brojTelefona = brojTelefona;
        this.jeValidiran = jeValidiran;
        this.jeAdmin = jeAdmin;
        Log.d("Korisnik", "Korisnik:radim ");
    }

    public long getkorisnikID() {
        return korisnikID;
    }

    public void setkorisnikID(long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZaporkaHash() {
        return zaporkaHash;
    }

    public void setZaporkaHash(String zaporkaHash) {
        this.zaporkaHash = zaporkaHash;
    }

    public short getDob() {
        return dob;
    }

    public void setDob(short dob) {
        this.dob = dob;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public long getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(long datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public boolean isJeValidiran() {
        return jeValidiran;
    }

    public void setJeValidiran(boolean jeValidiran) {
        this.jeValidiran = jeValidiran;
    }

    public long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public void setJeAdmin(boolean jeAdmin) {
        this.jeAdmin = jeAdmin;
    }

    @Override
    public String toString() {
        return "\nKorisnik: \n" + ime + "\n" + prezime + "\n" + zaporkaHash+  "\n" + email + "\n" + brojTelefona +"\n" + dob + "\n" + jeValidiran + "\n" + datumRegistracije + "\n" + opis;
    }

    public boolean isJeAdmin() {
        return jeAdmin;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Korisnik)){
            return false;
        }
        Korisnik other = (Korisnik) obj;
        return other.getEmail().equals(email);
    }
}

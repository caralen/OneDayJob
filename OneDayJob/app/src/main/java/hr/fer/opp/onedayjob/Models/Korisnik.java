package hr.fer.opp.onedayjob.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Korisnik implements Serializable {
    private String korisnikId;
    private String ime;
    private String prezime;
    private String email;
    private String zaporkaHash;
    private short dob;
    private String opis;
    private Date datumRegistracije;
    private String brojTelefona;
    private boolean jeValidiran;

    // TODO equals i hash


    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String zaporkaHash, short dob, String opis, Date datumRegistracije, String brojTelefona) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.zaporkaHash = zaporkaHash;
        this.dob = dob;
        this.opis = opis;
        this.datumRegistracije = datumRegistracije;
        this.brojTelefona = brojTelefona;
    }

    public String getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(String korisnikId) {
        this.korisnikId = korisnikId;
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

    public Date getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(Date datumRegistracije) {
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

    @Override
    public String toString() {
        return "\nKorisnik: \n" + ime + "\n" + prezime + "\n" + zaporkaHash+  "\n" + email + "\n" + brojTelefona +"\n" + dob + "\n" + jeValidiran + "\n" + datumRegistracije + "\n" + opis;
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

package server.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="korisnik")
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long korisnikID; 
	
	@NotNull
	@NotBlank
	@Column(name = "email",unique = true)
	private String email; 
	
	@NotNull
	@NotBlank
	@Column(name = "ime")
	private String ime; 
	
	@NotNull
	@NotBlank
	@Column(name  = "prezime") 
	private String prezime; 
	
	@NotNull
	@NotBlank
	@Column(name = "zaporkaHash")
	private String zaporkaHash; 
	
	@Column(name = "dob")
	private short dob; 
	
	@Column(name = "opis")
	private String opis; 
	
	@NotNull
	@Column(name = "datumRegistracije")
	private Date datumRegistracije; 
	
	@NotNull
	@NotBlank
	@Column(name = "brojTelefona")
	private String brojTelefona; 
	
	@Column(name = "jeValidiran")
	private boolean jeValidiran;
	
	@Column(name = "jeAdmin")
	private boolean jeAdmin;
	
	public Korisnik() {
		
	}
	
	
	public Korisnik(String email, String ime, String prezime, String zaporkaHash, short dob, String opis,
			Date datumRegistracije, String brojTelefona, boolean jeValidiran, boolean jeAdmin) {
		this.email = email; 
		this.ime = ime;
		this.prezime = prezime;
		this.zaporkaHash = zaporkaHash;
		this.dob = dob;
		this.opis = opis;
		this.datumRegistracije = datumRegistracije;
		this.brojTelefona = brojTelefona;
		this.jeValidiran = jeValidiran;
		this.jeAdmin = jeAdmin;
	}
	
	public long getKorisnikID() {
		return korisnikID;
	}
	
	public String getEmail() {
		return email; 
	}
	
	public void setEmail(String email) {
		this.email = email;
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

	public boolean isJeAdmin() {
		return jeAdmin;
	}
	
	public void setJeAdmin(boolean jeAdmin) {
		this.jeAdmin = jeAdmin;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (korisnikID ^ (korisnikID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (korisnikID != other.korisnikID)
			return false;
		return true;
	}
}

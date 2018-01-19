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
@Table(name = "posao")
public class Posao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long posaoID; 
	
	@Column(name = "poslodavacID")
	private long poslodavacID; 
	
	@Column(name = "posloprimacID")
	private long posloprimacID; 
	
	@NotNull
	@NotBlank
	@Column(name = "naslov")
	private String naslov; 
	
	@NotNull
	@NotBlank
	@Column(name = "opis")
	private String opis; 
	
	@Column(name = "lokacija")
	private String lokacija; 
	
	@NotNull
	@Column(name = "vrijeme")
	private Date vrijeme; 
	
	@Column(name = "trajanje")
	private long trajanje; 
	
	@Column(name = "ponudeniNovac")
	private int ponudeniNovac; 
	
	@Column(name = "posaoGotov")
	private boolean posaoGotov;
	
	@Column(name = "posaoRezerviran")
	private boolean posaoRezerviran; 
	
	@Column(name = "kategorijaID")
	private long kategorijaID; 
	
	public Posao() {
		
	}
	
	public Posao(long poslodavacID,long posloprimacID, String naslov, String opis, String lokacija, Date vrijeme,
			long trajanje, int ponudeniNovac, boolean posaoGotov,boolean posaoRezerviran,long kategorijaID) {
		this.poslodavacID = poslodavacID;
		this.posloprimacID = posloprimacID; 
		this.naslov = naslov;
		this.opis = opis;
		this.lokacija = lokacija;
		this.vrijeme = vrijeme;
		this.trajanje = trajanje;
		this.ponudeniNovac = ponudeniNovac;
		this.posaoGotov = posaoGotov;
		this.posaoRezerviran = posaoRezerviran; 
		this.kategorijaID = kategorijaID; 
	}
	public long getPosaoID() {
		return posaoID;
	}
	public long getPosloprimacID() {
		return posloprimacID; 
	}	
	public void setPosloprimacID(long posloprimacID) {
		this.posloprimacID = posloprimacID; 
	}
	public long getPoslodavacID() {
		return poslodavacID;
	}
	public void setPoslodavacID(long poslodavacID) {
		this.poslodavacID = poslodavacID;
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
	
	public boolean isPosaoRezerviran() {
		return posaoRezerviran;
	}
	public void setPosaoRezerviranv(boolean posaoRezerviran) {
		this.posaoRezerviran = posaoRezerviran;
	}
	
	public long getKategorijaID() {
		return kategorijaID; 
	}
	
	public void setKategorijaID(long kategorijaID) {
		this.kategorijaID = kategorijaID; 
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (posaoID ^ (posaoID >>> 32));
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
		Posao other = (Posao) obj;
		if (posaoID != other.posaoID)
			return false;
		return true;
	}
}

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
@Table(name = "poruka")
public class Poruka implements Comparable<Poruka>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long porukaID; 
	
	@NotNull
	@NotBlank
	@Column(name = "sadrzaj")
	private String sadrzaj; 
	
	@NotNull
	@Column(name = "datum")
	private Date datum; 
	
	@NotNull
	@Column(name = "posiljateljID")
	private long posiljateljID; 
	
	@NotNull
	@Column(name = "primateljID")
	private long primateljID;
	
	public Poruka() {
		
	}
	
	public Poruka(String sadrzaj, Date datum, long posiljateljID, long primateljID) {
		this.sadrzaj = sadrzaj;
		this.datum = datum;
		this.posiljateljID = posiljateljID;
		this.primateljID = primateljID;
	}
	public long getPorukaID() {
		return porukaID;
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
	public long getPosiljateljID() {
		return posiljateljID;
	}
	public void setPosiljateljID(long posiljateljID) {
		this.posiljateljID = posiljateljID;
	}
	public long getPrimateljID() {
		return primateljID;
	}
	public void setPrimateljID(long primateljID) {
		this.primateljID = primateljID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (porukaID ^ (porukaID >>> 32));
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
		Poruka other = (Poruka) obj;
		if (porukaID != other.porukaID)
			return false;
		return true;
	}

	@Override
	public int compareTo(Poruka o) {
		return this.datum.compareTo(o.datum); 
	}
}

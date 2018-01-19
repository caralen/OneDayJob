package server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kategorija")
public class Kategorija {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long kategorijaID; 
	
	@Column(name = "naziv",unique=true)
	public String naziv; 
	
	@Column(name = "opis")
	public String opis;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public long getKategorijaID() {
		return kategorijaID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (kategorijaID ^ (kategorijaID >>> 32));
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
		Kategorija other = (Kategorija) obj;
		if (kategorijaID != other.kategorijaID)
			return false;
		return true;
	} 
}

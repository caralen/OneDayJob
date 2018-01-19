package server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.models.Korisnik;
import server.repository.KorisnikRepository;

@Service
public class KorisnikServis{
	@Autowired
	KorisnikRepository repository; 
	
	public String process() {
		dodajKorisnika(new Korisnik("email1@a.a","ime1", "prezime1", "pass1", (short)15, "desc", new Date(), "912345678", false,false));
		dodajKorisnika(new Korisnik("email2@a.a","ime2", "prezime2", "pass2", (short)15, "desc", new Date(), "912345678", false,false));
		dodajKorisnika(new Korisnik("email3@a.a","ime3", "prezime3", "pass3", (short)15, "desc", new Date(), "912345678", false,false));
		dodajKorisnika(new Korisnik("email4@a.a","ime4", "prezime4", "pass4", (short)15, "desc", new Date(), "912345678", false,false));

		return "Done";
	}
	
	public Korisnik[] dohvatiKorisnikaSImenom(String ime) {
		List<Korisnik> korisnici = new ArrayList<>(repository.findByIme(ime)); 	
		return korisnici.toArray(new Korisnik[korisnici.size()]);
	}
	
	public Korisnik[] dohvatiKorisnikaSPrezimenom(String prezime) {
		List<Korisnik> korisnici = new ArrayList<>(repository.findByPrezime(prezime)); 	
		return korisnici.toArray(new Korisnik[korisnici.size()]);
	}
	
	public Korisnik[] dohvatiKorisnikaSImenomIPrezimenom(String ime, String prezime) {
		List<Korisnik> korisnici = new ArrayList<>(repository.findByImeAndPrezime(ime, prezime)); 	
		return korisnici.toArray(new Korisnik[korisnici.size()]);
	}
	
	public Korisnik[] dohvatiKorisnikaSValidacijom(boolean jeValidiran) {
		List<Korisnik> korisnici = new ArrayList<>(repository.findByJeValidiran(jeValidiran)); 	
		return korisnici.toArray(new Korisnik[korisnici.size()]);
	}
	
	public Korisnik dohvatiKorisnika(long korisnikID) {  
		return repository.findOne(korisnikID);
	}
	
	public Korisnik izmjeniKorisnika(Korisnik korisnik) {
		if (korisnik.getKorisnikID() == 0) {
			return null; 
		}
		
		repository.save(korisnik); 
		return korisnik; 
	}
	
	public Korisnik dodajKorisnika(Korisnik korisnik) {
		if (korisnik.getKorisnikID() != 0) {
			return null; 
		}

		repository.save(korisnik); 
		return korisnik;
	}
	
	public boolean validirajKorsinika(long korisnikID) {
		Korisnik k = repository.findOne(korisnikID); 
		
		if (k == null) {
			return false; 
		}
		
		k.setJeValidiran(true);
		return true;
	}
	
	
	public Korisnik uloggirajKorisnika(String email,String password) {		
		Korisnik k = repository.findByEmail(email); 
		//korisnik ne postoji
		if (k == null) {
			return null; 
		}
		
		//korisnik postoji i password je dobar
		if (k.getZaporkaHash().equals(password)) {
			return k; 
		}
		
		//korisnik postoji i password nije dobar
		return null; 
	}
	
	public boolean obrisiKorisnika(long korisnikID) {
		if (repository.findOne(korisnikID) == null) {
			return false; 
		}
		
		repository.delete(korisnikID);
		return true;
	}
	
	public void deleteAll() {
		repository.deleteAll(); 
	}
	
	public Korisnik[] getAll(){
		List<Korisnik> korisnici = new ArrayList<>(); 	
		repository.findAll().forEach(k -> korisnici.add(k));
		return korisnici.toArray(new Korisnik[korisnici.size()]); 
	}
}

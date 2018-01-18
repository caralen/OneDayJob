package server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.models.Poruka;
import server.repository.PorukaRepository;

@Service
public class PorukaServis {
	@Autowired
	PorukaRepository repository; 
	
	
	public Poruka dohvatiPoruku(Long porukaID) {		
		return repository.findOne(porukaID); 
	}
	
	public boolean dodajPoruku(Poruka poruka) {
		if (poruka.getPorukaID() != 0) {
			return false; 
		}
		
		repository.save(poruka); 
		return true; 
	}
	
	public boolean obrisiPoruku(long porukaID) {
		if (repository.findOne(porukaID) == null) {
			return false; 
		}
		
		repository.delete(porukaID);
		return true; 
	}
	
	public Poruka[] dohvatiPorukuSaKorisnikom(long korisnikID) {
		Set<Poruka> list = 	new TreeSet<>(repository.findByPosiljateljID(korisnikID)); 
		list.addAll(repository.findByPrimateljID(korisnikID)); 
		return list.toArray(new Poruka[list.size()]); 
	}
	
	public Poruka[] dohvatiPorukuSaPosiljateljem(long posiljateljID) {
		List<Poruka> list = 	repository.findByPosiljateljID(posiljateljID); 
		return list.toArray(new Poruka[list.size()]); 
	}
	
	public Poruka[] dohvatiPorukuSaPrimateljem(long primateljID) {
		List<Poruka> list = 	repository.findByPosiljateljID(primateljID); 
		return list.toArray(new Poruka[list.size()]); 
	}
	
	public Poruka[] dohvatiPorukeZaRazgovor(long korisnikID1,long korisnikID2) {
		Set<Poruka> list = new TreeSet<>(repository.findByPrimateljIDAndPosiljateljID(korisnikID1, korisnikID2)); 
		list.addAll(repository.findByPrimateljIDAndPosiljateljID(korisnikID2, korisnikID1)); 
		return list.toArray(new Poruka[list.size()]); 
	}
		
	public Poruka[] getAll() {
		List<Poruka> poruke = new ArrayList<>(); 	
		repository.findAll().forEach(p -> poruke.add(p));
		return poruke.toArray(new Poruka[poruke.size()]); 
	}
	
	public void init() {
		repository.save(new Poruka("poruka1", new Date(), 1, 2)); 
		repository.save(new Poruka("poruka2", new Date(), 2, 1)); 
		repository.save(new Poruka("poruka3", new Date(), 2, 1)); 
		repository.save(new Poruka("poruka4", new Date(), 1, 2)); 
		repository.save(new Poruka("poruka", new Date(), 3, 2)); 
	}
	
	public void deleteAll() {
		repository.deleteAll(); 
	}

	public Long[] dohvatiRazgovore(long korisnikID) {
		Set<Long> korisnici = new HashSet<>(); 
		
		for (Poruka poruka : repository.findByPosiljateljID(korisnikID)){
			korisnici.add(poruka.getPrimateljID()); 
		}
		
		for (Poruka poruka : repository.findByPrimateljID(korisnikID)){ 
			korisnici.add(poruka.getPosiljateljID()); 
		}
		
		return korisnici.toArray(new Long[korisnici.size()]);
	}
}

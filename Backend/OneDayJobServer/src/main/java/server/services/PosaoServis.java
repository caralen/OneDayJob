package server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.models.Posao;
import server.repository.PosaoRepositary;

@Service
public class PosaoServis {
	@Autowired
	PosaoRepositary repository; 
	
	public boolean dodajPosao(Posao posao) {
		if (posao.getPosaoID() != 0) {
			return false; 
		}
		
		System.out.println(posao.getPoslodavacID());
		System.out.println(posao.getPosloprimacID());
		repository.save(posao); 
		return true; 
	}
	
	public Posao[] filtrirajPoPoslodavcuIPosloprimcu(long posloprimacID, long poslodavacID) {
		List<Posao> list = repository.findByPoslodavacIDAndPosloprimacID(poslodavacID, posloprimacID);
		return list.toArray(new Posao[list.size()]);
	}
	
	public Posao dohvatiPosao(long posaoID) {
		return repository.findOne(posaoID); 
	}
	
	public Posao[] filtrirajPoKorisniku(long korisnik) {
		List<Posao> list = repository.findByPosloprimacID(korisnik);
		list.addAll(repository.findByPoslodavacID(korisnik)); 
		return list.toArray(new Posao[list.size()]); 
	}
	
	public Posao[] filtrirajPoPosloprimcu(long posloprimacID) {
		List<Posao> list = repository.findByPosloprimacID(posloprimacID);
		return list.toArray(new Posao[list.size()]); 
	}
	
	public Posao[] filtrirajPoPoslodavcu(long poslodavacID) {
		List<Posao> list = repository.findByPoslodavacID(poslodavacID);
		return list.toArray(new Posao[list.size()]); 
	}
	
	
	public Posao[] filtrirajPoKategoriji(long kategorijaID) {
		List<Posao> list = repository.findByKategorijaIDAndPosaoRezerviran(kategorijaID, false); 
		return list.toArray(new Posao[list.size()]); 
	}
	
	public Posao[] filtrirajPoPosaoGotov(boolean posaoGotov) {
		List<Posao> list = repository.findByPosaoGotov(posaoGotov); 
		return list.toArray(new Posao[list.size()]); 
	}
	
	public Posao[] filtrirajPoPosaoRezerviran(boolean posaoRezerviran) {
		List<Posao> list = repository.findByPosaoGotov(posaoRezerviran); 
		return list.toArray(new Posao[list.size()]); 
	}
	
	public boolean rezervirajPosao(long posaoID) {
		Posao posao = repository.findOne(posaoID); 

		if (posao == null) {
			return false; 
		}
		
		if (posao.isPosaoRezerviran()) {
			return false;
		}
		
		posao.setPosaoRezerviranv(true);
		repository.save(posao); 
		return true;
	}
	
	public boolean zavrsiPosao(long posaoID) {
		Posao posao = repository.findOne(posaoID); 
		
		if (posao == null) {
			return false; 
		}
		
		if (posao.isPosaoGotov()) {
			return false; 
		}
		
		posao.setPosaoGotov(true);
		repository.save(posao); 
		return true; 
	}
	
	public boolean urediPosao(Posao posao) {
		Posao posaoStari = repository.findOne(posao.getPosaoID()); 
		
		if (posaoStari == null) {
			return false; 
		}
		
		repository.save(posao); 
		return true; 
	}
	
	public Posao[] getAll(){
		List<Posao> posao = new ArrayList<>(); 	
		repository.findAll().forEach(p -> posao.add(p));
		return posao.toArray(new Posao[posao.size()]); 
	}
	
	public boolean obrisiPosao(long posaoID) {
		if (repository.findOne(posaoID) == null) {
			return false; 
		}
		
		repository.delete(posaoID);
		return true; 
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	public void init() {
		repository.save(new Posao(1, 2, "naslov", "opis", "lokacija", new Date(), 1, 50, false, true, 1)); 
		repository.save(new Posao(1, 2, "sredit sobu", "opis", "lokacija", new Date(), 1, 50, false, true, 1)); 
		repository.save(new Posao(1, 2, "uredit vrt", "opis", "lokacija", new Date(), 1, 50, true, true, 1)); 
		repository.save(new Posao(1, 2, "zalit cvijece", "opis", "lokacija", new Date(), 1, 50, false, false, 1)); 

	}
}

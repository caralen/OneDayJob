package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.models.Poruka;
import server.services.PorukaServis;

@RestController
public class PorukaController {
	@Autowired
	private PorukaServis porukaServis; 
	
	@RequestMapping(method=RequestMethod.POST,value= "/posaljiPoruku")
	public boolean dodajPoruku(@RequestBody Poruka poruka) {
		 return porukaServis.dodajPoruku(poruka); 
	}
	
	@RequestMapping("/poruka/{porukaID}/detalji")
	public Poruka getPoruka(@PathVariable long porukaID) {
		return porukaServis.dohvatiPoruku(porukaID); 
	}
	
	@RequestMapping("/poruka/{porukaID}/obrisi")
	public boolean obrisiPoruku(@PathVariable long porukaID) {
		return  porukaServis.obrisiPoruku(porukaID);
	}
	
	@RequestMapping("/korisnik/{korisnikID}/poruke")
	public Poruka[] getPoruke(@PathVariable long korisnikID) {
		return porukaServis.dohvatiPorukuSaKorisnikom(korisnikID); 
	}
	
	@RequestMapping(value = "/poruke" ,params = {"korisnikID1" , "korisnikID2"})
	public Poruka[] getPorukeZaRazgovor(@RequestParam long korisnikID1, @RequestParam long korisnikID2) {
		return porukaServis.dohvatiPorukeZaRazgovor(korisnikID1, korisnikID2); 
	}
	
	@RequestMapping(value = "korisnik/{korisnikID}/razgovori")
	public Long[] getRazgovori(@PathVariable long korisnikID) {
		return porukaServis.dohvatiRazgovore(korisnikID); 
	}
	
	@RequestMapping("/poruke/deleteAll")
	public String deleteAll() {
		porukaServis.deleteAll(); 
		return "pobrisane sve poruke";
	}
	
	@RequestMapping("/poruka/init")
	public String initPoruke() {
		porukaServis.init(); 
		return "Poruke initializirane";
	}
}

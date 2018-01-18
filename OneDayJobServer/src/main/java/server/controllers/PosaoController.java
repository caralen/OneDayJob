package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server.models.Posao;
import server.services.PosaoServis;

@RestController
public class PosaoController {
	@Autowired
	private PosaoServis posaoServis; 
	
	@RequestMapping(method=RequestMethod.POST,value= "/dodajPosao")
	public boolean dodajPosao(@RequestBody Posao posao) {
		 return posaoServis.dodajPosao(posao);
	}
	
	@RequestMapping("/posao/{posaoID}/detalji")
	public Posao getPosao(@PathVariable long posaoID) {
		return posaoServis.dohvatiPosao(posaoID); 
	}
	
	@RequestMapping("/posao/aktivni/")
	public Posao[] getAktivniPoslovi() {
		return posaoServis.filtrirajPoPosaoRezerviran(false); 
	}
	
	@RequestMapping("/posao/rezervirani/")
	public Posao[] getRezerviraniPoslovi() {
		return posaoServis.filtrirajPoPosaoRezerviran(true); 
	}
	
	@RequestMapping("/posao/gotovi/")
	public Posao[] getGotoviPoslovi() {
		return posaoServis.filtrirajPoPosaoGotov(true); 
	}
	
	@RequestMapping("/posao/neGotovi/")
	public Posao[] getNeGotoviPoslovi() {
		return posaoServis.filtrirajPoPosaoGotov(false); 
	}
	
	@RequestMapping("/posao/zaKategoriju/{kategorijaID}")
	public Posao[] getPosloviZaKategoriju(@PathVariable long kategorijaID) {
		return posaoServis.filtrirajPoKategoriji(kategorijaID); 
	}
	
	
	
	
	@RequestMapping("/posao/getAll")
	public Posao[]  getAll() {
		return posaoServis.getAll();
	}
	
	@RequestMapping("/korisnik/{korisnikID}/poslovi")
	public Posao[] dohvatiPosloveOd(@PathVariable long korisnikID) {
		return posaoServis.filtrirajPoKorisniku(korisnikID); 
	}
	
	@RequestMapping("/korisnik/{korisnikID}/posloprimac")
	public Posao[] dohvatiPosloveGdjeJePosloprimac(@PathVariable long korisnikID) {
		return posaoServis.filtrirajPoPosloprimcu(korisnikID); 
	}
	
	@RequestMapping("/korisnik/{korisnikID}/poslodavac")
	public Posao[] dohvatiPosloveGdjeJePoslodavac(@PathVariable long korisnikID) {
		return posaoServis.filtrirajPoPoslodavcu(korisnikID); 
	}
	
	@RequestMapping("posao/{posaoID}/rezerviraj")
	public boolean rezervirajPosao(@PathVariable long posaoID) {
		return posaoServis.rezervirajPosao(posaoID);
	}
	
	@RequestMapping("posao/{posaoID}/zavrsi")
	public boolean zavrsiPosao(@PathVariable long posaoID) {
		return posaoServis.zavrsiPosao(posaoID);
	}
	
	
	@RequestMapping("posao/{posaoID}/obrisi")
	public boolean obrisiPosao(@PathVariable long posaoID){
		return posaoServis.obrisiPosao(posaoID); 
	}
	
	@RequestMapping("/posao/deleteAll")
	public String deleteAll() {
		posaoServis.deleteAll();
		return "poslovi svi pobrisani"; 
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/posao/update")
	public boolean updatePosao(@RequestBody Posao posao) {
		return posaoServis.urediPosao(posao);  
	}
	
	@RequestMapping("/posao/init")
	public String initPoslovi() {
		posaoServis.init(); 
		return "Poslovi dodani"; 
	}
}

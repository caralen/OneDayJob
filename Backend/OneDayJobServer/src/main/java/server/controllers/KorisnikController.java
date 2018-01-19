package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.models.Korisnik;
import server.models.KorisnikZaporka;
import server.services.KorisnikServis;
import server.services.MailServis;

@RestController
public class KorisnikController {
	@Autowired
	private KorisnikServis korisnikServis; 
	
	
	@RequestMapping(method=RequestMethod.POST,value= "/register")
	public Korisnik registrirajKorisnik(@RequestBody Korisnik korisnik) {
		 return korisnikServis.dodajKorisnika(korisnik);
	}
	
	@RequestMapping(method=RequestMethod.POST,value= "/login")
	public Korisnik uloggirajKorisnika(@RequestBody KorisnikZaporka korisnikZaporka) {
		return korisnikServis.uloggirajKorisnika(korisnikZaporka.getEmail(), korisnikZaporka.getPassword()); 
	}
	
	private String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
		
	@RequestMapping(value = "/verify",params = "email")
	public String posaljiVerifikacijskiKod(@RequestParam("email") String email) {
		String hash =MD5(email).substring(0, 5); 
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailServis mm = (MailServis) context.getBean("mailMail");
		mm.sendMail("oneDayJobMail@gmail.com", email, "Verifikacijski kod", "Verifikacijski kod: " + hash);
		context.close();
		
		return hash; 
	}
	
	@RequestMapping("/korisnik/{korisnikID}/obrisi")
	public boolean obrisiKorisnik(@PathVariable long korisnikID) {
		return korisnikServis.obrisiKorisnika(korisnikID); 
	}
	
	@RequestMapping("/korisnik/{korisnikID}/detalji")
	public Korisnik dohvatiKorisnik(@PathVariable long korisnikID) {
		return korisnikServis.dohvatiKorisnika(korisnikID); 
	}
	
	@RequestMapping(value = "/korisnik" ,params = {"ime" , "prezime"})
	public Korisnik[] dohvatiKorisnikeSImenomIPrezimenom(@RequestParam("ime") String ime ,@RequestParam("prezime") String prezime) {
		return korisnikServis.dohvatiKorisnikaSImenomIPrezimenom(ime, prezime);
	}
	
	@RequestMapping(value = "/korisnik" ,params = "ime")
	public Korisnik[] dohvatiKorisnikeSImenom(@RequestParam("ime") String ime) {
		return korisnikServis.dohvatiKorisnikaSImenom(ime); 
	}
	
	@RequestMapping(value = "/korisnik" ,params = "prezime")
	public Korisnik[] dohvatiKorisnikeSPrezimenom(@RequestParam("prezime") String prezime) {
		return korisnikServis.dohvatiKorisnikaSImenom(prezime); 
	}
	
	@RequestMapping(method=RequestMethod.POST , value = "/korisnik/update") //ovo je mozda glupo pitaj tina
	public Korisnik izmjeniKorisnika(@RequestBody Korisnik korisnik) {
		return korisnikServis.izmjeniKorisnika(korisnik);
	}
	
	@RequestMapping("/korisnik/init")
	public String initializeKorisnik() {
		korisnikServis.process(); 
		return "korisnici dodani";
	}
	
	@RequestMapping("/korisnik/getAll")
	public Korisnik[]  getAll() {
		return korisnikServis.getAll();
	}
	
	@RequestMapping("/korisnik/deleteAll")
	public String deleteAllAll() {
		korisnikServis.deleteAll();
		return "deleted";
	}
	
}

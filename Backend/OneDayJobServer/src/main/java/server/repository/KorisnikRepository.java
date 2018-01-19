package server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import server.models.Korisnik;


public interface KorisnikRepository extends CrudRepository<Korisnik, Long>{
	List<Korisnik> findByPrezime(String prezime); 
	List<Korisnik> findByIme(String ime); 
	List<Korisnik> findByJeValidiran(boolean jeValidiran); 
	List<Korisnik> findByImeAndPrezime(String ime,String prezime); 
	Korisnik findByEmail(String email); 
}

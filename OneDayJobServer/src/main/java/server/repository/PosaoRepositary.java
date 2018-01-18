package server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import server.models.Posao;

public interface PosaoRepositary extends CrudRepository<Posao,Long>{
	List<Posao> findByPoslodavacID(long poslodavacID); 
	List<Posao> findByPosloprimacID(long posloprimacID); 
	List<Posao> findByPoslodavacIDAndPosloprimacID(long poslodavac,long posloprimac); 
	List<Posao> findByKategorijaID(long kategorijaID);
	List<Posao> findByPosaoRezerviran(boolean posaoRezerviran); 
	List<Posao> findByPosaoGotov(boolean posaoGotov); 
	List<Posao> findByKategorijaIDAndPosaoRezerviran(long kategorijaID,boolean posaoRezerviran); 
}

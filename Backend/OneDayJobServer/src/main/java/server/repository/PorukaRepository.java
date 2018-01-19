package server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import server.models.Poruka;

public interface PorukaRepository extends CrudRepository <Poruka, Long>{
	List<Poruka> findByPosiljateljID(long posiljateljID); 
	List<Poruka> findByPrimateljID(long primateljID); 
	List<Poruka> findByPrimateljIDAndPosiljateljID(long posiljateljID,long primateljID); 
}

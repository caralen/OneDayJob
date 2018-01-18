package server.repository;

import org.springframework.data.repository.CrudRepository;

import server.models.Kategorija;

public interface KategorijaRepository extends CrudRepository<Kategorija, Long> {
	
}

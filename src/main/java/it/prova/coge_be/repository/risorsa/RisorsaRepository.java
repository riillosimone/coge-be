package it.prova.coge_be.repository.risorsa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.coge_be.model.Risorsa;

public interface RisorsaRepository extends CrudRepository<Risorsa, Long>{

	
	@Query("from Risorsa r join fetch r.commesse c")
	List<Risorsa> getAllEagerWithCommesse();
	
	@Query("from Risorsa r join fetch r.commesse c where r.id =?1")
	Risorsa getSingleEagerWithCommesse(Long id);
	
	@Query("from Risorsa r join fetch r.attachment_id a where r.id = ?1")
	Risorsa getSingleEagerWithAttachment(Long id);
	
	@Query("from Risorsa r join fetch r.attachment_id a")
	List<Risorsa> getAllEagerWithAttachment();
	
	@Query("from Risorsa r join fetch r.commesse c join fetch r.attachment_id a")
	List<Risorsa> getAllEager();
	
	@Query("from Risorsa r join fetch r.commesse c join fetch r.attachment_id a where r.id = ?1")
	Risorsa getSingleEager(Long id);
}

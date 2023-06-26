package it.prova.coge_be.repository.azienda;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.coge_be.model.Azienda;


public interface AziendaRepository extends CrudRepository<Azienda, Long>{
	
	
	@Query(value = "select a.* from azienda a\r\n"
			+ "left join azienda_commesse ac on a.id=ac.azienda_id\r\n"
			+ "left join commessa c on ac.commesse_id=c.id\r\n"
			+ "group by (a.id) order by sum(c.importo) desc", nativeQuery = true)
	public List<Azienda> findAziendeConCostoDesc();
	
	
	
	

}

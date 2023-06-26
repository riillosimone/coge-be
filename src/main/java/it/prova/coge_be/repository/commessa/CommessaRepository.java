package it.prova.coge_be.repository.commessa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.prova.coge_be.dto.commessaMargine.ICommessaMargineDTO;
import it.prova.coge_be.model.Commessa;

@Repository
public interface CommessaRepository extends CrudRepository<Commessa, Long> {
	
	
	@Query(value = "SELECT c.*, subquery.somma_margine " +
	        "FROM commessa c " +
	        "JOIN ( " +
	        "   SELECT c.id, SUM(c.importo - (r.costogiornaliero * rn.numerogiorni)) AS somma_margine " +
	        "   FROM commessa c " +
	        "   JOIN commessa_risorsa rc ON c.id = rc.commessa_id " +
	        "   JOIN risorsa r ON rc.risorsa_id = r.id " +
	        "   JOIN rapportino rn ON rn.risorsa_id = r.id " +
	        "   WHERE c.data_out IS NOT NULL " +
	        "   GROUP BY c.id " +
	        ") subquery ON c.id = subquery.id " +
	        "ORDER BY subquery.somma_margine DESC", nativeQuery = true)
	List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente();
	
}

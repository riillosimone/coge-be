package it.prova.coge_be.repository.commessa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.prova.coge_be.dto.commessaMargine.ICommessaMargineDTO;
import it.prova.coge_be.model.Commessa;

@Repository
public interface CommessaRepository extends CrudRepository<Commessa, Long> {

	@Query(value = "SELECT c.*,margine\r\n" + "FROM commessa c\r\n" + "JOIN (\r\n"
			+ "  SELECT c.id, SUM(c.importo - (r.costogiornaliero * rn.numerogiorni)) AS margine\r\n"
			+ "  FROM commessa c\r\n" + "  join commessa_risorsa rc on c.id=rc.commessa_id\r\n"
			+ "  join risorsa r on rc.risorsa_id=r.id\r\n" + "  join rapportino rn on rn.risorsa_id = r.id\r\n"
			+ " where c.data_out is not null " + "  GROUP BY c.id  \r\n" + ") subquery ON c.id = subquery.id\r\n"
			+ "ORDER BY subquery.margine DESC;", nativeQuery = true)
	List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente();

}

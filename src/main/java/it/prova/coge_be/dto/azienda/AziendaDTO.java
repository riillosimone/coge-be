package it.prova.coge_be.dto.azienda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.coge_be.dto.commessa.CommessaDTO;
import it.prova.coge_be.model.Azienda;
import it.prova.coge_be.model.Commessa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AziendaDTO {
	private Long id;
	
	@NotBlank(message = "{ragionesociale.notblank}")
	private String ragioneSociale;
	
	@NotBlank(message = "{partitaIva.notblank}")
	private String partitaIva;
	
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;
	
	private List<CommessaDTO> commesse = new ArrayList<>();

	
	
	
	public Azienda buildAziendaModel() {
		return Azienda.builder().id(this.id).ragioneSociale(this.ragioneSociale).partivaIva(this.partitaIva).indirizzo(this.indirizzo).build();
	}
	
	
	public static AziendaDTO buildAziendaDTOFromModel(Azienda aziendaModel) {
		AziendaDTO result= AziendaDTO.builder().id(aziendaModel.getId()).ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartivaIva()).indirizzo(aziendaModel.getIndirizzo()).build();
		return result;
		
	}
	
	public static List<AziendaDTO> createAziendaDTOFromModelList(List<Azienda> modelListInput){
		return modelListInput.stream().map(aziendaEntity -> {
			AziendaDTO result = AziendaDTO.buildAziendaDTOFromModel(aziendaEntity);
			
			return result;
		}).collect(Collectors.toList());
	}
	
	public static Set<Commessa> createCommessaSetFromDTOList(List<CommessaDTO> modelListInput) {
		return modelListInput.stream().map(commessaItem -> {
		return commessaItem.buildCommessaModel();
		}).collect(Collectors.toSet());
		}
	
	
	


}

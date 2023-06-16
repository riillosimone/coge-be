package it.prova.coge_be.dto.commessa;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.coge_be.dto.azienda.AziendaDTO;
import it.prova.coge_be.model.Commessa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Json include, se un dato è nullo non cerrà messo nell'output
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommessaDTO {

	@NotNull(message = "{id.notnull}")
	@Min(0)
	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	@NotNull(message = "{dataIn.notnull}")
	private LocalDate dataIn;
	@NotNull(message = "{dataOut.notnull}")
	private LocalDate dataOut;

	@NotNull(message = "{importo.notnull}")
	@Min(0)
	private Double importo;
	
	
	private AziendaDTO azienda;
	
	
	
	

	

	public Commessa buildCommessaModel() {
		Commessa result = Commessa.builder().id(this.id).descrizione(this.descrizione).codice(this.codice)
				.dataIn(this.dataIn).dataOut(this.dataOut).importo(this.importo)
				.build();
		return result;
	}

	public static CommessaDTO buildCommessaDTOFromModel(Commessa commessaModel) {

		CommessaDTO result = CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto()).build();
		return result;
	}
	
	public static List<CommessaDTO> createCommessaDTOListFromModelList(List<Commessa> modelListInput){
		return modelListInput.stream().map(inputEntity -> {
			return CommessaDTO.buildCommessaDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}
	
	public static Set<CommessaDTO> createCommessaDTOSetFromModelSet(Set<Commessa> modelListInput) {
		return modelListInput.stream().map(commessaItem -> {
		return CommessaDTO.buildCommessaDTOFromModel(commessaItem);
		}).collect(Collectors.toSet());
		}

}

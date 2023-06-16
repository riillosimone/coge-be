package it.prova.coge_be.dto.rapportino;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.coge_be.dto.commessa.CommessaDTO;
import it.prova.coge_be.model.Rapportino;
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
public class RapportinoDTO {
	
	@NotNull(message = "{id.notnull}")
	@Min(0)
	private Long id;
	
	@NotNull(message = "{numeroGiorni.notnull}")
	@Min(0)
	private Integer numeroGiorni;
	
	@Valid
	private CommessaDTO commessa;
	
	public Rapportino buildRapportinoModel() {
		Rapportino result = Rapportino.builder().id(this.id).numeroGiorni(this.numeroGiorni).commessa(this.commessa.buildCommessaModel()).build();
		return result;
	}
	
	public static RapportinoDTO buildRapportinoDTOFromModel(Rapportino rapportinoModel) {
		RapportinoDTO result = RapportinoDTO.builder().id(rapportinoModel.getId()).numeroGiorni(rapportinoModel.getNumeroGiorni()).commessa(CommessaDTO.buildCommessaDTOFromModel(rapportinoModel.getCommessa())).build();
		return result;
	}
	
	public static List<RapportinoDTO> createRapportinoDTOListFromModelList(List<Rapportino> modelListInput){
		return modelListInput.stream().map(inputEntity -> {
			return RapportinoDTO.buildRapportinoDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}
	
	
}

package it.prova.coge_be.dto.risorsa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.coge_be.dto.attachment.AttachmentDTO;
import it.prova.coge_be.model.Risorsa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RisorsaDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;// obbligatoria

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;// obbligatoria

	private LocalDate dataIn;

	private LocalDate dataOut;

	@NotBlank(message = "{cf.notblank}")
	private String cf;// obbligatoria

	private String email;

	private Integer costoGiornaliero;

	private AttachmentDTO cv;

	public Risorsa buildModelFromDTO() {
		return Risorsa.builder().id(this.id).nome(this.nome).cognome(this.cognome).dataIn(this.dataIn)
				.dataOut(this.dataOut).cf(this.cf).email(this.email).costoGiornaliero(this.costoGiornaliero)
				.cv(this.cv.buildModelFromDTO()).build();
	}

	public static RisorsaDTO buildRisorsaDTOFromModel(Risorsa risorsaModel) {
		RisorsaDTO result = RisorsaDTO.builder().id(risorsaModel.getId()).nome(risorsaModel.getNome())
				.cognome(risorsaModel.getCognome()).dataIn(risorsaModel.getDataIn()).dataOut(risorsaModel.getDataOut())
				.cf(risorsaModel.getCf()).email(risorsaModel.getEmail())
				.costoGiornaliero(risorsaModel.getCostoGiornaliero())
				.cv(AttachmentDTO.buildAttachmentDTOFromModel(risorsaModel.getCv())).build();
		return result;
	}

	public static List<RisorsaDTO> createRisorsaDTOListFromModelList(List<Risorsa> modelListInput) {
		return modelListInput.stream().map(risorsaItem -> {
			return RisorsaDTO.buildRisorsaDTOFromModel(risorsaItem);
		}).collect(Collectors.toList());
	}

}
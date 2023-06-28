package it.prova.coge_be.dto.risorsa;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.coge_be.dto.commessa.CommessaDTO;
import it.prova.coge_be.model.Risorsa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsertRisorsaDTO {

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
	
	private byte[] cv;
	
	
	
	public Risorsa buildModelFromDTO() {
		return Risorsa.builder().id(this.id).nome(this.nome).cognome(this.cognome).dataIn(this.dataIn)
				.dataOut(this.dataOut).cf(this.cf).email(this.email).costoGiornaliero(this.costoGiornaliero)
				.build();
	}
}

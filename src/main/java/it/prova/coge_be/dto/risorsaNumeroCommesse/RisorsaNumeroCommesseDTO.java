package it.prova.coge_be.dto.risorsaNumeroCommesse;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.prova.coge_be.dto.attachment.AttachmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RisorsaNumeroCommesseDTO {
	private Long id;

	private String nome;// obbligatoria

	private String cognome;// obbligatoria

	private LocalDate dataIn;

	private LocalDate dataOut;

	private String cf;// obbligatoria

	private String email;

	private Integer costoGiornaliero;

	private Long cv_id;
	private Integer numeroCommesse;
	
	
	

	

}

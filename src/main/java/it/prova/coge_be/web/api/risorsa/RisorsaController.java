package it.prova.coge_be.web.api.risorsa;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.coge_be.dto.risorsa.RisorsaDTO;
import it.prova.coge_be.model.Attachment;
import it.prova.coge_be.dto.risorsaNumeroCommesse.IRisorsaNumeroCommesseDTO;
import it.prova.coge_be.model.Risorsa;
import it.prova.coge_be.service.risorsa.RisorsaService;
import it.prova.coge_be.web.api.exception.IdNotNullForInsertException;
import it.prova.coge_be.web.api.exception.RisorsaNotFoundException;

@RestController
@RequestMapping("/api/risorsa")
@CrossOrigin
public class RisorsaController {

	@Autowired
	private RisorsaService service;

	@GetMapping
	public List<RisorsaDTO> getAll() {
		return RisorsaDTO.createRisorsaDTOListFromModelList(service.listAllElements());
	}

	@GetMapping("/{id}")
	public RisorsaDTO getSingleEager(@PathVariable(value = "id", required = true) Long id) {
		return RisorsaDTO.buildRisorsaDTOFromModel(service.caricaSingoloElementoEager(id));
	}
	
	
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RisorsaDTO inserisciNuovo(@Valid @RequestBody RisorsaDTO risorsaInstance) {

		if (risorsaInstance.getId() != null)
			throw new IdNotNullForInsertException("impossibile creare un id per la creazione.");

		Attachment cv = new Attachment();
		cv.setPayload(risorsaInstance.getCv().getPayload());
		Risorsa risorsaModel = risorsaInstance.buildModelFromDTO();
		Risorsa risorsaInserita = service.inserisciNuovo(risorsaModel);

		return RisorsaDTO.buildRisorsaDTOFromModel(service.caricaSingoloElemento(risorsaInserita.getId()));
	}

	@PutMapping("/edit/{id}")
	public RisorsaDTO update(@PathVariable(required = true) Long id, @Valid @RequestBody RisorsaDTO risorsaInput) {

		risorsaInput.setId(id);
		Risorsa risorsaAggiornata = service.aggiorna(risorsaInput.buildModelFromDTO());
		return RisorsaDTO.buildRisorsaDTOFromModel(risorsaAggiornata);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Risorsa risorsaDaEliminare = service.caricaSingoloElemento(id);

		if (risorsaDaEliminare == null)
			throw new RisorsaNotFoundException("non è stato trovato alcun dottore.");

		service.rimuoviById(risorsaDaEliminare.getId());
	}
	
	@GetMapping("/riepilogorisorse")
	public List<IRisorsaNumeroCommesseDTO> riepilogoRisorseConCommesse() {
		
		List<IRisorsaNumeroCommesseDTO> risorseConCommesse= service.riepilogoRisorseConCommesse();
		
		return risorseConCommesse;
		
		
	}

}

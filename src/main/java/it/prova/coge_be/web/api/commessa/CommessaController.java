package it.prova.coge_be.web.api.commessa;

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

import it.prova.coge_be.dto.commessa.CommessaDTO;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import it.prova.coge_be.model.Azienda;
=======
import it.prova.coge_be.dto.commessaMargine.CommessaMargineDTO;
import it.prova.coge_be.dto.commessaMargine.ICommessaMargineDTO;
>>>>>>> Stashed changes
=======
import it.prova.coge_be.dto.commessa.CommessaPerInsertDTO;
import it.prova.coge_be.model.Azienda;
>>>>>>> Stashed changes
import it.prova.coge_be.model.Commessa;
import it.prova.coge_be.service.azienda.AziendaService;
import it.prova.coge_be.service.commessa.CommessaService;

@RestController
@RequestMapping("api/commessa")
@CrossOrigin
public class CommessaController {
	
	@Autowired
	private CommessaService commessaService;
	
<<<<<<< Updated upstream
=======
	@Autowired
	private AziendaService aziendaService;
	
	
>>>>>>> Stashed changes
	@GetMapping
	public List<CommessaDTO> visualizzaCommesse(){
		return CommessaDTO.createCommessaDTOListFromModelList(commessaService.listAll(), true, false);
		
	}
	
	@GetMapping("/{id}")
	public CommessaDTO visualizza (@PathVariable(required = true) Long id) {
		return CommessaDTO.buildCommessaDTOFromModel(commessaService.caricaSingoloElemento(id), false, false);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommessaDTO createNew(@Valid @RequestBody CommessaPerInsertDTO commessaInput) {
	     if(commessaInput.getId() != null) {
	    	 throw new RuntimeException();
	     }
		Azienda aziendaCaricata = aziendaService.caricaSingolo(commessaInput.getAzienda_id());
		Commessa commessa = new Commessa();
		commessa.setDescrizione(commessaInput.getDescrizione());
		commessa.setCodice(commessaInput.getCodice());
		commessa.setDataIn(commessaInput.getDataIn());
		commessa.setDataOut(commessaInput.getDataOut());
		commessa.setImporto(commessaInput.getImporto());
		commessa.setAzienda(aziendaCaricata);
		Commessa commessaInserita = commessaService.aggiorna(commessa);
		
		return CommessaDTO.buildCommessaDTOFromModel(commessaInserita, false, false);
		
		
<<<<<<< Updated upstream
		Commessa commessaInserita = commessaService.inserisciNuovo(commessaInput.buildCommessaModel());
		return CommessaDTO.buildCommessaDTOFromModel(commessaInserita, true, false);
=======
		
>>>>>>> Stashed changes
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommessaDTO update (@PathVariable Long id,@Valid @RequestBody CommessaPerInsertDTO commessaInput) {
		
		Commessa commessaEsistente = commessaService.caricaSingoloElemento(id);
		if(commessaEsistente == null) {
			throw new RuntimeException("Commessa non trovata con id: " + id);
		}
		
		Azienda aziendaCaricata = aziendaService.caricaSingolo(commessaInput.getAzienda_id());
		commessaEsistente.setDescrizione(commessaInput.getDescrizione());
		commessaEsistente.setCodice(commessaInput.getCodice());
		commessaEsistente.setDataIn(commessaInput.getDataIn());
		commessaEsistente.setDataOut(commessaInput.getDataOut());
		commessaEsistente.setImporto(commessaInput.getImporto());
		commessaEsistente.setAzienda(aziendaCaricata);
		
		Commessa commessaAggiornata = commessaService.aggiorna(commessaEsistente);
		
		return CommessaDTO.buildCommessaDTOFromModel(commessaAggiornata, false, false);
		
		
		
	
    }
	
		@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		commessaService.rimuovi(id);
	}
		
		
		
	@GetMapping("/commessechiusemarginedecrescente")
	public List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente() {
		List<ICommessaMargineDTO> listaCommesseChiuseConMargineDecr= commessaService.commesseChiuseConMargineDecrescente();
		
		return listaCommesseChiuseConMargineDecr;
		
	}
		
		
		
	
}

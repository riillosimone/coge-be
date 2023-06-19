package it.prova.coge_be.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.coge_be.dto.risorsa.RisorsaDTO;
import it.prova.coge_be.service.risorsa.RisorsaService;

@RestController
@RequestMapping("/api/risorsa")
public class RisorsaController {
	
	@Autowired
	private RisorsaService service;
	
	@GetMapping
	public List<RisorsaDTO> getAll() {
		return RisorsaDTO.createRisorsaDTOListFromModelList(service.listAllElements(), false, false);
	}
	
	@GetMapping("/{id}")
	public RisorsaDTO getSingleEager(@PathVariable(value = "id", required = true) Long id) {
		return RisorsaDTO.buildRisorsaDTOFromModel(service.caricaSingoloElementoEager(id), true, true);
	}
	
	
	
	
	
	
	
	
}

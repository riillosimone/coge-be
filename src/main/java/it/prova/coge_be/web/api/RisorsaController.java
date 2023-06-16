package it.prova.coge_be.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		return RisorsaDTO.createRisorsaDTOListFromModelList(service.listAllElements());
	}
	
	
}

package it.prova.coge_be.service.azienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.coge_be.model.Azienda;
import it.prova.coge_be.repository.azienda.AziendaRepository;


@Service
public class AziendaServiceImpl implements AziendaService{
	
	@Autowired
	AziendaRepository repository;

	@Override
	public List<Azienda> listAll() {
		return (List<Azienda>) repository.findAll();

	}

	@Override
	public Azienda caricaSingolo(Long id) {
		return repository.findById(id).orElse(null);

	}

	@Override
	public Azienda aggiorna(Azienda aziendaInstance) {
	
		
		return repository.save(aziendaInstance);
	}

	@Override
	public Azienda inserisciNuovo(Azienda aziendaInstance) {
		return repository.save(aziendaInstance);

	}

	@Override
	public void rimuovi(Azienda aziendaInstance) {
		repository.delete(aziendaInstance);

		
	}
	
	

}

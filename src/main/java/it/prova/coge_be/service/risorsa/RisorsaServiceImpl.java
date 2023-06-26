package it.prova.coge_be.service.risorsa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< Updated upstream
import it.prova.coge_be.model.Attachment;
=======
import it.prova.coge_be.dto.risorsaNumeroCommesse.IRisorsaNumeroCommesseDTO;
>>>>>>> Stashed changes
import it.prova.coge_be.model.Risorsa;
import it.prova.coge_be.repository.attachment.AttachmentRepository;
import it.prova.coge_be.repository.risorsa.RisorsaRepository;

@Service
@Transactional(readOnly = true)
public class RisorsaServiceImpl implements RisorsaService {

	@Autowired
	private RisorsaRepository repository;

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public List<Risorsa> listAllElements() {
		return (List<Risorsa>) repository.findAll();
	}

	@Override
	public Risorsa caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Risorsa aggiorna(Risorsa entityInstance) {

		Attachment attachmentNuovo = entityInstance.getCv();
		Risorsa risorsaCaricata = repository.getSingleEagerWithAttachment(entityInstance.getId());
		if (risorsaCaricata.getCv().getId() != null) {
			attachmentNuovo.setId(risorsaCaricata.getCv().getId());
		}
		attachmentNuovo.setRisorsa(risorsaCaricata);
		attachmentRepository.save(attachmentNuovo);
		risorsaCaricata.setCv(attachmentNuovo);
		return repository.save(risorsaCaricata);

	}

	@Override
	@Transactional
	public Risorsa inserisciNuovo(Risorsa entityInstance) {
		if (entityInstance.getCv() != null) {
			entityInstance.getCv().setRisorsa(entityInstance);
			attachmentRepository.save(entityInstance.getCv());
		}
		return repository.save(entityInstance);
	}

	@Override
	@Transactional
	public void rimuoviById(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	@Override
	public List<Risorsa> listAllElementsEagerWithCommesse() {
		return repository.getAllEagerWithCommesse();
	}

	@Override
	public List<Risorsa> listAllElementsEagerWithAttachment() {
		return repository.getAllEagerWithAttachment();
	}

	@Override
	public List<Risorsa> listAllElementsEager() {
		return repository.getAllEager();
	}

	@Override
	public Risorsa caricaSingoloElementoEager(Long id) {
		return repository.getSingleEager(id);
	}

	@Override
	public Risorsa caricaSingoloElementoEagerWithCommesse(Long id) {
		return repository.getSingleEagerWithCommesse(id);
	}

	@Override
	public Risorsa caricaSingoloElementoEagerWithAttachment(Long id) {
		return repository.getSingleEagerWithAttachment(id);
	}

	@Override
	public List<IRisorsaNumeroCommesseDTO> riepilogoRisorseConCommesse() {
		return repository.riepilogoRisorseConCommesse();
	}
}

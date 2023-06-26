package it.prova.coge_be.dto.commessaMargine;

import java.time.LocalDate;

public interface ICommessaMargineDTO {
	String descrizione();
	String codice();
	LocalDate dataIn();
	LocalDate dataOut();
	Double importo();
	
	Integer margine();

}

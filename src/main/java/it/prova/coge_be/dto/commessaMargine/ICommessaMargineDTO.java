package it.prova.coge_be.dto.commessaMargine;

import java.time.LocalDate;

public interface ICommessaMargineDTO {
	String getDescrizione();
	String getCodice();
	LocalDate getDataIn();
	LocalDate getDataOut();
	Double getImporto();
	
	Integer getMargine();

}

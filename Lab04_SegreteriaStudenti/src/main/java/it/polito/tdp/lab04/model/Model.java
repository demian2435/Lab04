package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO = new CorsoDAO();
	private StudenteDAO studenteDAO = new StudenteDAO();
	
	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
	}

	public Studente trovaMatricola(int matricola) {
		return studenteDAO.trovaMatricola(matricola);
	}

	public Corso getCorsoNome(String nomeCorso) {
		Corso corso = new Corso("", 0, nomeCorso, 0);
		return corsoDAO.getCorsoNome(corso);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> trovaCorsiStudente(Studente studente){
		return studenteDAO.trovaCorsiStudente(studente);
	}

	public boolean findStudenteInCorso(Studente studente, Corso corso) {
		return corsoDAO.findStudenteInCorso(studente, corso);	
	}

}

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Corso> comboBox;

	@FXML
	private Button btnCercaIscritti;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnTrovaMatricola;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextArea txtArea;

	@FXML
	private Button btnReset;

	@FXML
	void doCercaCorsi(ActionEvent event) {
		txtArea.clear();
		int matricola = Integer.parseInt(txtMatricola.getText());
		Studente studente = model.trovaMatricola(matricola);
		if (studente == null) {
			txtArea.setText("MATRICOLA NON TROVATA");
			return;
		}

		List<Corso> corsi = model.trovaCorsiStudente(studente);
		for (Corso corso : corsi) {
			txtArea.appendText(corso.toStringAll() + "\n");
		}
	}

	@FXML
	void doCercaIscritti(ActionEvent event) {
		txtArea.clear();
		Corso corso = comboBox.getValue();

		if (corso == null) {
			txtArea.setText("SELEZIONARE UN CORSO");
			return;
		}
		List<Studente> studenti = model.getStudentiIscrittiAlCorso(corso);
		for (Studente studente : studenti) {
			txtArea.appendText(studente.toString() + "\n");
		}
	}

	@FXML
	void doIscrivi(ActionEvent event) {

	}

	@FXML
	void doTrovaMatricola(ActionEvent event) {
		txtArea.clear();
		int matricola;
		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (Exception e) {
			txtArea.setText("INSERIRE SOLO NUMERI");
			return;
		}

		Studente studente = model.trovaMatricola(matricola);
		if (studente == null) {
			txtArea.setText("MATRICOLA NON TROVATA");
			return;
		}

		if (comboBox.getValue() == null) {
			txtCognome.setText(studente.getCognome());
			txtNome.setText(studente.getNome());
		} else {
			boolean risultato = model.findStudenteInCorso(studente, comboBox.getValue());
			if (risultato) {
				txtArea.setText("LO STUDENTE E' ISCRITTO AL CORSO");
			} else {
				txtArea.setText("LO STUDENTE NON E' ISCRITTO AL CORSO");
			}
		}
	}

	@FXML
	void doReset(ActionEvent event) {
		txtArea.clear();
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
	}

	@FXML
	void initialize() {
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnTrovaMatricola != null : "fx:id=\"btnTrovaMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	private void startComboCox() {
		List<Corso> lista = model.getTuttiICorsi();
		comboBox.getItems().setAll(lista);
	}

	public void setModel(Model model) {
		this.model = model;
		startComboCox();
	}

}

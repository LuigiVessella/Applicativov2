package project.model;

public class Studente extends Utente {

	private String matricolaStud;
	
public Studente(String Nome, String Cognome, String Login, String Password, String MatricolaStud) {
		this.matricolaStud=MatricolaStud;
		this.nome=Nome;
		this.cognome=Cognome;
		this.login=Login;
		this.password=Password;
}

public String getMatricola_Stud() {
	return matricolaStud;
}
	 
	
}

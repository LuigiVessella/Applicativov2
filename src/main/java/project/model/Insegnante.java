package project.model;

public class Insegnante extends Utente {

	private String matricolaIns;
	
 public Insegnante (String nome, String cognome, String login, String password, String matricolaIns) {
		this.nome=nome;
		this.cognome=cognome;
		this.login=login;
		this.password=password;
		this.matricolaIns=matricolaIns;
	}

 public String getMatricola_Ins() {
		return matricolaIns;
	}
		
	
}

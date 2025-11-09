package project.model;

public class Utente {

	protected String nome;
	protected String cognome;
	protected String login;
	protected String password;


public Utente(String Nome, String Cognome, String Login, String Password){

	this.nome=Nome;
	this.cognome=Cognome;
	this.login=Login;
	this.password=Password;

}

public Utente(){};

public String getLogin() {
	return login;
}


public void setLogin(String login) {
	this.login = login;
}


public String getCognome() {
	return cognome;
}


public void setCognome(String cognome) {
	this.cognome = cognome;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


}
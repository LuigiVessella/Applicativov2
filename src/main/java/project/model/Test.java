package project.model;

import java.sql.Date;
import java.sql.Time;

public class Test {
	private String nome_id;
	private int n_quiz;
	private Date data;
	private Time ora_inizio;
	private Time ora_fine;
	private String cod_corso;
	private String id_ins;
	

 public Test (String nome_id, int n_quiz, Date data, Time ora_inizio ,Time ora_fine, String cod_corso, String id_ins) {
		this.setNome_id(nome_id);
		this.setN_quiz(n_quiz);
		this.setData(data);
		this.setOra_inizio(ora_inizio);
		this.setOra_fine(ora_fine);
		this.setCod_corso(cod_corso);
		this.setId_ins(id_ins);
	}


public Time getOra_fine() {
	return ora_fine;
}


public void setOra_fine(Time ora_fine) {
	this.ora_fine = ora_fine;
}


public int getN_quiz() {
	return n_quiz;
}


public void setN_quiz(int n_quiz) {
	this.n_quiz = n_quiz;
}


public String getId_ins() {
	return id_ins;
}


public void setId_ins(String id_ins) {
	this.id_ins = id_ins;
}


public String getNome_id() {
	return nome_id;
}


public void setNome_id(String nome_id) {
	this.nome_id = nome_id;
}


public Date getData() {
	return data;
}


public void setData(Date data) {
	this.data = data;
}


public Time getOra_inizio() {
	return ora_inizio;
}


public void setOra_inizio(Time ora_inizio) {
	this.ora_inizio = ora_inizio;
}


public String getCod_corso() {
	return cod_corso;
}


public void setCod_corso(String cod_corso) {
	this.cod_corso = cod_corso;
}
		
	
}

package project.model;

import java.sql.Date;

public class Corso {
	private String cod_corso;
	private String nome;
	private Date data_inizio;
	private Date data_fine;
	private String id_ins;
	

 public Corso (String cod_corso, String nome, String id_ins, Date d_ini, Date d_fin) {
		this.cod_corso=cod_corso;;
		this.nome=nome;
		this.id_ins=id_ins;
		this.data_inizio=d_ini;
		this.data_fine=d_fin;
		
	}


public String getId_ins() {
	return id_ins;
}


public void setId_ins(String id_ins) {
	this.id_ins = id_ins;
}


public Date getD_ini() {
	return data_inizio;
}


public void setD_ini(Date d_ini ) {
	this.data_inizio = d_ini;
}


public Date getD_fin() {
	return data_fine;
}


public void setD_fin(Date d_fin ) {
	this.data_fine = d_fin;
}

public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getCod_corso() {
	return cod_corso;
}


public void setCod_corso(String cod_corso) {
	this.cod_corso = cod_corso;
}
		
	
	
}

package project.model;


public class Quiz_risp_mul extends Quiz{

	private String A;
	private String B;
	private String C;
	private String D;
	private String risposta_corretta;
	private int punt_corretto;
	private int punt_errato;
	private String id_test;

	

 public Quiz_risp_mul (String id_quiz, String domanda, String A, String B, String C, String D, String risp_corr, int punt_c, int punt_e, String id_test) {
		this.domanda = domanda;
		this.id_quiz=id_quiz;
		this.A=A;
		this.B=B;
		this.C=C;
		this.D=D;
		this.risposta_corretta=risp_corr;
		this.punt_corretto=punt_c;
		this.punt_errato=punt_e;
		this.id_test=id_test;
		
	}
 
 public String getDomanda() {
		return domanda;
	}
 
 public String getId_quiz() {
		return id_quiz;
	}
 
 public String getA() {
		return A;
	}
 
 public String getB() {
		return B;
	}
 
 public String getC() {
		return C;
	}
 
 public String getD() {
		return D;
	}
 
 public String getRispo_corr() {
		return risposta_corretta;
	}
 
 public int getPunt_c() {
		return punt_corretto;
	}
 
 public int getPunt_e() {
		return punt_errato;
	}
 
 public String getId_test() {
		return id_test;
	}
}
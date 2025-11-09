package project.model;


public class Quiz_svolti{

	private String risposta_data;
	private String nome_id;
	private String id_stud;
	private float punteggio_quiz;
	private String id_quiz;
	

 public Quiz_svolti (String nome_id, String id_stud, String id_quiz, String risposta_data, float punteggio_quiz) {
	 this.nome_id=nome_id;
	 this.id_stud=id_stud;
	 this.id_quiz=id_quiz;
	 this.risposta_data = risposta_data;
	 this.punteggio_quiz=punteggio_quiz;
	}
 
 
 public String getNome_id() {
		return nome_id;
	}
 
 public String getId_stud() {
		return id_stud;
	}
		
 public String getId_quiz() {
		return id_quiz;
	}
 
 public String getRispostaData() {
		return risposta_data;
	}
 
 public float getPunteggio_quiz() {
		return punteggio_quiz;
	}
}


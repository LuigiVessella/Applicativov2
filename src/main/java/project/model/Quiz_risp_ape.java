package project.model;


public class Quiz_risp_ape extends Quiz{

	private int max_lenght;
	private float punt_max;
	private float punt_min;
	private String id_test;

	

 public Quiz_risp_ape (String id_quiz,String domanda, int max_lenght, float punt_max, float punt_min, String id_test) {
		this.id_quiz=id_quiz;
	 	this.domanda = domanda;
		this.max_lenght=max_lenght;
		this.punt_max=punt_max;
		this.punt_min=punt_min;
		this.id_test=id_test;
		
	}


 public String getId_quiz() {
		return id_quiz;
	}


 public String getDomanda() {
		return domanda;
	}

 public int getMax_lenght() {
		return max_lenght;
	}
 
 public float getPunt_max() {
		return punt_max;
	}
	
 public float getPunt_min() {
		return punt_min;
	}
 public String getId_test() {
		return id_test;
	}
}

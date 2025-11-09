package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Quiz_svolti;

public class ImpQuiz_svoltiDao implements project.dao.Quiz_svoltiDAO{
	public Connection connessione;

	public void salvaRispMulti(Quiz_svolti qs) {
		
		 try {
		        connessione = Database_connection.getInstance().getConnection();
		    } catch (SQLException e) {
		        System.out.println("Connessione fallita.");
		        e.printStackTrace();
		    }
			
		    try {
		    	PreparedStatement MyStmt = connessione.prepareStatement("INSERT INTO QUIZ_SVOLTI (NOME_ID, ID_STUD, ID_QUIZM, RISPOSTA_DATA) VALUES (?,?,?,?)");
		        MyStmt.setObject(1, qs.getNome_id());
		        MyStmt.setObject(2, qs.getId_stud());
		        MyStmt.setObject(3, qs.getId_quiz());
		        MyStmt.setObject(4, qs.getRispostaData());
		        MyStmt.execute();
		        
		   
		    	
		    } 
		    catch (SQLException e) {

		        e.printStackTrace();

		    }
		
	}

	public void salvaRispostaApe(Quiz_svolti qs) {
		 try {
		        connessione = Database_connection.getInstance().getConnection();
		    } catch (SQLException e) {
		        System.out.println("Connessione fallita.");
		        e.printStackTrace();
		    }
			
		    try {
		    	PreparedStatement MyStmt = connessione.prepareStatement("INSERT INTO QUIZ_SVOLTI (NOME_ID, ID_STUD, ID_QUIZA, RISPOSTA_DATA) VALUES (?,?,?,?)");
		        MyStmt.setObject(1, qs.getNome_id());
		        MyStmt.setObject(2, qs.getId_stud());
		        MyStmt.setObject(3, qs.getId_quiz());
		        MyStmt.setObject(4, qs.getRispostaData());
		        MyStmt.execute();
		        
		    	
		    } 
		    catch (SQLException e) {

		        e.printStackTrace();

		    }
	}


}

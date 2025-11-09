package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Insegnante;
import project.model.Quiz_risp_ape;

public class ImpQuiz_Risp_ApeDao implements project.dao.Quiz_Risp_ApeDAO{

	public Connection connessione;
	
	public void ImpQuiz_Risp_ApeDao (){
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
        
        
        
        
    }

	public void Quiz_Risp_Ape(Quiz_risp_ape qra) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		

	    try {
	    	PreparedStatement MyStmt = connessione.prepareStatement("INSERT INTO QUIZ_RISP_APE VALUES (?,?,?,?,?,?)");
	        MyStmt.setObject(1, qra.getId_quiz());
	        MyStmt.setObject(2, qra.getDomanda());
	        MyStmt.setInt(3, qra.getMax_lenght());
	        MyStmt.setFloat(4, qra.getPunt_max());
	        MyStmt.setFloat(5, qra.getPunt_min());
	        MyStmt.setObject(6, qra.getId_test());
	        MyStmt.executeUpdate();

	    } 
	    catch (SQLException e) {

	        e.printStackTrace();

	    }
		
	}
}

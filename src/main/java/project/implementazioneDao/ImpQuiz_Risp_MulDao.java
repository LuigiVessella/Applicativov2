package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Insegnante;
import project.model.Quiz_risp_mul;

public class ImpQuiz_Risp_MulDao implements project.dao.Quiz_risp_MulDAO{

	public Connection connessione;
	
	public void ImpQuiz_Risp_MulDao (){
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
        
        
        
        
    }

	public void Quiz_Risp_Multi(Quiz_risp_mul qrm) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		

	    try {
	    	PreparedStatement MyStmt = connessione.prepareStatement("INSERT INTO QUIZ_RISP_MUL VALUES (?,?,?,?,?,?,?,?,?,?)");
	        MyStmt.setObject(1, qrm.getId_quiz());
	        MyStmt.setObject(2, qrm.getDomanda());
	        MyStmt.setObject(3, qrm.getA());
	        MyStmt.setObject(4, qrm.getB());
	        MyStmt.setObject(5, qrm.getC());
	        MyStmt.setObject(6, qrm.getD());
	        MyStmt.setObject(7, qrm.getRispo_corr());
	        MyStmt.setInt(8, qrm.getPunt_c());
	        MyStmt.setInt(9, qrm.getPunt_e());
	        MyStmt.setObject(10, qrm.getId_test());

	        MyStmt.executeUpdate();

	    } 
	    catch (SQLException e) {

	        e.printStackTrace();

	    }
		
	}
}
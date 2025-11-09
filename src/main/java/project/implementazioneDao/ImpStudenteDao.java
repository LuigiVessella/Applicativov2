package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Studente;


public class ImpStudenteDao implements project.dao.StudenteDAO{
	public Connection connessione;
	
	public ImpStudenteDao() {
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
    }

	public void InserisciStud(Studente s) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }

			PreparedStatement insert;
	        try {
	            insert = connessione.prepareStatement(
	                    "INSERT INTO STUDENTE VALUES ('"+s.getMatricola_Stud()+"','"+s.getNome()+"','"+s.getCognome()+"','"+s.getLogin()+"','"+s.getPassword()+"');");

	        insert.execute();

	        } 
	        catch (SQLException e) {

	            e.printStackTrace();

	        }
		}
	
	
}

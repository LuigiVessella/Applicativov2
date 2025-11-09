package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Test;

public class ImpTestDao implements project.dao.TestDAO{
	public Connection connessione;
	
	public void impTestDao() {
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
    }

	public void InserisciTest(Test t) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		
		PreparedStatement insert;
	    try {
	        insert = connessione.prepareStatement(
	                "INSERT INTO test VALUES ('"+t.getNome_id()+"','"+t.getN_quiz()+"','"+t.getData()+"','"+t.getOra_inizio()+"','"+t.getOra_fine()+"','"+t.getCod_corso()+"','"+t.getId_ins()+"');");

	    insert.executeUpdate();

	    } 
	    catch (SQLException e) {

	        e.printStackTrace();

	    }
		
	}
	
	
}

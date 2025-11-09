package project.implementazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.database_connection.Database_connection;
import project.model.Insegnante;

public class ImpInsegnanteDao implements project.dao.InsegnanteDAO{

	public Connection connessione;
	
	public void InsegnanteDAO() {
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
    }

public void InserisciIns(Insegnante i) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		
		PreparedStatement insert;
     
        try {
            insert = connessione.prepareStatement(
                    "INSERT INTO INSEGNANTE VALUES ('"+i.getMatricola_Ins()+"','"+i.getNome()+"','"+i.getCognome()+"','"+i.getLogin()+"','"+i.getPassword()+"');");

        insert.executeUpdate();

        } 
        catch (SQLException e) {

            e.printStackTrace();

        }
		
	}
	
}

package project.implementazioneDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import project.database_connection.Database_connection;
import project.model.Corso;

public class ImpCorsoDao implements project.dao.CorsoDAO{

	public Connection connessione;
	
	public void CorsoDAO() {
        try {
            connessione = Database_connection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Connessione fallita.");
            e.printStackTrace();
        }
    }

	public void InserisciCorso(Corso c) {
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
			
	        try {

	        	PreparedStatement MyStmt = connessione.prepareStatement("INSERT INTO CORSO VALUES (?,?,?,?,?)");
	            MyStmt.setObject(1, c.getCod_corso());
	            MyStmt.setObject(2, c.getNome());
	            MyStmt.setObject(3, c.getId_ins());
	            
	            String pattern = "yyyy-MM-dd";
	    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    		
	    		String date1 = simpleDateFormat.format(c.getD_ini());
	    		Date d_ini1=Date.valueOf(date1);
	    		
	    		String date2 = simpleDateFormat.format(c.getD_fin());
	    		Date d_fin1=Date.valueOf(date2);
	    		
	            MyStmt.setObject(4, d_ini1);
	            MyStmt.setObject(5, d_fin1);
	            MyStmt.executeUpdate();

	        } 
	        catch (SQLException e) {

	            e.printStackTrace();

	        }
			
		}
	
}

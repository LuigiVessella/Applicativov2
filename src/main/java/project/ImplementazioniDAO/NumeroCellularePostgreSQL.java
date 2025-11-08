package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.NumeroCellulareDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NumeroCellularePostgreSQL implements NumeroCellulareDAO {

    private Connection conn;

    public NumeroCellularePostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe NumeroCellularePostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciCellulare(int id, String cellulare) throws SQLException {
        String inserisciCellulare = "INSERT INTO NumeroCellulare(cellulare, idcontatto) VALUES ('"+cellulare+"' , '"+id+"')";

        PreparedStatement st = conn.prepareStatement(inserisciCellulare);
        st.executeUpdate();
    }

    @Override
    public void modificaCellulare(String id, String cellulare) throws SQLException {

        String inserisciCellulare = "INSERT INTO NumeroCellulare(cellulare, idcontatto) VALUES ('"+cellulare+"' , '"+id+"')";

        PreparedStatement st = conn.prepareStatement(inserisciCellulare);
        st.executeUpdate();
    }
}

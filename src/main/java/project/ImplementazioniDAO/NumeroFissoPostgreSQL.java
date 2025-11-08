package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.NumeroFissoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NumeroFissoPostgreSQL implements NumeroFissoDAO {

    private Connection conn;

    public NumeroFissoPostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe NumeroFissoPostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciFisso(int id, String fisso) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserisciFisso = "INSERT INTO NumeroFisso(fisso, idcontatto) VALUES ('"+fisso+"' , '"+id+"')";

        PreparedStatement st = conn.prepareStatement(inserisciFisso);
        st.executeUpdate();
    }

    @Override
    public void modificaFisso(String id, String fisso) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        //String inserisciContatto = "INSERT INTO Contatto(nome, cognome, foto) VALUES ('"+nome+"', '"+cognome+"', '"+foto+"')";

        //PreparedStatement st = conn.prepareStatement(inserisciContatto);
        //st.executeUpdate();

    }
}

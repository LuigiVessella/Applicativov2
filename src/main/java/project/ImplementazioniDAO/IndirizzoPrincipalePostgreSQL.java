package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.IndirizzoPrincipaleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IndirizzoPrincipalePostgreSQL implements IndirizzoPrincipaleDAO {

    private Connection conn;

    public IndirizzoPrincipalePostgreSQL() throws SQLException {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe IndirizzoPrincipale PostgreSQL");
            e.printStackTrace();
        }
    }


    @Override
    public void inserisciIndirizzoPrincipale(int id, String indirizzoPrincipale) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        try {
            String via, civico, cap, citta, nazione;
            String[] divisione = indirizzoPrincipale.split("\\s*,\\s*");
            via = divisione[0];
            civico = divisione[1];
            cap = divisione[2];
            citta = divisione[3];
            nazione = divisione[4];
            String inserisciIndirizzo = "INSERT INTO IndirizzoPrincipale(via, civico, citta, cap, nazione, idcontatto) " +
                    "VALUES ('" + via + "', '" + civico + "', '" + citta + "', '" + cap + "', '" + nazione + "', '" + id + "')";

            PreparedStatement st = conn.prepareStatement(inserisciIndirizzo);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 42 Classe IndirizzoPrincipalePostgreSQL");
            e.printStackTrace();
        }

    }
}

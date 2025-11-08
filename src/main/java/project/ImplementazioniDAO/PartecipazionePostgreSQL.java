package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.PartecipazioneDAO;

import java.sql.*;
import java.util.ArrayList;

public class PartecipazionePostgreSQL implements PartecipazioneDAO {

    private Connection conn;

    public PartecipazionePostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe EmailPostgreSQL");
            e.printStackTrace();
        }
    }
    @Override
    public void entraInGruppo(int id, String nomeGruppo) {

        if (!nomeGruppo.isEmpty()) {
            try {
                conn = Connessione.getInstance().getConnection();
                PreparedStatement inserisciPersonaInGruppo = conn.prepareStatement("INSERT INTO Partecipazione (idcontatto, nomegruppo) VALUES ('" + id + "','" + nomeGruppo + "');");
                inserisciPersonaInGruppo.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                System.out.println("ECCEZIONE::Riga 105 Classe CreaContattoPostgreSQL");
                e.printStackTrace();

            }
        }

    }

    @Override
    public void esciDalGruppo(int id, String nomeGruppo) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        PreparedStatement rimuoviPartecipazione = conn.prepareStatement("DELETE FROM Partecipazione WHERE idcontatto = '"+id+"' AND nomegruppo = '"+nomeGruppo+"'");
        rimuoviPartecipazione.executeUpdate();

    }

    @Override
    public ArrayList<String> estraiGruppi(int id, ArrayList<String> gruppi) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String cercaGruppi = "SELECT nomegruppo FROM Partecipazione WHERE idcontatto = '"+id+"'";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(cercaGruppi);
        while(rs.next())
            gruppi.add(rs.getString("nomegruppo"));

        return gruppi;
    }

    @Override
    public ArrayList<String> gruppiPartecipabili(int id, ArrayList<String> gruppi) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String cercaGruppi = "select nome from gruppo  " +
                             "EXCEPT Select nomegruppo " +
                             "from partecipazione " +
                             "WHERE idcontatto =  '"+id+"'";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(cercaGruppi);
        while(rs.next())
            gruppi.add(rs.getString("nome"));

        return gruppi;
    }


}

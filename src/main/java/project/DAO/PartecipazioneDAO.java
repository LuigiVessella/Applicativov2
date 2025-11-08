package project.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartecipazioneDAO {
    void entraInGruppo(int id, String nomeGruppo) throws SQLException;
    void esciDalGruppo(int id, String nomeGruppo) throws SQLException;
    ArrayList<String> estraiGruppi(int id, ArrayList<String> gruppi) throws SQLException;
    ArrayList<String> gruppiPartecipabili(int id, ArrayList<String> gruppi) throws SQLException;
}

package project.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import project.Classi.Contatti;

public interface ContattoDAO {
    int inserimentoContatto(String nome, String cognome, String foto) throws SQLException;
    ArrayList<Contatti> stampaContatti() throws SQLException;
    Contatti cercaInfoContatti(int id, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException;
    void eliminaContatto(int id) throws SQLException;
    public Contatti estraiCellulareFisso(int id);
}

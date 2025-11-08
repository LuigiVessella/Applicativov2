package project.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import project.Classi.Contatti;

public interface RicercaContattoDAO {
    public ArrayList<Contatti> ricercaNome(String nome) throws SQLException;
    public ArrayList<Contatti> ricercaCellulare(String cellulare) throws SQLException;
    public ArrayList<Contatti> ricercaNickname(String Nickname);
    public ArrayList<Contatti> ricercaEmail(String email) throws SQLException;

}

package project.DAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IndirizzoSecondarioDAO {
    void inserisciIndirizzoSecondario(int id, ArrayList<JTextField> listaIndirizzi) throws SQLException;
}

package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.EmailSecondarioDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmailSecondarioPostgreSQL implements EmailSecondarioDAO {

    private Connection conn;

    public EmailSecondarioPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciEmailSecondarie(int id, ArrayList<JTextField> listaEmail) {
        String email;
        try {
            if (listaEmail != null) {
                for (int i = 0; i < listaEmail.size(); i++) {
                    email = listaEmail.get(i).getText();
                    if (!email.isEmpty()) {
                        PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into Email (email, idcontatto) VALUES ('" + email + "','" + id + "');");
                        inserisciContattoEmail.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 117 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }
    }
}

package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.GruppoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GruppoPostgreSQL implements GruppoDAO {
    ArrayList<String> infoGruppi = new ArrayList<>();
    private Connection conn;
    public GruppoPostgreSQL() {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> cercaGruppi() throws SQLException {
        conn = Connessione.getInstance().getConnection();

        String queryCercaGruppi = ("SELECT * FROM Gruppo");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaGruppi);
        String nome;
        infoGruppi.clear();
        while (rs.next()){
            nome = rs.getString("nome");
            infoGruppi.add(nome);
        }

        return infoGruppi;
    }
}

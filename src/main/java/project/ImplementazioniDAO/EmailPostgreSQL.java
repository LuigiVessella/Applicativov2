package project.ImplementazioniDAO;

import project.ConnessioneDB.Connessione;
import project.DAO.EmailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailPostgreSQL implements EmailDAO {

    private Connection conn;

    public EmailPostgreSQL() throws SQLException{
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe EmailPostgreSQL");
            e.printStackTrace();
        }
    }
    @Override
    public int inserimentoEmail(int id, String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserimentoEmail = "INSERT INTO Email(idcontatto, email) VALUES ('"+id+"', '"+email+"') RETURNING IDEMAIL;";

        PreparedStatement st = conn.prepareStatement(inserimentoEmail);
        st.execute();
        ResultSet rs = st.getResultSet();
        rs.next();

        return rs.getInt("idEmail");
    }

    @Override
    public boolean controlloDuplicatoEmail(String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String rimozioneEmail = "SELECT EXISTS( SELECT email FROM EMAIL WHERE email = '" + email + "');";
        PreparedStatement st = conn.prepareStatement(rimozioneEmail);
        ResultSet rs = st.executeQuery();
            rs.next();

            return rs.getBoolean(1);

    }
}

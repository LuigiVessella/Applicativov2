package project.ImplementazioniDAO;

import project.Classi.Contatti;
import project.ConnessioneDB.Connessione;
import project.DAO.RicercaContattoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RicercaContattoPostgreSQL implements RicercaContattoDAO {

    ArrayList<Contatti> contattiInfo = new ArrayList<>();

    private Connection conn;


    public RicercaContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Contatti> ricercaNome(String nome) throws SQLException {
        contattiInfo.clear();
        conn = Connessione.getInstance().getConnection();

        String queryRicercaNome = ("SELECT * FROM Contatto as c  " +
                "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                " ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto WHERE c.nome LIKE '%"+nome+"%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaNome);

        while(rs.next()){
            Contatti contatto = new Contatti();
            contatto.setNome(rs.getString("nome"));
            contatto.setCognome(rs.getString("cognome"));
            contatto.setCellulare(rs.getString("cellulare"));
            contatto.setFisso(rs.getString("fisso"));
            contatto.setId(rs.getInt("id"));
            String indirizzo = rs.getString("via" );
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            contatto.setIndirizzo(indirizzo+", "+civico+", "+cap+", "+citta+", "+nazione);
            contattiInfo.add(contatto);
        }
        return contattiInfo;
    }

    @Override
    public ArrayList<Contatti> ricercaCellulare(String cellulare) throws SQLException {
        contattiInfo.clear();
        conn = Connessione.getInstance().getConnection();

        String queryRicercaNome = ("SELECT * FROM Contatto as c  " +
                "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                " ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto WHERE n.cellulare LIKE '%"+cellulare+"%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaNome);

        while(rs.next()){
            Contatti contatto = new Contatti();
            contatto.setNome(rs.getString("nome"));
            contatto.setCognome(rs.getString("cognome"));
            contatto.setCellulare(rs.getString("cellulare"));
            contatto.setFisso(rs.getString("fisso"));
            contatto.setId(rs.getInt("id"));
            String indirizzo = rs.getString("via" );
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            contatto.setIndirizzo(indirizzo+", "+civico+", "+cap+", "+citta+", "+nazione);
            contattiInfo.add(contatto);
        }
        return contattiInfo;
    }

    @Override
    public ArrayList<Contatti> ricercaNickname(String nickname) {
        contattiInfo.clear();
        try{
            conn = Connessione.getInstance().getConnection();
            String queryNickname = ("SELECT nome,cognome FROM Messaging as m " +
                                    "JOIN Contatto as c ON c.id = m.idcontatto " +
                                    "WHERE m.nickname LIKE '%"+nickname+"%'");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(queryNickname);

            while(rs.next()){
                Contatti contatto  = new Contatti();
                contatto.setNome(rs.getString("nome"));
                contatto.setCognome(rs.getString("cognome"));
                contattiInfo.add(contatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contattiInfo;
    }

    @Override
    public ArrayList<Contatti> ricercaEmail(String email) throws SQLException {

        contattiInfo.clear();
        conn = Connessione.getInstance().getConnection();

        String queryRicercaNome = ("SELECT nome,cognome,id FROM Contatto as c JOIN Email as e ON c.id = e.idcontatto " +
                                   "WHERE e.email LIKE '%"+email+"%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaNome);

        while(rs.next()){
            Contatti contatto = new Contatti();
            contatto.setNome(rs.getString("nome"));
            contatto.setCognome(rs.getString("cognome"));
            contatto.setId(rs.getInt("id"));
            contattiInfo.add(contatto);
        }
        return contattiInfo;
    }
}

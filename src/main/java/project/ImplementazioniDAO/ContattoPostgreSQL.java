package project.ImplementazioniDAO;

import project.Classi.Contatti;
import project.ConnessioneDB.Connessione;
import project.DAO.ContattoDAO;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class ContattoPostgreSQL implements ContattoDAO {

    private Connection conn;
    ArrayList<Contatti> contattiDB = new ArrayList<>();

    public ContattoPostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 19 Classe ContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public int inserimentoContatto(String nome, String cognome, String foto) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserisciContatto = "INSERT INTO Contatto(nome, cognome, foto) VALUES ('"+nome+"', '"+cognome+"', '"+foto+"') RETURNING ID;";

        PreparedStatement st = conn.prepareStatement(inserisciContatto);
        st.execute();
        ResultSet rs = st.getResultSet();
        rs.next();
        return rs.getInt("id");
    }

    @Override
    public ArrayList<Contatti> stampaContatti() throws SQLException {

        String queryStampaContatti = "SELECT * FROM Contatto as c JOIN NumeroCellulare as n ON c.id = n.idcontatto ORDER BY nome";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryStampaContatti);

        while (rs.next()){
            Contatti contatti =  new Contatti();

            String nome = rs.getString("nome");
            contatti.setNome(nome);
            String cognome = rs.getString("cognome");
            contatti.setCognome(cognome);
            int id = rs.getInt("id");
            contatti.setId(id);

            contattiDB.add(contatti);
        }
        st.close();
        conn.close();
        return contattiDB;
    }

    @Override
    public Contatti cercaInfoContatti(int id, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        Contatti contatto = new Contatti();

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN Email as e ON c.id = e.idcontatto " +
                                         "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                                         "ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto WHERE c.id = '"+id+"'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaInfoContatto);

        while (rs.next()) {
            contatto.setId(rs.getInt("id"));
            contatto.setNome(rs.getString("nome"));
            contatto.setCognome(rs.getString("cognome"));
            contatto.setCellulare(rs.getString("cellulare"));
            contatto.setFisso(rs.getString("fisso"));
            contatto.setFoto(rs.getString("foto"));
            String indirizzo = rs.getString("via");
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            contatto.setIndirizzo(indirizzo+", "+civico+", "+cap+", "+citta+", "+nazione);

        }

        estraiInformazioniSecondarie(id, indirizzoSecondario, emailSecondario);
        contatto.setEmail(emailSecondario.get(0));
        conn.close();
        return contatto;
    }

    @Override
    public void eliminaContatto(int id) throws SQLException {
            conn = Connessione.getInstance().getConnection();
            PreparedStatement eliminaContatto = conn.prepareStatement("DELETE FROM CONTATTO WHERE id = '"+id+"'");
            eliminaContatto.executeUpdate();

            conn.close();
    }

    public void estraiInformazioniSecondarie(int id, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN IndirizzoSecondario as i ON i.idcontatto = c.id " +
                                         "WHERE c.id = '"+id+"'");
        String queryCercaEmailSecondarie = "SELECT * FROM Contatto as c JOIN email as es ON es.idcontatto = c.id WHERE c.id = '"+id+"'";

        Statement stIndirizziSecondari = conn.createStatement();
        ResultSet rsIndirizziSecondari = stIndirizziSecondari.executeQuery(queryCercaInfoContatto);
        while(rsIndirizziSecondari.next()) {
            String indirizzo = rsIndirizziSecondari.getString("via");
            String civico = rsIndirizziSecondari.getString("civico");
            String cap = rsIndirizziSecondari.getString("cap");
            String citta = rsIndirizziSecondari.getString("citta");
            String nazione = rsIndirizziSecondari.getString("nazione");
            indirizzoSecondario.add(indirizzo + ", " + civico + ", " + cap + ", " + citta + ", " + nazione);
        }



        Statement stEmailSecondari = conn.createStatement();
        ResultSet rsEmailSecondari = stEmailSecondari.executeQuery(queryCercaEmailSecondarie);
        while(rsEmailSecondari.next()) {
            emailSecondario.add(rsEmailSecondari.getString("email"));
        }
    }

    @Override
    public Contatti estraiCellulareFisso(int id){
        Contatti contatto = new Contatti();
        try{
            conn = Connessione.getInstance().getConnection();
            String query = "SELECT * FROM NumeroCellulare as c JOIN NumeroFisso as f ON c.idcontatto = f.idcontatto WHERE c.idcontatto = '"+id+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                contatto.setCellulare(rs.getString("cellulare"));
                contatto.setFisso(rs.getString("fisso"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return contatto;
    }
}

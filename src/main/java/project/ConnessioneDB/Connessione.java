package project.ConnessioneDB;


import java.sql.*;


public class Connessione {

    // ATTRIBUTI
    private static Connessione instance;
    private Connection connection = null;
    private String nome = "postgres";
    private String password = "rubrica";
    private String url = "jdbc:postgresql://localhost:5432/Rubrica";
    private String driver = "org.postgresql.Driver";


    public Connessione()throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Connessione al database FALLITA : " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    //METODI
    public static Connessione getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connessione();
        } else if (instance.getConnection().isClosed()) {
            instance = new Connessione();
        }
        return instance;
    }

    //GETTER SETTER
    public static void setInstance(Connessione instance) {
        Connessione.instance = instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
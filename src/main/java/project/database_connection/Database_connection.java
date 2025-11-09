package project.database_connection;

    	import java.sql.*;
    	import java.sql.Connection;
    	import java.sql.DriverManager;
    	import java.sql.SQLException;
    	 
    public class Database_connection {

    		// ATTRIBUTI
    		private static Database_connection instance;
    		private Connection connection = null;
    		private String nome = "master";
    		private String password = "Qaz1234!";
    		private String url = "jdbc:postgresql://ls-13c6161d9317ff2d5e81cbee204d4014abe34ab6.c5uty6bg7pgc.eu-west-2.rds.amazonaws.com:5432/dbapp_elearning";
    		private String driver = "org.postgresql.Driver";
    		String failed = "Connessione al Database fallita.";
    		String success = "Connessione al Database effettuata con successo.";

    		// COSTRUTTORE
    		public Database_connection() throws SQLException {
    			try {
    				Class.forName(getDriver());
    				setConnection(DriverManager.getConnection(getUrl(), getNome(), password));

    			} catch (ClassNotFoundException ex) {
    				System.out.println("Connessione al Database fallita: " + ex.getMessage());
    				ex.printStackTrace();
    			}

    		}

    		public Connection getConnection() {
    				return connection;
    		}
    		
    		public static Database_connection getInstance() throws SQLException {
    			if (instance == null) {
    				instance = new Database_connection();
    			} else if (instance.getConnection().isClosed()) {
    				instance = new Database_connection();
    			}
    			return instance;
    		}

    		public String getNome() {
    			return nome;
    		}

    		public void setNome(String nome) {
    			this.nome = nome;
    		}

    		public String getDriver() {
    			return driver;
    		}

    		public void setDriver(String driver) {
    			this.driver = driver;
    		}

    		public String getUrl() {
    			return url;
    		}

    		public void setUrl(String url) {
    			this.url = url;
    		}

    		public void setConnection(Connection connection) {
    			this.connection = connection;
    		}


    	}

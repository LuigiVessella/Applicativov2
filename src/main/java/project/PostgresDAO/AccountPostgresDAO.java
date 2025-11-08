package project.PostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.DAO.AccountDAO;
import project.Database.DatabaseConnection;
import project.Model.Account;
import project.Model.Contact;

public class AccountPostgresDAO implements AccountDAO {

	private Connection connection;

	public AccountPostgresDAO() {
		try {
			connection = DatabaseConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addAccountDB(Account account, Contact contact) {
		try {
			PreparedStatement nuovoAccount = connection.prepareStatement(
					"INSERT INTO \"UltraPhoneBookDB\".\"Account\" " + 
					"(\"app_name\", \"nickname\", \"email\", \"welcome_sentence\")" +
					"VALUES ('"+account.getAppName()+"','"+account.getNickname()+"', "+account.getEmail()+", "+account.getWelcomeSentence()+");");
			nuovoAccount.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

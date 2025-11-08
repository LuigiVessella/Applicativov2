package project.DAO;

import project.Model.Account;
import project.Model.Contact;

public interface AccountDAO {
	void addAccountDB(Account account, Contact contact);
}

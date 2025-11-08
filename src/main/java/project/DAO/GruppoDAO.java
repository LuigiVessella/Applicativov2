package project.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GruppoDAO {
    public ArrayList<String> cercaGruppi() throws SQLException;
}

package project.DAO;

import java.sql.SQLException;

public interface NumeroCellulareDAO {
    void inserisciCellulare(int id, String cellulare) throws SQLException;
    void modificaCellulare(String id, String cellulare) throws SQLException;
}

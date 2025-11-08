package project.DAO;

import java.sql.SQLException;

public interface EmailDAO {
    int inserimentoEmail(int id, String email) throws SQLException;
    boolean controlloDuplicatoEmail(String email) throws SQLException;
}

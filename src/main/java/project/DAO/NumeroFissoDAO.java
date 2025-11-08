package project.DAO;

import java.sql.SQLException;

public interface NumeroFissoDAO {
    void inserisciFisso(int id, String fisso) throws SQLException;
    void modificaFisso(String id, String fisso) throws SQLException;
}

package project.DAO;

import java.sql.SQLException;

import project.Classi.Messaging;

public interface MessagingDAO {
    Messaging ricercaProvider(int id, String provider) throws SQLException;
    void inserimentoAccountMessaging(int id, int email, Messaging messaging) throws SQLException;
}

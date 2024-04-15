package ejercicio1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private final String url;
    private final String userName;
    private final String password;
    public Conn(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    Connection connection() throws SQLException {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

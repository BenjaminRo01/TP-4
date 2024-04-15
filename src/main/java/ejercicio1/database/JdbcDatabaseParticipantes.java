package ejercicio1.database;

import ejercicio1.model.ParticipantesDatabaseService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDatabaseParticipantes implements ParticipantesDatabaseService {
    public static final String SQL_INSERT_PARTICIPANTES = "INSERT INTO participantes(nombre, telefono, region) VALUES(?,?,?)";
    private final Conn conn;

    public JdbcDatabaseParticipantes(String url, String userName, String password){
        this.conn = new Conn(url, userName, password);
    }
    @Override
    public void insert(String nombre, String telefono, String region) {
        try(Connection connection = this.conn.connection();
            PreparedStatement st = connection.prepareStatement(SQL_INSERT_PARTICIPANTES);)
        {
            st.setString(1, nombre);
            st.setString(2, telefono);
            st.setString(3, region);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

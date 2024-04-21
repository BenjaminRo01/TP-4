package ejercicio3.persistent;

import ejercicio3.model.Concurso;
import ejercicio3.model.Inscripcion;
import ejercicio3.model.InscripcionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscripcionJdbcDAO implements InscripcionDAO {
    private String url;
    private String user;
    private String pwd;
    private final String SQL_INSERT = "INSERT INTO inscriptos (idConcurso, apellido, nombre, telefono, email)" +
            " VALUES (?,?,?,?,?);";

    public InscripcionJdbcDAO(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    @Override
    public void agregarInscripcion(Inscripcion inscripcion, Concurso concurso) {
        try(Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstm = conn.prepareStatement(SQL_INSERT);){
            pstm.setString(1, concurso.id());
            pstm.setString(2, inscripcion.apellido());
            pstm.setString(3, inscripcion.nombre());
            pstm.setString(4, inscripcion.telefono());
            pstm.setString(5, inscripcion.email());
            pstm.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

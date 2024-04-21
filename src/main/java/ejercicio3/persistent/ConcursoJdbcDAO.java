package ejercicio3.persistent;

import ejercicio3.model.Concurso;
import ejercicio3.model.ConcursoDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcursoJdbcDAO implements ConcursoDAO {
    private String url;
    private String user;
    private String pwd;
    private final String SQL_SELECT_ALL = "SELECT * FROM concursos;";
    private final String SQL_SELECT = "SELECT * FROM concursos WHERE nombre = ?;";
    public ConcursoJdbcDAO(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    @Override
    public List<Concurso> obtenerListaConcursos() {
        List<Concurso> concursos = new ArrayList<Concurso>();
        Concurso concurso;
        try(Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);) {
            while (rs.next()){
                concurso = new Concurso(rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getDate("fechaInicioInscripcion").toLocalDate(),
                        rs.getDate("fechaFinInscripcion").toLocalDate());
                concursos.add(concurso);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return concursos;
    }

    @Override
    public Optional<Concurso> obtenerConcurso(String nombre) {
        Concurso concurso = null;
        ResultSet rs = null;
        try(Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstm = conn.prepareStatement(SQL_SELECT);){
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            while (rs.next()){
                concurso = new Concurso(rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getDate("fechaInicioInscripcion").toLocalDate(),
                        rs.getDate("fechaFinInscripcion").toLocalDate());
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(concurso);
    }
}

package ejercicio3.model;

public class Inscripcion {
    private final String apellido;
    private final String nombre;
    private final String teléfono;
    private final String email;
    private final int idConcurso;
    public Inscripcion(String apellido, String nombre, String teléfono, String email, int idConcurso) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.teléfono = teléfono;
        this.email = email;
        this.idConcurso = idConcurso;
    }
}

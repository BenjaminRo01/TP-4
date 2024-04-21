package ejercicio3.model;

import java.time.LocalDate;

public class Concurso {
    private final String id;
    private final String nombre;
    private final LocalDate fechaInicioInscripcion;
    private final LocalDate fechaFinInscripcion;

    public Concurso(String id, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

}

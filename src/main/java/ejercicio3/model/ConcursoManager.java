package ejercicio3.model;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcursoManager {
    private final ConcursoDAO concursoDAO;
    private final InscripcionDAO inscripcionDAO;
    public ConcursoManager(ConcursoDAO concursoDAO, InscripcionDAO inscripcionDAO){
        this.concursoDAO = concursoDAO;
        this.inscripcionDAO = inscripcionDAO;
    }
    public List<String> todosLosConcursos() {
        // carga del archivo de texto concursos.txt los concursos
        List<Concurso> concursos = concursoDAO.obtenerListaConcursos();
        return concursos.stream().map(Concurso::nombre).collect(Collectors.toList());
    }
    public boolean saveInscription(String nombre, String apellido, String idParticipante, String email, String telefono, String nombreConcurso) {
        if (!validations(nombre, apellido, idParticipante, email, telefono, nombreConcurso)) {
            return false;
        }
        // Guarda en inscriptos.txt los datos de la persona y el concurso elegido
        Optional<Concurso> concursoOptional = concursoDAO.obtenerConcurso(nombreConcurso);
        if (concursoOptional.isEmpty()) {
            return false;
        }
        Concurso concurso = concursoOptional.get();
        Inscripcion inscripcion = new Inscripcion(apellido, nombre, telefono, email, concurso);
        inscripcionDAO.agregarInscripcion(inscripcion, concurso);
        return true;
    }
    private boolean validations(String nombre, String apellido, String idParticipante, String email, String telefono, String nombreConcurso) {
        if ("".equals(nombre)) {
            JOptionPane.showMessageDialog(null, "Nombre no puede ser vacio");
            return false;
        }
        if ("".equals(apellido)) {
            JOptionPane.showMessageDialog(null,
                    "apellido no puede ser vacio");
            return false;
        }
        if ("".equals(idParticipante)) {
            JOptionPane.showMessageDialog(null, "dni no puede ser vacio");
            return false;
        }
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(null,
                    "email debe ser válido");
            return false;
        }
        if (!checkPhone(telefono)) {
            JOptionPane.showMessageDialog(null,
                    "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
            return false;
        }
        if (nombreConcurso.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe elegir un Concurso");
            return false;
        }
        return true;
    }
    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }
}

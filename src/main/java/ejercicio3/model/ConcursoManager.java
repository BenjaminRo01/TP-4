package ejercicio3.model;

import javax.swing.*;
import java.util.List;

public class ConcursoManager {
    public List<String> todosLosConcursos() {
        // carga del archivo de texto concursos.txt los concursos
        return null;
    }
    public void saveInscription(String nombre, String apellido, String id, String email, String telefono, String nombreConcurso) {
        if (validations(nombre,apellido,id,email,telefono,nombreConcurso)) {
            // Guarda en inscriptos.txt los datos de la persona y el concurso elegido
        }
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

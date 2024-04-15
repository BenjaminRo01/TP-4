package ejercicio1.model;

import javax.swing.JOptionPane;

public class AgregarParticipante {
    private static final String REGEX_TELEFONO = "\\d{4}-\\d{6}";
    ParticipantesDatabaseService databaseService;
    public AgregarParticipante(ParticipantesDatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    public void agregar(String nombre, String telefono, String region){
        this.databaseService.insert(nombre, telefono, region);
    }
    public boolean validarCampos(String nombre, String telefono, String region){
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe cargar un nombre");
            return false;
        }
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe cargar un telefono");
            return false;
        }
        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(null, "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
            return false;
        }
        if (!region.equals("China") && !region.equals("US") && !region.equals("Europa")) {
            JOptionPane.showMessageDialog(null, "Region desconocida. Las conocidas son: China, US, Europa");
            return false;
        }
        return true;
    }
    private boolean validarTelefono(String telefono) {
        return telefono.matches(REGEX_TELEFONO);
    }
}

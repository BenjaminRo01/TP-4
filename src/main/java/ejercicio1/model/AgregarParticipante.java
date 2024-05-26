package ejercicio1.model;

import javax.swing.*;
import java.util.List;

public class AgregarParticipante extends Observable{
    private static final String REGEX_TELEFONO = "\\d{4}-\\d{6}";
    private static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final int EXITO_INSERT = 1;
    ParticipantesDatabaseService databaseService;
    public AgregarParticipante(ParticipantesDatabaseService databaseService, List<Observer> observadores) {
        this.databaseService = databaseService;
        for (Observer o : observadores){
            this.agregarObservadores(o);
        }
    }
    public void agregar(String nombre, String telefono, String region, String email){
        int exito = this.databaseService.insert(nombre, telefono, region);
        if (exito != EXITO_INSERT) {
            JOptionPane.showMessageDialog(null, "No se pudo inscribir al participante.");
            return;
        }
        this.notificar(nombre, email);
    }
    public boolean validarCampos(String nombre, String telefono, String region, String email){
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
        if(!validarEmail(email)){
            JOptionPane.showMessageDialog(null, "Email inválido.");
            return false;
        }
        return true;
    }
    private boolean validarTelefono(String telefono) {
        return telefono.matches(REGEX_TELEFONO);
    }
    private boolean validarEmail(String email){
        return email.matches(REGEX_EMAIL);
    }
}

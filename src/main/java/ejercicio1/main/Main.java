package ejercicio1.main;

import ejercicio1.database.JdbcDatabaseParticipantes;
import ejercicio1.model.AgregarParticipante;
import ejercicio1.ui.AddParticipantView;

import java.awt.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/tp-4";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                var agregarParticipante = new AgregarParticipante(new JdbcDatabaseParticipantes(URL, USER, PASSWORD));
                new AddParticipantView(agregarParticipante).setupUIComponents();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
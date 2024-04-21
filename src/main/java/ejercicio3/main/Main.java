package ejercicio3.main;
import ejercicio3.model.ConcursoManager;
import ejercicio3.persistent.ConcursoTxtDAO;
import ejercicio3.persistent.InscripcionTxtDAO;
import ejercicio3.ui.RadioCompetition;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start();
                } catch (Exception e) {
                    // log exception...
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void start() {
        new RadioCompetition(
                new ConcursoManager(
                        new ConcursoTxtDAO("src/main/resources/concursos.txt"),
                        new InscripcionTxtDAO("src/main/resources/inscriptos.txt")));
    }
}
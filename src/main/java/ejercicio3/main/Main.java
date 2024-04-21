package ejercicio3.main;
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
                    System.out.println(e);
                }
            }
        });
    }
    private void start() {
        new RadioCompetition();
    }
}
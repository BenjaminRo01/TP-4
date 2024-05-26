package ejercicio1.ui;

import ejercicio1.model.AgregarParticipante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class VentanaAgregarParticipante extends JFrame{
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;
    private JTextField email;
    private final AgregarParticipante agregarParticipante;

    public VentanaAgregarParticipante(AgregarParticipante agregarParticipante){
        this.agregarParticipante = agregarParticipante;
    }

    public void setupUIComponents() {
        setTitle("Add Participant");
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.email = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        contentPane.add(new JLabel("Email: "));
        contentPane.add(email);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(e -> {
            try {
                onBotonCargar();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        });
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }
    private void onBotonCargar() throws SQLException {
        boolean isValid = this.agregarParticipante.validarCampos(nombre.getText(), telefono.getText(), region.getText(), email.getText());
        if (isValid){
            this.agregarParticipante.agregar(nombre.getText(), telefono.getText(), region.getText(), email.getText());
            this.limpiarCampos();
        }
    }
    private void limpiarCampos(){
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("");
        this.email.setText("");
    }
}

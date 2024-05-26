package ejercicio1.model;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailNotificacion implements Observer{
    private String host;
    private String port;
    private String username;
    private String password;
    private String from;
    public EmailNotificacion(String host, String port, String username, String password, String from) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
    }
    @Override
    public void actualizar(String nombre, String email) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
        //crea una Session
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //Se crea un objeto MimeMessage para poder realizar las operaciones de configuracion del email
            Message message = new MimeMessage(session);
            //Se añade "quien" envia el mail
            message.setFrom(new InternetAddress(this.from));
            //Se añade "a quien" se va a enviar el mail
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Inscripción realizada.");
            //Se añade el contenido del mensaje de texto
            String text = "Hola " + nombre + ",\n \tsu inscripción a sido realizada exitosamente.\nSaludos!.";
            message.setText(text);
            //Se envia el mensaje email
            Transport.send(message);
        }
        catch (Exception e){
            throw new RuntimeException("No se ha podido enviar el correo.", e);
        }
    }
}

package ejercicio2.notification;

import ejercicio2.model.Employee;
import ejercicio2.model.Notification;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class EmailNotification implements Notification {
    private String host;
    private String port;
    private String username;
    private String password;
    private String from;

    public EmailNotification(String host, String port, String username, String password, String from) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
    }

    @Override
    public void send(Employee employee) {
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
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(employee.email()));
            //Se añade el asunto del email
            message.setSubject("Feliz cumpleaños!");
            //Se añade el contenido del mensaje de texto
            String text = "Feliz cumpleaños querido " + employee.name();
            message.setText(text);
            //Se envia el mensaje email
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException("No se ha podido enviar el mail", e);
        }
    }
}

package ejercicio2.main;

import ejercicio2.model.BirthdayNotification;
import ejercicio2.notification.EmailNotification;
import ejercicio2.persistent.EmployeeCsvDAO;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String from = "from@example.com";
        final String username = "c276e21b0ab06a";
        final String password = "3416ef01d5a717";
        String host = "sandbox.smtp.mailtrap.io";
        String port = "2525";
        var birthdayNotification = new BirthdayNotification(
                new EmployeeCsvDAO("src/main/resources/employees.csv"),
                new EmailNotification(host, port, username, password, from));
        birthdayNotification.sendBirthdayNotification(LocalDate.of(2024, 4, 21));
    }
}

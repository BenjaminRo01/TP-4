package ejercicio2;

import ejercicio2.model.BirthdayNotification;
import ejercicio2.model.FakeNotification;
import ejercicio2.notification.EmailNotification;
import ejercicio2.persistent.EmployeeCsvDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
public class NotificacionTest {
    @Test
    public void testBirthdayEmployee(){
        FakeNotification fakeNotification = new FakeNotification();
        var birthdayNotification = new BirthdayNotification(
                new EmployeeCsvDAO("src/main/resources/employees.csv"),
                fakeNotification);
        birthdayNotification.sendBirthdayNotification(LocalDate.of(2024, 9, 11));
        assertTrue(fakeNotification.startWith("Feliz cumpleaños querido Brian"));
    }
    @Test
    public void testBirthdayEmployees(){
        FakeNotification fakeNotification = new FakeNotification();
        var birthdayNotification = new BirthdayNotification(
                new EmployeeCsvDAO("src/main/resources/employees.csv"),
                fakeNotification);
        birthdayNotification.sendBirthdayNotification(LocalDate.of(2024, 4, 21));
        assertTrue(fakeNotification.startWith("Feliz cumpleaños querido Juan"));
        assertTrue(fakeNotification.startWith("Feliz cumpleaños querido Benjamin"));
    }
}

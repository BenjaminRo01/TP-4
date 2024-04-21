package ejercicio2.notification;

import ejercicio2.model.Employee;
import ejercicio2.model.Notification;

public class EmailNotification implements Notification {
    @Override
    public void send(Employee employee) {
        System.out.println(employee.surname() + " " + employee.name() + " " + employee.birthdate() + " " + employee.email());
    }
}

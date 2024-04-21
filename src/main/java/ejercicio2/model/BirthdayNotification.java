package ejercicio2.model;

import java.time.LocalDate;
import java.util.List;

public class BirthdayNotification {
    private final EmployeeDAO employeeDAO;
    private final Notification notification;

    public BirthdayNotification(EmployeeDAO employeeDAO, Notification notification) {
        this.employeeDAO = employeeDAO;
        this.notification = notification;
    }
    public void sendBirthdayNotification(LocalDate date){
        List<Employee> employees = this.employeeDAO.getAllEmployees();
        for (Employee employee : employees){
            if(employee.isBirthday(date)){
                notification.send(employee);
            }
        }
    }
}

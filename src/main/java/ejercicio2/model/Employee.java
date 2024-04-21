package ejercicio2.model;

import java.time.LocalDate;

public record Employee(String surname, String name, LocalDate birthdate, String email){
    public boolean isBirthday(LocalDate date){
        return birthdate.getMonth() == date.getMonth() && birthdate.getDayOfMonth() == date.getDayOfMonth();
    }
}

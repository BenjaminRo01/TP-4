package ejercicio2.model;

import java.util.ArrayList;
import java.util.List;

public class FakeNotification implements Notification{
    private final List<String> content;

    public FakeNotification(){
        this.content = new ArrayList<>();
    }
    @Override
    public void send(Employee employee) {
        this.content.add("Feliz cumpleaños querido " + employee.name());
    }
    public boolean startWith(String str){
        return this.content.contains(str);
    }
}

package ejercicio1.model;

import java.util.ArrayList;
import java.util.List;

abstract class Observable {
    private List<Observer> observadores;

    public Observable() {
        this.observadores = new ArrayList<>();
    }
    protected void agregarObservadores(Observer observer){
        this.observadores.add(observer);
    }
    protected void notificar(String nombre, String email){
        this.observadores.forEach(o -> o.actualizar(nombre, email));
    }
}

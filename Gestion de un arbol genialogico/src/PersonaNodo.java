import java.util.*;

class PersonaNodo {
    String nombre;
    String fechaNacimiento;
    String genero;
    List<PersonaNodo> hijos;
    PersonaNodo padre; // para rastrear antepasados

    public PersonaNodo(String nombre, String fechaNacimiento, String genero) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.hijos = new ArrayList<>();
        this.padre = null;
    }

    public void agregarHijo(PersonaNodo hijo) {
        hijo.padre = this;
        hijos.add(hijo);
    }
}

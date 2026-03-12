import java.util.*;

public class ArbolGenealogico {
    private PersonaNodo raiz;

    public ArbolGenealogico(String nombre, String fechaNacimiento, String genero) {
        raiz = new PersonaNodo(nombre, fechaNacimiento, genero);
    }


    public PersonaNodo getRaiz() {
        return raiz;
    }

    public PersonaNodo buscar(String nombre, PersonaNodo nodo) {
        if (nodo == null) return null;
        if (nodo.nombre.equalsIgnoreCase(nombre)) return nodo;
        for (PersonaNodo hijo : nodo.hijos) {
            PersonaNodo encontrado = buscar(nombre, hijo);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    public void agregarPersona(String nombrePadre, String nombre, String fechaNacimiento, String genero) {
        PersonaNodo padre = buscar(nombrePadre, raiz);
        if (padre != null) {
            padre.agregarHijo(new PersonaNodo(nombre, fechaNacimiento, genero));
        } else {
            System.out.println("Padre/Madre '" + nombrePadre + "' no encontrado.");
        }
    }

    public void mostrarAntepasados(String nombre) {
        PersonaNodo persona = buscar(nombre, raiz);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }
        PersonaNodo actual = persona.padre;
        if (actual == null) {
            System.out.println(nombre + " no tiene antepasados registrados.");
            return;
        }
        System.out.println("Antepasados de " + nombre + ":");
        while (actual != null) {
            System.out.println(" - " + actual.nombre);
            actual = actual.padre;
        }
    }

    public void mostrarDescendientes(String nombre) {
        PersonaNodo persona = buscar(nombre, raiz);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }
        Queue<PersonaNodo> cola = new LinkedList<>();
        cola.add(persona);
        int generacion = 0;

        while (!cola.isEmpty()) {
            int size = cola.size();
            generacion++;
            System.out.println("Generación " + generacion + ":");
            for (int i = 0; i < size; i++) {
                PersonaNodo actual = cola.poll();
                for (PersonaNodo hijo : actual.hijos) {
                    System.out.println(" - " + hijo.nombre);
                    cola.add(hijo);
                }
            }
        }
    }

    public void eliminarRama(String nombre) {
        PersonaNodo persona = buscar(nombre, raiz);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }
        if (persona.padre != null) {
            persona.padre.hijos.remove(persona);
            System.out.println("Se eliminó la rama de " + nombre);
        } else {
            System.out.println("No se puede eliminar la raíz del árbol.");
        }
    }

    public int profundidad(PersonaNodo nodo) {
        if (nodo == null) return 0;
        int max = 0;
        for (PersonaNodo hijo : nodo.hijos) {
            max = Math.max(max, profundidad(hijo));
        }
        return max + 1;
    }

    public void recorridoPorGeneraciones() {
        Queue<PersonaNodo> cola = new LinkedList<>();
        cola.add(raiz);
        int generacion = 0;

        while (!cola.isEmpty()) {
            int size = cola.size();
            generacion++;
            System.out.println("Generación " + generacion + ":");
            for (int i = 0; i < size; i++) {
                PersonaNodo actual = cola.poll();
                System.out.println(" - " + actual.nombre);
                cola.addAll(actual.hijos);
            }
        }
    }
}

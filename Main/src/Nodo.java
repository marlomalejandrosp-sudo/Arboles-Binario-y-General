import java.util.ArrayList;
import java.util.List;

class Nodo<E> {
    E dato;
    List<Nodo<E>> hijos;
    Nodo<E> izquierdo, derecho;

    public Nodo(E valor) {
        this.dato = valor;
        this.hijos = new ArrayList<>();
        this.izquierdo = null;
        this.derecho = null;
    }

    // Métodos para árbol general
    public void agregarHijo(Nodo<E> hijo) {
        hijos.add(hijo);
    }

    // Métodos para árbol binario
    public void setIzquierdo(Nodo<E> nodo) {
        this.izquierdo = nodo;
    }

    public void setDerecho(Nodo<E> nodo) {
        this.derecho = nodo;
    }
}

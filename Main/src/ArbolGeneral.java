import java.util.ArrayList;
import java.util.List;

public class ArbolGeneral<E> {
    Nodo<E> raiz;

    public ArbolGeneral(E valorRaiz) {
        raiz = new Nodo<>(valorRaiz);
    }

    public void mostrarEstructura(Nodo<E> nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print("Nodo: " + nodo.dato);

        if (!nodo.hijos.isEmpty()) {
            System.out.print(", hijos: ");
            for (int i = 0; i < nodo.hijos.size(); i++) {
                Nodo<E> hijo = nodo.hijos.get(i);
                System.out.print(hijo.dato);
                if (i < nodo.hijos.size() - 1)
                    System.out.print(", ");
                }
            } else {
            System.out.print(" (hoja)");
        }

        System.out.println();


        for (Nodo<E> hijo : nodo.hijos) {
            mostrarEstructura(hijo); } }

    // (DFS)
    public void imprimirDFS(Nodo<E> nodo) {
        if (nodo == null) return;

        System.out.print(nodo.dato + " ");

        for (Nodo<E> hijo : nodo.hijos) {
            imprimirDFS(hijo);
        }
    }


    // (BFS)
    public void imprimirBFS() {
        List<Nodo<E>> cola = new ArrayList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Nodo<E> actual = cola.remove(0);
            System.out.print(actual.dato + " ");
            cola.addAll(actual.hijos);
        }
        System.out.println();
    }
}


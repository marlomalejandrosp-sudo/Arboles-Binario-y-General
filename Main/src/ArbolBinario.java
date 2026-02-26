public class ArbolBinario<E extends Comparable<E>> {
    Nodo<E> raiz;

    public ArbolBinario(E valorRaiz) {
        raiz = new Nodo<>(valorRaiz);
    }

    public void mostrarEstructura(Nodo<E> nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print("Nodo: " + nodo.dato);

        if (nodo.izquierdo != null) {
            System.out.print(", hijo izquierdo: " + nodo.izquierdo.dato);
        }
        if (nodo.derecho != null) {
            System.out.print(", hijo derecho: " + nodo.derecho.dato);
        }


        if (nodo.izquierdo == null && nodo.derecho == null) {
            System.out.print(" (hoja)");
        }

        System.out.println();

        mostrarEstructura(nodo.izquierdo);
        mostrarEstructura(nodo.derecho);
    }


    public void insertar(Nodo<E> nodo, E valor) {
        if (valor.compareTo(nodo.dato) < 0) {
            if (nodo.izquierdo == null) nodo.setIzquierdo(new Nodo<>(valor));
            else insertar(nodo.izquierdo, valor);
        } else {
            if (nodo.derecho == null) nodo.setDerecho(new Nodo<>(valor));
            else insertar(nodo.derecho, valor);
        }
    }

    public void preOrden(Nodo<E> nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrden(nodo.izquierdo);
            preOrden(nodo.derecho);
        }
    }

    public void inOrden(Nodo<E> nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inOrden(nodo.derecho);
        }
    }

    public void postOrden(Nodo<E> nodo) {
        if (nodo != null) {
            postOrden(nodo.izquierdo);
            postOrden(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        ArbolGeneral<String> ag = new ArbolGeneral<>("A");
        Nodo<String> b = new Nodo<>("B");
        Nodo<String> c = new Nodo<>("C");
        Nodo<String> d = new Nodo<>("D");
        Nodo<String> e = new Nodo<>("E");

        ag.raiz.agregarHijo(b);
        ag.raiz.agregarHijo(c);
        ag.raiz.agregarHijo(d);
        b.agregarHijo(e);

        System.out.println("Estructura del árbol general:");
        ag.mostrarEstructura(ag.raiz);

        System.out.println("DFS:");
        ag.imprimirDFS(ag.raiz);
        System.out.println("\nBFS:");
        ag.imprimirBFS();

        ArbolBinario<Integer> ab = new ArbolBinario<>(50);
        ab.insertar(ab.raiz, 30);
        ab.insertar(ab.raiz, 70);
        ab.insertar(ab.raiz, 20);
        ab.insertar(ab.raiz, 40);
        ab.insertar(ab.raiz, 60);
        ab.insertar(ab.raiz, 80);

        System.out.println("Estructura del árbol binario:");
        ab.mostrarEstructura(ab.raiz);

        System.out.println("PreOrden:");
        ab.preOrden(ab.raiz);
        System.out.println("\nInOrden:");
        ab.inOrden(ab.raiz);
        System.out.println("\nPostOrden:");
        ab.postOrden(ab.raiz);
    }
}
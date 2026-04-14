public class Main {
    public static void main(String[] args) {

        System.out.println("GRAFO DEL EJERCICIO");
        Grafo grafo = new Grafo();

        grafo.agregarArista("A", "B", 20);
        grafo.agregarArista("A", "D", 50);
        grafo.agregarArista("B", "C", 10);
        grafo.agregarArista("C", "A", 25);
        grafo.agregarArista("C", "F", 45);
        grafo.agregarArista("D", "C", 30);
        grafo.agregarArista("D", "E", 10);
        grafo.agregarArista("E", "F", 5);
        grafo.agregarArista("F", "A", 30);
        grafo.agregarArista("F", "D", 25);

        grafo.mostrarGrafo();

        System.out.println("\nRECORRIDOS");
        System.out.println("BFS: " + grafo.bfs("D"));
        System.out.println("DFS: " + grafo.dfs("A"));

        // CREATE
        System.out.println("\nCREATE: Agregar nuevo vértice y arista");
        grafo.agregarVertice("G");
        grafo.agregarArista("E", "G", 15);
        grafo.mostrarGrafo();

        // READ
        System.out.println("\nREAD: Todas las aristas");
        System.out.println(grafo.obtenerTodasLasAristas());
        grafo.mostrarGrafo();

        // UPDATE
        System.out.println("\nUPDATE: Cambiar peso");
        grafo.modificarPesoArista("A", "D", 55);
        grafo.mostrarGrafo();

        // DELETE
        System.out.println("\nDELETE: Eliminar arista");
        grafo.eliminarArista("C", "F");
        grafo.mostrarGrafo();

        System.out.println("\nDELETE: Eliminar vértice");
        grafo.eliminarVertice("G");
        grafo.mostrarGrafo();

    }
}
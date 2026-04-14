public class Main {
    public static void main(String[] args) {

        System.out.println("GRAFO DEL EJERCICIO");

        GrafoMatriz grafo = new GrafoMatriz();

        //CREAR VÉRTICES
        System.out.println("\nVértices");
        try {
            grafo.agregarVertice("A");
            grafo.agregarVertice("B");
            grafo.agregarVertice("C");
            grafo.agregarVertice("D");
            grafo.agregarVertice("E");
            grafo.agregarVertice("F");
            grafo.agregarVertice("G");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        //CREAR ARISTAS
        System.out.println("\nAristas");
        try {
            // A
            grafo.agregarArista("A", "B");
            grafo.agregarArista("A", "C");
            grafo.agregarArista("A", "D");
            // B
            grafo.agregarArista("B", "C");
            grafo.agregarArista("B", "E");
            grafo.agregarArista("B", "G");
            // C
            grafo.agregarArista("C", "D");
            grafo.agregarArista("C", "F");
            // D
            grafo.agregarArista("D", "G");
            // E
            grafo.agregarArista("E", "F");
            grafo.agregarArista("E", "G");
            // F
            grafo.agregarArista("F", "G");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        grafo.mostrarGrafo();

        //READ
        System.out.println("\nREAD: Consultas");
        System.out.println("Vértices: " + grafo.obtenerVertices());
        System.out.println("Adyacentes de A: " + grafo.obtenerAdyacentes("A"));
        System.out.println("Adyacentes de G: " + grafo.obtenerAdyacentes("G"));
        System.out.println("Grado de B: " + grafo.obtenerGrado("B"));
        System.out.println("Todas las aristas: " + grafo.obtenerTodasLasAristas());

        //RECORRIDOS
        System.out.println("\nRECORRIDOS");
        System.out.println("BFS: " + grafo.bfs("C"));
        System.out.println("DFS: " + grafo.dfs("G"));

        //UPDATE
        System.out.println("\nModificar nombre de vértice");
        try {
            grafo.modificarVertice("G", "H");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        grafo.mostrarGrafo();

        //DELETE
        System.out.println("\nEliminar arista");
        try {
            grafo.eliminarArista("B", "E");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        grafo.mostrarGrafo();

        System.out.println("\nEliminar vértice");
        try {
            grafo.eliminarVertice("D");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        grafo.mostrarGrafo();

    }
}
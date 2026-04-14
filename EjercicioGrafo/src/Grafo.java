import java.util.*;

public class Grafo {
    private final Map<String, List<Arista>> adyacencia;

    public Grafo() {
        adyacencia = new HashMap<>();
    }

    // CREATE:
    // Agregar vértice
    public void agregarVertice(String vertice) {
        if (vertice == null || vertice.trim().isEmpty()) {
            System.err.println("El nombre del vértice no puede estar vacío.");
        }
        adyacencia.putIfAbsent(vertice, new LinkedList<>());
        System.out.println("Vértice agregado: " + vertice);
    }

    //Agregar arista ponderada
    public void agregarArista(String origen, String destino, int peso) {
        if (origen == null || destino == null || origen.trim().isEmpty() || destino.trim().isEmpty()) {
            System.err.println("Origen y destino no pueden ser nulos o vacíos.");
        }
        if (peso < 0) {
            System.err.println("El peso no puede ser negativo.");
        }
        if (origen.equals(destino)) {
            System.err.println("No se permiten bucles.");
        }

        adyacencia.putIfAbsent(origen, new LinkedList<>());
        adyacencia.putIfAbsent(destino, new LinkedList<>());

        for (Arista a : adyacencia.get(origen)) {
            if (a.destino.equals(destino)) {
                System.err.println("La arista " + origen + " → " + destino + " ya existe.");
            }
        }

        adyacencia.get(origen).add(new Arista(destino, peso));
        System.out.println("Arista agregada: " + origen + " -> " + destino + " (" + peso + ")");
    }

    // READ
    public List<String> obtenerTodasLasAristas() {
        List<String> aristas = new ArrayList<>();
        for (String origen : adyacencia.keySet()) {
            for (Arista a : adyacencia.get(origen)) {
                aristas.add(origen + " → " + a.destino + " (" + a.peso + ")");
            }
        }
        return aristas;
    }

    // UPDATE
    public void modificarPesoArista(String origen, String destino, int nuevoPeso) {
        if (!adyacencia.containsKey(origen)) {
            System.err.println("El vértice origen " + origen + " no existe.");
        }
        if (nuevoPeso < 0) {
            System.err.println("El peso no puede ser negativo.");
        }

        for (Arista a : adyacencia.get(origen)) {
            if (a.destino.equals(destino)) {
                int pesoAnterior = a.peso;
                a.peso = nuevoPeso;
                System.out.println("Peso modificado: " + origen + " → " + destino +
                        " de " + pesoAnterior + " a " + nuevoPeso);
                return;
            }
        }
        System.err.println("La arista " + origen + " → " + destino + " no existe.");
    }

    // DELETE:
    // Eliminar arista
    public void eliminarArista(String origen, String destino) {
        if (!adyacencia.containsKey(origen)) {
            System.err.println("El vértice origen " + origen + " no existe.");
        }

        Iterator<Arista> it = adyacencia.get(origen).iterator();
        while (it.hasNext()) {
            Arista a = it.next();
            if (a.destino.equals(destino)) {
                it.remove();
                System.out.println("Arista eliminada: " + origen + " → " + destino);
                return;
            }
        }
        System.err.println("La arista " + origen + " → " + destino + " no existe.");
    }

    //Eliminar vértice y todas sus aristas incidentes
    public void eliminarVertice(String vertice) {
        if (!adyacencia.containsKey(vertice)) {
            System.err.println("El vértice " + vertice + " no existe.");
        }

        adyacencia.remove(vertice);

        for (String origen : adyacencia.keySet()) {
            adyacencia.get(origen).removeIf(a -> a.destino.equals(vertice));
        }
        System.out.println("Vértice eliminado: " + vertice);
    }

    // BFS
    public List<String> bfs(String inicio) {
        if (!adyacencia.containsKey(inicio)) {
            throw new IllegalArgumentException("El vértice " + inicio + " no existe.");
        }

        List<String> recorrido = new ArrayList<>();
        Set<String> visitado = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        visitado.add(inicio);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            recorrido.add(actual);
            for (Arista a : adyacencia.getOrDefault(actual, new LinkedList<>())) {
                if (!visitado.contains(a.destino)) {
                    visitado.add(a.destino);
                    cola.add(a.destino);
                }
            }
        }
        return recorrido;
    }

    // DFS
    public List<String> dfs(String inicio) {
        if (!adyacencia.containsKey(inicio)) {
            System.err.println("El vértice " + inicio + " no existe.");
        }

        List<String> recorrido = new ArrayList<>();
        Set<String> visitado = new HashSet<>();
        dfsRecursivo(inicio, visitado, recorrido);
        return recorrido;
    }

    private void dfsRecursivo(String actual, Set<String> visitado, List<String> recorrido) {
        visitado.add(actual);
        recorrido.add(actual);
        for (Arista a : adyacencia.getOrDefault(actual, new LinkedList<>())) {
            if (!visitado.contains(a.destino)) {
                dfsRecursivo(a.destino, visitado, recorrido);
            }
        }
    }

    public void mostrarGrafo() {
        System.out.println("\nGRAFO (Listas de Adyacencia)");
        List<String> verticesOrdenados = new ArrayList<>(adyacencia.keySet());
        Collections.sort(verticesOrdenados);
        for (String v : verticesOrdenados) {
            System.out.println(v + " -> " + adyacencia.get(v));
        }
    }
}
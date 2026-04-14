public class GrafoMatriz {
    private int[][] matrizAdyacencia;
    private String[] vertices;
    private int numVertices;
    private static final int MAX_VERTICES = 100;

    public GrafoMatriz() {
        this.matrizAdyacencia = new int[MAX_VERTICES][MAX_VERTICES];
        this.vertices = new String[MAX_VERTICES];
        this.numVertices = 0;
    }

    private int obtenerIndice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    private boolean verticeExiste(String vertice) {
        return obtenerIndice(vertice) != -1;
    }

    private void validarVertice(String vertice, String mensaje) {
        if (!verticeExiste(vertice)) {
            throw new IllegalArgumentException(mensaje + ": El vértice '" + vertice + "' no existe.");
        }
    }

    private void validarVertices(String vertice1, String vertice2) {
        if (!verticeExiste(vertice1)) {
            throw new IllegalArgumentException("El vértice '" + vertice1 + "' no existe.");
        }
        if (!verticeExiste(vertice2)) {
            throw new IllegalArgumentException("El vértice '" + vertice2 + "' no existe.");
        }
    }

    public void agregarVertice(String info) {
        if (info == null || info.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: La información del vértice no puede estar vacía.");
        }
        if (numVertices >= MAX_VERTICES) {
            throw new IllegalStateException("Error: Se alcanzó el número máximo de vértices (" + MAX_VERTICES + ").");
        }
        if (verticeExiste(info)) {
            throw new IllegalArgumentException("Error: El vértice '" + info + "' ya existe.");
        }

        vertices[numVertices] = info;
        numVertices++;
        System.out.println("Vértice agregado: " + info);
    }

    public void agregarArista(String origen, String destino) {
        if (origen == null || destino == null || origen.trim().isEmpty() || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Origen y destino no pueden ser nulos o vacíos.");
        }
        if (origen.equals(destino)) {
            throw new IllegalArgumentException("Error: No se permiten bucles (auto-conexiones).");
        }

        validarVertices(origen, destino);

        int idxOrigen = obtenerIndice(origen);
        int idxDestino = obtenerIndice(destino);

        if (matrizAdyacencia[idxOrigen][idxDestino] == 1) {
            throw new IllegalArgumentException("Error: La arista " + origen + " — " + destino + " ya existe.");
        }

        matrizAdyacencia[idxOrigen][idxDestino] = 1;
        matrizAdyacencia[idxDestino][idxOrigen] = 1;

        System.out.println("Arista agregada: " + origen + " — " + destino);
    }

    public String obtenerAdyacentes(String vertice) {
        validarVertice(vertice, "Error al obtener adyacentes");
        int idx = obtenerIndice(vertice);

        StringBuilder sb = new StringBuilder("[");
        boolean primero = true;

        for (int i = 0; i < numVertices; i++) {
            if (matrizAdyacencia[idx][i] == 1) {
                if (!primero) {
                    sb.append(", ");
                }
                sb.append(vertices[i]);
                primero = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String obtenerTodasLasAristas() {
        StringBuilder sb = new StringBuilder("[");
        boolean primera = true;

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {  // Solo triangular superior por ser no dirigido
                if (matrizAdyacencia[i][j] == 1) {
                    if (!primera) {
                        sb.append(", ");
                    }
                    sb.append(vertices[i]).append("—").append(vertices[j]);
                    primera = false;
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int obtenerGrado(String vertice) {
        validarVertice(vertice, "Error al obtener grado");
        int idx = obtenerIndice(vertice);
        int grado = 0;
        for (int i = 0; i < numVertices; i++) {
            grado += matrizAdyacencia[idx][i];
        }
        return grado;
    }

    public void modificarVertice(String viejoNombre, String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nuevo nombre no puede estar vacío.");
        }
        validarVertice(viejoNombre, "Error al modificar vértice");

        if (verticeExiste(nuevoNombre) && !viejoNombre.equals(nuevoNombre)) {
            throw new IllegalArgumentException("Error: El vértice '" + nuevoNombre + "' ya existe.");
        }

        int idx = obtenerIndice(viejoNombre);
        vertices[idx] = nuevoNombre;
        System.out.println("Vértice modificado: '" + viejoNombre + "' -> '" + nuevoNombre + "'");
    }

    public void eliminarArista(String vertice1, String vertice2) {
        validarVertices(vertice1, vertice2);
        int idx1 = obtenerIndice(vertice1);
        int idx2 = obtenerIndice(vertice2);

        if (matrizAdyacencia[idx1][idx2] == 0) {
            throw new IllegalArgumentException("Error: La arista " + vertice1 + " — " + vertice2 + " no existe.");
        }

        matrizAdyacencia[idx1][idx2] = 0;
        matrizAdyacencia[idx2][idx1] = 0;
        System.out.println("Arista eliminada: " + vertice1 + " — " + vertice2);
    }

    public void eliminarVertice(String vertice) {
        validarVertice(vertice, "Error al eliminar vértice");
        int idxEliminar = obtenerIndice(vertice);

        for (int i = 0; i < numVertices; i++) {
            if (matrizAdyacencia[idxEliminar][i] == 1) {
                System.out.println("Arista eliminada: " + vertices[idxEliminar] + " — " + vertices[i]);
            }
            matrizAdyacencia[idxEliminar][i] = 0;
            matrizAdyacencia[i][idxEliminar] = 0;
        }

        for (int i = idxEliminar; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
            for (int j = 0; j < numVertices; j++) {
                matrizAdyacencia[i][j] = matrizAdyacencia[i + 1][j];
                matrizAdyacencia[j][i] = matrizAdyacencia[j][i + 1];
            }
        }

        for (int j = 0; j < MAX_VERTICES; j++) {
            matrizAdyacencia[numVertices - 1][j] = 0;
            matrizAdyacencia[j][numVertices - 1] = 0;
        }
        vertices[numVertices - 1] = null;
        numVertices--;

        System.out.println("Vértice eliminado: " + vertice);
    }

    public String bfs(String inicio) {
        validarVertice(inicio, "Error en BFS");
        int idxInicio = obtenerIndice(inicio);

        StringBuilder resultado = new StringBuilder("[");
        boolean[] visitado = new boolean[numVertices];

        int[] cola = new int[MAX_VERTICES];
        int frente = 0, fin = 0;

        visitado[idxInicio] = true;
        cola[fin++] = idxInicio;

        boolean primero = true;

        while (frente < fin) {
            int actual = cola[frente++];

            if (!primero) {
                resultado.append(", ");
            }
            resultado.append(vertices[actual]);
            primero = false;

            Integer[] indicesOrdenados = new Integer[numVertices];
            for (int i = 0; i < numVertices; i++) {
                indicesOrdenados[i] = i;
            }

            java.util.Arrays.sort(indicesOrdenados, (a, b) -> vertices[a].compareTo(vertices[b]));

            for (int i : indicesOrdenados) {
                if (matrizAdyacencia[actual][i] == 1 && !visitado[i]) {
                    visitado[i] = true;
                    cola[fin++] = i;
                }
            }
        }

        resultado.append("]");
        return resultado.toString();
    }

    public String dfs(String inicio) {
        validarVertice(inicio, "Error en DFS");
        int idxInicio = obtenerIndice(inicio);

        StringBuilder resultado = new StringBuilder("[");
        boolean[] visitado = new boolean[numVertices];

        dfsRecursivo(idxInicio, visitado, resultado, new boolean[]{true});

        resultado.append("]");
        return resultado.toString();
    }

    private void dfsRecursivo(int actual, boolean[] visitado, StringBuilder resultado, boolean[] primeraVez) {
        visitado[actual] = true;

        if (!primeraVez[0]) {
            resultado.append(", ");
        }
        resultado.append(vertices[actual]);
        primeraVez[0] = false;

        for (int i = 0; i < numVertices; i++) {
            if (matrizAdyacencia[actual][i] == 1 && !visitado[i]) {
                dfsRecursivo(i, visitado, resultado, primeraVez);
            }
        }
    }

    public void mostrarGrafo() {
        System.out.println("\n--- GRAFO (Matriz de Adyacencia) ---");
        if (numVertices == 0) {
            System.out.println("Grafo vacío");
            return;
        }

        System.out.print("    ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + "  ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String obtenerVertices() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < numVertices; i++) {
            if (i > 0) sb.append(", ");
            sb.append(vertices[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
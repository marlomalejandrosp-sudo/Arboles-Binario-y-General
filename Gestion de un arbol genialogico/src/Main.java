public class Main{

public static void main(String[] args) {
    ArbolGenealogico arbol = new ArbolGenealogico("Juan", "1950-01-01", "M");

    arbol.agregarPersona("Juan", "Carlos", "1975-05-10", "M");
    arbol.agregarPersona("Juan", "Ana", "1978-08-20", "F");
    arbol.agregarPersona("Carlos", "Luis", "2000-02-15", "M");


    arbol.mostrarAntepasados("Luis");
    arbol.mostrarAntepasados("Juan");

    System.out.println("\nDescendientes de Juan:");
    arbol.mostrarDescendientes("Juan");

    arbol.eliminarRama("Carlos");

    System.out.println("\nProfundidad del árbol: " + arbol.profundidad(arbol.getRaiz()));

    System.out.println("\nRecorrido por generaciones:");
    arbol.recorridoPorGeneraciones();
}
}
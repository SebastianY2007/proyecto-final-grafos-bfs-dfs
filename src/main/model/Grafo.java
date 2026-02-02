package main.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Grafo {

    // Estructura principal del grafo:
    // guarda cada nodo usando su ID como clave
    private Map<String, Nodo> nodos = new HashMap<>();

    /**
     * Agrega un nodo al grafo.
     * El ID del nodo se usa como clave en el mapa.
     */
    public void agregarNodo(Nodo n) {
        nodos.put(n.getId(), n);
    }

    /**
     * Devuelve un nodo a partir de su ID.
     * Se usa cuando se quiere acceder a un nodo específico.
     */
    public Nodo getNodo(String id) {
        return nodos.get(id);
    }

    /**
     * Conecta dos nodos del grafo usando sus IDs.
     * La conexión es bidireccional (grafo no dirigido),
     * es decir, ambos nodos se agregan como vecinos.
     */
    public void conectar(String idA, String idB) {
        Nodo a = nodos.get(idA);
        Nodo b = nodos.get(idB);

        // Validación: si alguno no existe, se muestra error
        if (a == null || b == null) {
            System.out.println("Error: nodo no encontrado (" + idA + ", " + idB + ")");
            return;
        }

        // Conexión en ambos sentidos
        a.agregarVecino(b);
        b.agregarVecino(a);
    }

    /**
     * Devuelve todos los nodos del grafo.
     * Se usa para recorrerlos o dibujarlos.
     */
    public Collection<Nodo> getNodos() {
        return nodos.values();
    }
}

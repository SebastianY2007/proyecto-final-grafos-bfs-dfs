package main.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, Nodo> nodos;

    public Grafo() {
        nodos = new HashMap<>();
    }

    // Método para agregar un nodo al grafo
    public void agregarNodo(Nodo nodo) {
        nodos.put(nodo.getId(), nodo);
    }

    // Método para obtener un nodo por su ID
    public Nodo getNodo(String id) {
        return nodos.get(id);
    }

    // Conectar dos nodos del grafo de manera bidireccional
    public void conectar(String id1, String id2) {
        Nodo n1 = nodos.get(id1);
        Nodo n2 = nodos.get(id2);

        if (n1 != null && n2 != null) {
            n1.agregarVecino(n2);
            n2.agregarVecino(n1);
        }
    }

    // Devuelve todos los nodos que contiene el grafo
    public Collection<Nodo> getNodos() {
        return nodos.values();
    }
}
package main.controller;

import java.util.*;
import main.model.Nodo;

public class DFS {

    /**
     * Realiza la búsqueda DFS (Depth-First Search) desde un nodo inicio
     * hasta un nodo destino.
     *
     * DFS explora el grafo profundizando lo más posible antes de retroceder.
     * No garantiza el camino más corto.
     */
    public static List<Nodo> buscar(Nodo inicio, Nodo destino) {

        // Conjunto para almacenar los nodos que ya fueron visitados
        Set<Nodo> visitados = new HashSet<>();

        // Mapa que guarda desde qué nodo se llegó a otro
        // Sirve para reconstruir la ruta final
        Map<Nodo, Nodo> predecesor = new HashMap<>();

        // Se inicia la búsqueda recursiva desde el nodo inicial
        boolean encontrado = dfsRecursivo(inicio, destino, visitados, predecesor);

        // Si se encontró el destino, se reconstruye la ruta
        if (encontrado) {
            return reconstruirRuta(predecesor, inicio, destino);
        }

        // Si no existe camino entre inicio y destino
        return new ArrayList<>();
    }

    /**
     * Método recursivo que implementa el recorrido DFS.
     * Explora un camino completo antes de probar otro.
     */
    private static boolean dfsRecursivo(
            Nodo actual,
            Nodo destino,
            Set<Nodo> visitados,
            Map<Nodo, Nodo> predecesor) {

        // Se marca el nodo actual como visitado
        visitados.add(actual);

        // Si el nodo actual es el destino, se termina la búsqueda
        if (actual.equals(destino)) {
            return true;
        }

        // Se recorren todos los vecinos del nodo actual
        for (Nodo vecino : actual.getVecinos()) {

            // Solo se exploran vecinos no visitados
            if (!visitados.contains(vecino)) {

                // Se guarda que el vecino se alcanzó desde el nodo actual
                predecesor.put(vecino, actual);

                // Llamada recursiva para seguir profundizando
                if (dfsRecursivo(vecino, destino, visitados, predecesor)) {
                    return true;
                }
            }
        }

        // Si ningún camino desde este nodo lleva al destino
        return false;
    }

    /**
     * Reconstruye la ruta desde el nodo destino hasta el nodo inicio
     * usando el mapa de predecesores.
     */
    private static List<Nodo> reconstruirRuta(
            Map<Nodo, Nodo> predecesor,
            Nodo inicio,
            Nodo destino) {

        List<Nodo> ruta = new ArrayList<>();
        Nodo actual = destino;

        // Se retrocede desde el destino hasta llegar al inicio
        while (actual != null && !actual.equals(inicio)) {
            ruta.add(actual);
            actual = predecesor.get(actual);
        }

        // Se añade el nodo inicial
        if (actual != null) {
            ruta.add(inicio);
        }

        // Se invierte la lista para que quede en orden correcto
        Collections.reverse(ruta);

        return ruta;
    }
}

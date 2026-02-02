package main.controller;

import java.util.*;
import main.model.Nodo;

public class BFS {

    /**
     * Realiza la búsqueda BFS (Breadth-First Search) desde un nodo inicial
     * hasta un nodo destino.
     *
     * BFS recorre el grafo por niveles, garantizando encontrar el camino
     * más corto en cantidad de nodos.
     */
    public static List<Nodo> buscar(Nodo inicio, Nodo destino) {

        // Cola que permite recorrer el grafo por niveles (propio de BFS)
        Queue<Nodo> cola = new LinkedList<>();

        // Guarda los nodos que ya fueron visitados para no repetirlos
        Set<Nodo> visitados = new HashSet<>();

        // Guarda de dónde viene cada nodo para poder reconstruir la ruta final
        Map<Nodo, Nodo> predecesor = new HashMap<>();

        // Se inicia la búsqueda desde el nodo inicial
        cola.add(inicio);
        visitados.add(inicio);

        // Mientras haya nodos pendientes por explorar
        while (!cola.isEmpty()) {

            // Se toma el siguiente nodo de la cola
            Nodo actual = cola.poll();

            // Si llegamos al destino, ya no seguimos buscando
            // y reconstruimos el camino recorrido
            if (actual.equals(destino)) {
                return recontruirRuta(predecesor, inicio, destino);
            }

            // Se recorren todos los nodos vecinos (conectados)
            for (Nodo vecino : actual.getVecinos()) {

                // Si el vecino no ha sido visitado aún
                if (!visitados.contains(vecino)) {

                    // Se marca como visitado
                    visitados.add(vecino);

                    // Se guarda desde qué nodo se llegó a este vecino
                    predecesor.put(vecino, actual);

                    // Se añade a la cola para seguir explorando
                    cola.add(vecino);
                }
            }
        }

        // Si no existe camino entre inicio y destino
        return null;
    }

    /**
     * Reconstruye la ruta desde el nodo destino hasta el nodo inicio
     * usando el mapa de predecesores.
     */
    public static List<Nodo> recontruirRuta(
            Map<Nodo, Nodo> predecesor,
            Nodo inicio,
            Nodo destino) {

        List<Nodo> ruta = new ArrayList<>();

        // Se empieza desde el destino
        Nodo actual = destino;

        // Se va retrocediendo usando el mapa de predecesores
        while (actual != null && !actual.equals(inicio)) {
            ruta.add(actual);
            actual = predecesor.get(actual);
        }

        // Se agrega el nodo inicial al final del recorrido inverso
        if (actual != null) {
            ruta.add(inicio);
        }

        // Se invierte la lista para que quede de inicio a destino
        Collections.reverse(ruta);

        return ruta;
    }
}

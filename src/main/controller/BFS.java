package main.controller;

import java.util.*;
import main.model.Nodo;

public class BFS {
    public static List<Nodo> buscar(Nodo inicio, Nodo destino) {
        // Cola que recorrera el grafo por niveles
        Queue<Nodo> cola = new LinkedList<>(); 
        // Nodos visitados
        Set<Nodo> visitados = new HashSet<>();
        // Mapa para poder reconstruir la ruta final
        Map<Nodo, Nodo> predecesor = new HashMap<>();

        // Iniciar
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            // Si se llega al destino deseado, se recontruye la ruta
            if (actual.equals(destino)) {
                return recontruirRuta(predecesor, inicio, destino);
            }

            // Explorar los vecinos
            for (Nodo vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    predecesor.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        return null;
    }

    public static List<Nodo> recontruirRuta(Map<Nodo, Nodo> predecesor, Nodo inicio, Nodo destino) {
       List<Nodo> ruta = new ArrayList<>();
       Nodo actual = destino;
       
       while (actual != null && !actual.equals(inicio)) {
            ruta.add(actual);
            actual = predecesor.get(actual);
       }

       // Agregar el Nodo inicial
       if (actual != null) {
        ruta.add(inicio);
       }    

       Collections.reverse(ruta);

        return ruta;
    }
}

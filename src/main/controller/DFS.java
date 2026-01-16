package main.controller;

import java.util.*;
import main.model.Nodo;

public class DFS {
    public static List<Nodo> buscar(Nodo inicio, Nodo destino) {

        // Conjunto de nodos visitados
        Set<Nodo> visitados = new HashSet<>();

        // Mapa para reconstruir la ruta final
        Map<Nodo, Nodo> predecesor = new HashMap<>();

        // Ejecución recursiva
        boolean encontrado = dfsRecursivo(inicio, destino, visitados, predecesor);

        if (encontrado) {
            return reconstruirRuta(predecesor, inicio, destino);
        }

        // Si no hay ruta, se retorna lista vacía
        return new ArrayList<>();
    }

    private static boolean dfsRecursivo(
            Nodo actual,
            Nodo destino,
            Set<Nodo> visitados,
            Map<Nodo, Nodo> predecesor) {

        // Marcamos el nodo como visitado
        visitados.add(actual);

        // Si llegamos al destino, detenemos la búsqueda
        if (actual.equals(destino)) {
            return true;
        }

        // Exploramos cada vecino
        for (Nodo vecino : actual.getVecinos()) {
            if (!visitados.contains(vecino)) {
                predecesor.put(vecino, actual);

                // Llamada recursiva
                if (dfsRecursivo(vecino, destino, visitados, predecesor)) {
                    return true;
                }
            }
        }

        // No se encontró el destino en esta rama
        return false;
    }

    private static List<Nodo> reconstruirRuta(
            Map<Nodo, Nodo> predecesor,
            Nodo inicio,
            Nodo destino) {

        List<Nodo> ruta = new ArrayList<>();
        Nodo actual = destino;

        // Retroceso desde destino a inicio
        while (actual != null && !actual.equals(inicio)) {
            ruta.add(actual);
            actual = predecesor.get(actual);
        }

        if (actual != null) {
            ruta.add(inicio);
        }

        Collections.reverse(ruta);
        return ruta;
    }
}
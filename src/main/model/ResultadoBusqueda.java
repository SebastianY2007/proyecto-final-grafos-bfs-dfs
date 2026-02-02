package main.model;

import java.util.List;

public class ResultadoBusqueda {

    // Lista de nodos que fueron visitados durante la búsqueda
    // (útil para animación del algoritmo)
    private List<Nodo> visitados;

    // Lista de nodos que forman el camino final encontrado
    private List<Nodo> camino;

    /**
     * Constructor del resultado de una búsqueda.
     * Guarda tanto el recorrido como el camino final.
     */
    public ResultadoBusqueda(List<Nodo> visitados, List<Nodo> camino) {
        this.visitados = visitados;
        this.camino = camino;
    }

    // Devuelve los nodos visitados
    public List<Nodo> getVisitados() { 
        return visitados; 
    }

    // Devuelve el camino final
    public List<Nodo> getCamino() { 
        return camino; 
    }
}

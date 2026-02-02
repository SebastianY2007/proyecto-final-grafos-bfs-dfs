package main.model;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    // Identificador único del nodo (A, B, C, etc.)
    private String id;

    // Coordenadas del nodo en el mapa (pixeles)
    private int x;
    private int y;

    // Lista de nodos vecinos (conexiones)
    private List<Nodo> vecinos = new ArrayList<>();

    /**
     * Constructor del nodo.
     * Define su ID y su posición en el mapa.
     */
    public Nodo(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // Getters básicos
    public String getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }

    /**
     * Devuelve la lista de vecinos del nodo.
     * Es clave para BFS y DFS.
     */
    public List<Nodo> getVecinos() {
        return vecinos;
    }

    /**
     * Agrega un vecino al nodo.
     * Se evita duplicar conexiones.
     */
    public void agregarVecino(Nodo n) {
        if (!vecinos.contains(n)) {
            vecinos.add(n);
        }
    }
}

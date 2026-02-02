package main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import main.model.Grafo;
import main.model.Nodo;

public class GrafoLoader {

    /**
     * Carga el grafo desde un archivo de texto (grafo.txt).
     * El archivo contiene dos secciones:
     *  - NODOS: define cada nodo con su ID y coordenadas
     *  - CONEXIONES: define cómo se conectan los nodos entre sí
     */
    public static Grafo cargarGrafo() {

        // Se crea el grafo vacío
        Grafo grafo = new Grafo();

        // Se abre el archivo grafo.txt para lectura
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/resources/grafo.txt"))) {

            String linea;

            // Variables que indican qué parte del archivo se está leyendo
            boolean leyendoNodos = false;
            boolean leyendoConexiones = false;

            // Se lee el archivo línea por línea
            while ((linea = br.readLine()) != null) {

                // Se eliminan espacios innecesarios
                linea = linea.trim();

                // Se ignoran líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {

                    // Si se encuentra la sección de nodos
                    if (linea.contains("NODOS")) {
                        leyendoNodos = true;
                        leyendoConexiones = false;
                    }

                    // Si se encuentra la sección de conexiones
                    if (linea.contains("CONEXIONES")) {
                        leyendoNodos = false;
                        leyendoConexiones = true;
                    }
                    continue;
                }

                // Lectura de nodos: ID, X, Y
                if (leyendoNodos) {
                    String[] p = linea.split(",");
                    grafo.agregarNodo(
                        new Nodo(
                            p[0],
                            Integer.parseInt(p[1]),
                            Integer.parseInt(p[2])
                        )
                    );
                }

                // Lectura de conexiones: ID1-ID2
                if (leyendoConexiones) {
                    String[] p = linea.split("-");
                    grafo.conectar(p[0], p[1]);
                }
            }

        } catch (Exception e) {
            // En caso de error (archivo no encontrado, formato incorrecto, etc.)
            System.out.println("Error al cargar grafo: " + e.getMessage());
        }

        // Se devuelve el grafo ya cargado
        return grafo;
    }
}

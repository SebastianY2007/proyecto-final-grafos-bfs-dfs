package main.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import main.model.Grafo;
import main.util.GrafoLoader;

public class VentanaPrincipal extends JFrame {

    /**
     * Ventana principal del programa.
     * Se encarga de cargar el grafo y mostrar el mapa.
     */
    public VentanaPrincipal() {

        // Se carga el grafo desde el archivo grafo.txt
        Grafo grafo = GrafoLoader.cargarGrafo();

        // Se usa un BorderLayout para organizar los componentes
        setLayout(new BorderLayout());

        // Se añade el panel del mapa en el centro de la ventana
        add(new MapaPanel(grafo), BorderLayout.CENTER);

        // Configuración básica de la ventana
        setTitle("Mapa - Centro de Cuenca");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

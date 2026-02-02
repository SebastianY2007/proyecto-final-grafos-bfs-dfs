package main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import main.model.Grafo;
import main.model.Nodo;

public class MapaPanel extends JPanel {

    // Referencia al grafo que contiene los nodos a dibujar
    private Grafo grafo;

    // Imagen del mapa
    private Image mapa;

    // Dimensiones originales de la imagen del mapa
    private int imgW;
    private int imgH;

    /**
     * Constructor del panel del mapa.
     * Recibe el grafo para poder dibujar sus nodos.
     */
    public MapaPanel(Grafo grafo) {
        this.grafo = grafo;

        // Se carga la imagen del mapa desde recursos
        ImageIcon icono = new ImageIcon("src/resources/Mapa.png");
        mapa = icono.getImage();

        // Se guardan las dimensiones originales de la imagen
        imgW = icono.getIconWidth();
        imgH = icono.getIconHeight();
    }

    /**
     * Método que se encarga de dibujar el mapa y los nodos.
     * Se ejecuta automáticamente cada vez que el panel necesita repintarse.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Tamaño actual del panel
        int panelW = getWidth();
        int panelH = getHeight();

        // Calcula la escala para que el mapa entre completo
        // sin deformarse (mantiene proporción)
        double escala = Math.min(
                (double) panelW / imgW,
                (double) panelH / imgH
        );

        // Tamaño final del mapa ya escalado
        int mapaW = (int) (imgW * escala);
        int mapaH = (int) (imgH * escala);

        // Cálculo del desplazamiento para centrar el mapa
        int offsetX = (panelW - mapaW) / 2;
        int offsetY = (panelH - mapaH) / 2;

        // Dibuja la imagen del mapa centrada y escalada
        g.drawImage(mapa, offsetX, offsetY, mapaW, mapaH, this);

        // Dibuja los nodos del grafo como puntos rojos
        g.setColor(Color.RED);
        for (Nodo nodo : grafo.getNodos()) {

            // Se convierten las coordenadas del nodo
            // al sistema de la pantalla usando la escala
            int x = offsetX + (int) (nodo.getX() * escala);
            int y = offsetY + (int) (nodo.getY() * escala);

            // Se dibuja el nodo como un círculo
            g.fillOval(x - 5, y - 5, 10, 10);
        }
    }
}

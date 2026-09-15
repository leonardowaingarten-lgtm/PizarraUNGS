import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PizarraGeometrica extends JPanel {
    private static final long serialVersionUID = 1L;

    // Estructura que ahora almacena la figura, su color y su etiqueta opcional
    private class ElementoGrafico<T> {
        T figura;
        Color color;
        String etiqueta; // Puede ser null si se omite

        ElementoGrafico(T figura, Color color, String etiqueta) {
            this.figura = figura;
            this.color = color;
            this.etiqueta = etiqueta;
        }
    }

    private List<ElementoGrafico<Point>> puntos = new ArrayList<>();
    private List<ElementoGrafico<Linea>> lineas = new ArrayList<>();
    private List<ElementoGrafico<Rectangle>> rectangulos = new ArrayList<>();

    public PizarraGeometrica() {
        setBackground(Color.WHITE);
        JFrame ventana = new JFrame("Pizarra Geométrica UNGS");
        ventana.add(this);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static PizarraGeometrica crearPizarra() {
        return new PizarraGeometrica();
    }

    // --- SOBRECARGA PARA PUNTOS ---
    public void dibujar(Point p) {
        dibujar(p, Color.BLACK, null);
    }

    public void dibujar(Point p, Color c) {
        dibujar(p, c, null);
    }

    public void dibujar(Point p, String etiqueta) {
        dibujar(p, Color.BLACK, etiqueta);
    }

    public void dibujar(Point p, Color c, String etiqueta) {
        puntos.add(new ElementoGrafico<>(p, c, etiqueta));
        repaint();
    }

    // --- SOBRECARGA PARA LÍNEAS ---
    public void dibujar(Linea l) {
        dibujar(l, Color.BLACK, null);
    }

    public void dibujar(Linea l, Color c) {
        dibujar(l, c, null);
    }

    public void dibujar(Linea l, String etiqueta) {
        dibujar(l, Color.BLACK, etiqueta);
    }

    public void dibujar(Linea l, Color c, String etiqueta) {
        lineas.add(new ElementoGrafico<>(l, c, etiqueta));
        repaint();
    }

    // --- SOBRECARGA PARA RECTÁNGULOS ---
    public void dibujar(Rectangle r) {
        dibujar(r, Color.BLACK, null);
    }

    public void dibujar(Rectangle r, Color c) {
        dibujar(r, c, null);
    }

    public void dibujar(Rectangle r, String etiqueta) {
        dibujar(r, Color.BLACK, etiqueta);
    }

    public void dibujar(Rectangle r, Color c, String etiqueta) {
        rectangulos.add(new ElementoGrafico<>(r, c, etiqueta));
        repaint();
    }

    public void actualizar() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Dibujar rectángulos
        for (ElementoGrafico<Rectangle> e : rectangulos) {
            g.setColor(e.color);
            g.drawRect(e.figura.x, e.figura.y, e.figura.width, e.figura.height);
            if (e.etiqueta != null) {
                // Se ubica 10 píxeles arriba del inicio del rectángulo
                g.drawString(e.etiqueta, e.figura.x, e.figura.y - 10);
            }
        }

        // 2. Dibujar líneas
        for (ElementoGrafico<Linea> e : lineas) {
            g.setColor(e.color);
            g.drawLine(e.figura.inicio.x, e.figura.inicio.y, e.figura.fin.x, e.figura.fin.y);
            if (e.etiqueta != null) {
                // Se toma el punto de inicio como referencia para la etiqueta
                g.drawString(e.etiqueta, e.figura.inicio.x, e.figura.inicio.y - 10);
            }
        }

        // 3. Dibujar puntos
        for (ElementoGrafico<Point> e : puntos) {
            g.setColor(e.color);
            g.fillOval(e.figura.x - 3, e.figura.y - 3, 6, 6);
            if (e.etiqueta != null) {
                g.drawString(e.etiqueta, e.figura.x, e.figura.y - 10);
            }
        }
    }
}


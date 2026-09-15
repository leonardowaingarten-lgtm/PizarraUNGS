import java.awt.Point;

public class Linea {
	
	//Java no tiene lineas. La puse nombre en español para que quede claro que noesta en el default
    public Point inicio;
    public Point fin;

    // Constructor que recibe dos objetos Point de AWT
    public Linea(Point inicio, Point fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
}

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Scanner;


public class Principal2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // 1. Iniciar la pizarra
        PizarraGeometrica pizarra = PizarraGeometrica.crearPizarra();

        Rectangle a= new Rectangle (250,250, 80, 300);
        Rectangle b = new Rectangle (10,20, 50,65);
        Rectangle c = Funciones.interseccion(a, b);
        		
        Point p1= new Point (Math.max(a.x, b.x), Math.max(a.y,b.y));
        Point p2= new  Point (Math.min(a.x+a.width, b.x+b.width), Math.min(a.y+a.height,b.y+b.height));
        
        System.out.print(Funciones.estaContenido(a, b));
        
        
        pizarra.dibujar(a, Color.blue, "A");
        pizarra.dibujar(b, Color.blue, "B");
     // pizarra.dibujar(p1, Color.red);
     // pizarra.dibujar(p2, Color.red);
      //pizarra.dibujar(c, Color.green, "intersect");
      
      
        
        // Cerrar el scanner al terminar
        teclado.close();
    }
}

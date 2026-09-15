import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // 1. Iniciar la pizarra
        PizarraGeometrica pizarra = PizarraGeometrica.crearPizarra();

        // 2. Crear y enviar figuras iniciales
        Point p1 = new Point(100, 100);
        Point p2 = new Point(300, 100);
        Linea miLinea = new Linea(p1, p2);
        Rectangle miRectangulo = new Rectangle(100, 250, 200, 100);

        pizarra.dibujar(p1,Color.LIGHT_GRAY,"A");
        pizarra.dibujar(p2, Color.BLUE,"B");
        pizarra.dibujar(miLinea, Color.cyan,"linea");
        pizarra.dibujar(miRectangulo, Color.green,"miRectangulo");

        System.out.println("Presiona ENTER en la consola para mover el rectángulo...");
        teclado.nextLine(); // El programa se detiene aquí esperando el Enter

        // El alumno modifica los parámetros del rectángulo
        miRectangulo.x = 450;
        miRectangulo.width = 250;
        
        // Forzamos el redibujado manualmente
        pizarra.actualizar();
        System.out.println("¡Rectángulo movido!");

        // --- PAUSA 2 ---
        System.out.println("\nPresiona ENTER en la consola para modificar la línea...");
        teclado.nextLine(); 

        // El alumno modifica la línea moviendo sus puntos internos
        miLinea.inicio.y = 450;
        miLinea.fin.x = 600;
        
        // Volvemos a actualizar
        pizarra.actualizar();
        System.out.println("¡Línea modificada!");
        
        // Cerrar el scanner al terminar
        teclado.close();
    }
}

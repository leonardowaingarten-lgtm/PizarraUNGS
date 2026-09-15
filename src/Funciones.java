import java.awt.*;

public class Funciones {

	public static double distancia(Point p1, Point p2) {
		 
		 return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
	 }
	
	
	public static double diagonal(Rectangle r) {
		Point p1= new Point(r.x, r.y);
		Point p2= new Point(r.x+r.width, r.y+r.height);
		
		return distancia(p1,p2);
	}
	
	public static Point centro(Rectangle r) {
		
		return new Point(r.x+r.width/2, r.y+r.height/2);
		 
	 }
	
	
	public static boolean estaDentro(Point p, Rectangle r) {
		 
		return p.x >= r.x && p.x <= r.x+r.width &&   p.y >= r.y && p.y <= r.y+r.height;
	 }
	
	
	public static Point puntoMedio(Point p1, Point p2) {
		return new Point ((p1.x+p2.x)/2, (p1.y+p2.y)/2);
	}
	
	public static Rectangle encuadrar(Rectangle r1, Rectangle r2) {
		 int x= Math.min(r1.x, r2.x);
		 int y= Math.min(r1.y, r2.y);
		 int width= Math.max(r1.x+r1.width, r2.y+r2.width)-x;
		 int height= Math.max(r1.y+r1.height, r2.y+r2.height)-y;
		 
		 return new Rectangle(x,y,width,height);
		 
		 
	 }
	
	public static boolean estaContenido(Rectangle r1, Rectangle r2) {
		    Point p1= new Point (r1.x,r1.y);
	        Point p2= new Point (r1.x+ r1.width,r1.y+r1.height);
	        
	        return estaDentro(p1,r2) && estaDentro(p2,r2);
	        
		
		
	}
	
	
	
	
	public static Rectangle interseccion(Rectangle r1, Rectangle r2) {
		int x=Math.max(r1.x, r2.x);
		
		int y=Math.max(r1.y,r2.y);
		
		int width=Math.min(r1.x+r1.width, r2.x+r2.width)-x;
		int height=Math.min(r1.y+r1.height, r2.y+r2.height)-y;

		if (width> 0 && height >0  ) {
			
			return new Rectangle (x,y,width,height);
		}
		
		return null;
	}
	
		
		
	
	
	
}

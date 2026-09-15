# Pizarra Geométrica UNGS

`PizarraGeometrica` es un componente gráfico interactivo desarrollado en Java Swing para la visualización y renderizado dinámico de figuras primitivas (`Puntos`, `Líneas` y `Rectángulos`). 

La clase implementa un motor de dibujo optimizado mediante herencia de `JPanel`, gestionando estructuras de datos genéricas concurrentes, asignación personalizada de colores y etiquetado tipográfico por coordenadas.

---

## 🏗️ Ciclo de Inicialización

Para desplegar la pizarra dentro de una ventana independiente de 800x600 píxeles centrada en pantalla, se expone un método de fábrica estático:

```java
// Instancia y despliega la ventana gráfica automáticamente
PizarraGeometrica pizarra = PizarraGeometrica.crearPizarra();
```

---

## 🛠️ Especificación de Métodos de Dibujo

La API de la pizarra utiliza **sobrecarga de métodos (`Overloading`)**, permitiendo renderizar cualquier figura geométrica con tres niveles de parametrización independientes:

### 📍 1. Renderizado de Puntos (`java.awt.Point`)
Dibuja un círculo relleno (`fillOval`) de radio fijo (6x6 píxeles) centrado exactamente en la coordenada estipulada.

| Firma del Método | Descripción | Color por defecto |
| :--- | :--- | :--- |
| `dibujar(Point p)` | Grafica el punto en la coordenada fija. | `Color.BLACK` |
| `dibujar(Point p, Color c)` | Grafica el punto con color personalizado. | Configurado por el usuario |
| `dibujar(Point p, String etiqueta)` | Grafica el punto y añade texto descriptivo superior. | `Color.BLACK` |
| `dibujar(Point p, Color c, String etiqueta)` | Firma completa con control de color y texto. | Configurado por el usuario |

### ➖ 2. Renderizado de Líneas (`Linea`)
Grafica un segmento de recta uniendo dos objetos `Point` definidos internamente en la estructura de tu clase personalizada `Linea`.

| Firma del Método | Descripción | Color por defecto |
| :--- | :--- | :--- |
| `dibujar(Linea l)` | Grafica el segmento de inicio a fin. | `Color.BLACK` |
| `dibujar(Linea l, Color c)` | Grafica el segmento con color personalizado. | Configurado por el usuario |
| `dibujar(Linea l, String etiqueta)` | Grafica la recta con etiqueta sobre el punto de origen. | `Color.BLACK` |
| `dibujar(Linea l, Color c, String etiqueta)` | Control total sobre el renderizado de la recta. | Configurado por el usuario |

### 🔲 3. Renderizado de Rectángulos (`java.awt.Rectangle`)
Renderiza el contorno perimetral (`drawRect`) de un rectángulo a partir de su esquina superior izquierda `(x, y)`, su ancho y su alto.

| Firma del Método | Descripción | Color por defecto |
| :--- | :--- | :--- |
| `dibujar(Rectangle r)` | Grafica el contorno del cuadrilátero. | `Color.BLACK` |
| `dibujar(Rectangle r, Color c)` | Grafica el cuadrilátero con color personalizado. | Configurado por el usuario |
| `dibujar(Rectangle r, String etiqueta)` | Añade una etiqueta de texto 10 píxeles sobre el marco. | `Color.BLACK` |
| `dibujar(Rectangle r, Color c, String etiqueta)` | Control total de propiedades del rectángulo. | Configurado por el usuario |

---

## 🔄 Actualización y Renderizado Interno

* **`actualizar()`**: Provoca una llamada explícita al pipeline de dibujo asíncrono de AWT (`repaint()`) para forzar la actualización inmediata de los gráficos en pantalla reflejando los cambios de los objetos en memoria.
* **`paintComponent(Graphics g)`**: Método del ciclo de vida interno de Swing. Realiza un barrido iterativo sobre las listas dinámicas de objetos y computa secuencialmente el dibujado respetando las opacidades y profundidades por tipo de figura:
  1. Primero renderiza los **Rectángulos**.
  2. Superpone las **Líneas**.
  3. Dibuja los **Puntos** en la capa superior para asegurar su visibilidad.

---

## 🚀 Guía de Uso: Clase Principal (Ejemplo Interactivo)

A continuación se detalla un escenario práctico de ejecución utilizando la consola de Java (`Scanner`) combinada con la interfaz gráfica para mutar propiedades en tiempo real:

```java
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // 1. Iniciar la pizarra gráfica
        PizarraGeometrica pizarra = PizarraGeometrica.crearPizarra();

        // 2. Instanciar figuras geométricas iniciales
        Point p1 = new Point(100, 100);
        Point p2 = new Point(300, 100);
        Linea miLinea = new Linea(p1, p2);
        Rectangle miRectangulo = new Rectangle(100, 250, 200, 100);

        // 3. Enviar figuras a la cola de dibujo de la pizarra con propiedades estéticas
        pizarra.dibujar(p1, Color.LIGHT_GRAY, "A");
        pizarra.dibujar(p2, Color.BLUE, "B");
        pizarra.dibujar(miLinea, Color.CYAN, "linea");
        pizarra.dibujar(miRectangulo, Color.GREEN, "miRectangulo");

        // --- INTERACCIÓN 1: Modificación de Rectángulos ---
        System.out.println("Presiona ENTER en la consola para mover el rectángulo...");
        teclado.nextLine(); // Pausa de ejecución por consola

        // Mutación de parámetros por referencia de memoria externa
        miRectangulo.x = 450;
        miRectangulo.width = 250;
        
        // Sincronización asíncrona: Forzamos el redibujado de la escena
        pizarra.actualizar();
        System.out.println("¡Rectángulo movido!");

        // --- INTERACCIÓN 2: Modificación de Líneas mediante Puntos Internos ---
        System.out.println("\nPresiona ENTER en la consola para modificar la línea...");
        teclado.nextLine(); 

        // Mutación de las coordenadas internas del objeto compuesto 'Linea'
        miLinea.inicio.y = 450;
        miLinea.fin.x = 600;
        
        // Forzamos el redibujado final
        pizarra.actualizar();
        System.out.println("¡Línea modificada!");
        
        teclado.close();
    }
}
```

### 💡 Mecánica clave explicada a alumnos:
Dado que la estructura `ElementoGrafico` interna guarda **la referencia exacta del objeto** (`Point`, `Linea` o `Rectangle`), cualquier cambio que realice el alumno sobre variables como `miRectangulo.x` afectará directamente los datos almacenados en la lista de la pizarra. Sin embargo, los cambios gráficos no se verán reflejados en la ventana hasta que no se invoque de manera explícita la función **`pizarra.actualizar()`**.

---

## 📦 Arquitectura de Almacenamiento Estático

El almacenamiento de los datos gráficos se procesa mediante una clase anidada privada encapsulada que unifica el modelado matemático de los objetos de Java junto con sus atributos estéticos de representación:

```java
private class ElementoGrafico<T> {
    T figura;          // Acepta Point, Linea o Rectangle
    Color color;       // Instancia java.awt.Color
    String etiqueta;   // Cadena de texto (admite valores null)
}
```

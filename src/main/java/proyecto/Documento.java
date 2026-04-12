package proyecto;

import com.murcia.utils.*; //comentario para el commit de prueba
import java.io.*;

public class Documento{ //Comentario de prueba en casa

    private ConcreteListaEnlazada<String> lineasDeTexto;

    Documento() { //Constructor por defecto
        this.lineasDeTexto = new ConcreteListaEnlazada<String>(); //Por que es una clase tipo abstract?
    }

    public void insertarLinea(int posicion, String texto) {
        int sizeActual = lineasDeTexto.size();
        
        if (posicion <= 0) {
            // Posición inválida, ignorar
            return;
        } else if (posicion <= sizeActual) {
            // Si la línea ya existe, reemplazar el contenido
            lineasDeTexto.set(posicion - 1, texto);
        } else if (posicion == sizeActual + 1) {
            // Si es la siguiente línea, agregar al final
            lineasDeTexto.addLast(texto);
        } else {
            // Si está más allá, llenar con vacíos y luego insertar
            while (lineasDeTexto.size() < posicion - 1) {
                lineasDeTexto.addLast("");
            }
            lineasDeTexto.addLast(texto);
        }
    }

    public String borrarLinea(int posicion) {
        if (lineasDeTexto.size() == 0) return null;
        
        if (posicion < 1) {
            return lineasDeTexto.remove(0);
        } else if (posicion >= lineasDeTexto.size()) {
            return lineasDeTexto.remove(lineasDeTexto.size() - 1);
        } else {
            return lineasDeTexto.remove(posicion - 1);
        }
    }


    public void mostrarDocumento() {
        System.out.println("\n--- Contenido del Documento ---");
        if (lineasDeTexto.size() == 0) {
            System.out.println("(Vacío)");
        } else {
            for (int i = 0; i < lineasDeTexto.size(); i++) {
                System.out.println((i + 1) + ": " + lineasDeTexto.get(i));
            }
        }
        System.out.println("-------------------------------\n");
    }

    public void guardarEnArchivo(String nombreArchivo) {
        // Agregar extensión .txt si no la tiene
        if (!nombreArchivo.endsWith(".txt")) {
            nombreArchivo = nombreArchivo + ".txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < lineasDeTexto.size(); i++) {
                writer.write(lineasDeTexto.get(i));
                writer.newLine();
            }
            System.out.println("✓ Documento guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("✗ Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        // Agregar extensión .txt si no la tiene
        if (!nombreArchivo.endsWith(".txt")) {
            nombreArchivo = nombreArchivo + ".txt";
        }
        lineasDeTexto.clear(); // Limpiar contenido actual
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasDeTexto.addLast(linea);
            }
            System.out.println("✓ Documento cargado desde: " + nombreArchivo);
        } catch (FileNotFoundException e) {
            System.out.println("✗ Archivo no encontrado: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("✗ Error al cargar el archivo: " + e.getMessage());
        }
    }

    public int obtenerTotalLineas() {
        return lineasDeTexto.size();
    }

    public void limpiar() {
        lineasDeTexto.clear();
    }
}

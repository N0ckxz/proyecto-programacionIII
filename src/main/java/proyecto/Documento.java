package proyecto;

import com.murcia.utils.*; //comentario para el commit de prueba

public class Documento{ //Comentario de prueba en casa

    private ConcreteListaEnlazada<String> lineasDeTexto;

    Documento() { //Constructor por defecto
        this.lineasDeTexto = new ConcreteListaEnlazada<String>(); //Por que es una clase tipo abstract?
    }

    public void insertarLinea(int posicion, String texto) {
        if (posicion < 1) { //La posicion es 1 para la entrada del usuario
            lineasDeTexto.add(0, texto); //insertar al inicio
        } else if (posicion > lineasDeTexto.size()) {
            lineasDeTexto.add(texto); //insertar al final
        } else {
            lineasDeTexto.add(posicion - 1, texto); //insertar en el índice 0 específicado
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
}

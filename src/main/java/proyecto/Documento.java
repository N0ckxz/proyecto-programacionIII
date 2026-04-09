package proyecto;

import com.murcia.utils.*;

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

    public void borrarLinea(int posicion) {
    }


    public void mostrarDocumento() {
    }
}

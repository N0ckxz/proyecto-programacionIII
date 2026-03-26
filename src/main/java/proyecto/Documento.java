package proyecto;

import com.murcia.utils.*;

public class Documento{ //Comentario de prueba en casa

    private listaEnlazada<String> lineasDeTexto;

    Documento() { //Constructor por defecto
        this.lineasDeTexto = new listaEnlazada<String>(); //Por que es una clase tipo abstract?
    }

    public void insertarLinea(int posicion, String texto) {
        
    }

    public void borrarLinea(int posicion) {
    }


    public void mostrarDocumento() {
    }
}

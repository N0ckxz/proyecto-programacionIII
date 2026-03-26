package proyecto;

public class EditorTexto {
    private Documento documentoActual; //No hay clase pila en las librerías del profesor
    private PilaEnlazada<Accion> pilaDeshacer;
    private PilaEnlazada<Accion> pilaRehacer;

    public EditorTexto() { //Constructor por defecto
        this.documentoActual = new Documento();
        this.pilaDeshacer = new PilaEnlazada<Accion>();
        this.pilaRehacer = new PilaEnlazada<Accion>();
    }

    public void escribir(int linea, String texto) { 

    }

    public void borrar(int linea) {
        
    }

    public void deshacer() {
    }

    public void rehacer() {
    
    }

}
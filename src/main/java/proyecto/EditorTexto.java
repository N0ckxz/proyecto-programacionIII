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
        documentoActual.insertarLinea(linea, texto); //Insertar el texto en el documento en la linea especificada

        pilaDeshacer.apilar(new Accion(Accion.TipoAccion.INSERTAR, linea, texto)); //Registramos la accion para poder deshacer
        //Limpiar la pila de rehacer, ya que hay una nueva accion

        pilaRehacer = new PilaEnlazada<Accion>(); //Reiniciamos para limpiar la pila
    }

    public void borrar(int linea) {
        
    }

    public void deshacer() {
    }

    public void rehacer() {
    
    }

}
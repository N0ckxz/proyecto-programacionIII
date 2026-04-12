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
        String textoEliminado = documentoActual.borrarLinea(linea);
        if (textoEliminado != null) {
            pilaDeshacer.apilar(new Accion(Accion.TipoAccion.BORRAR, linea, textoEliminado));
            pilaRehacer = new PilaEnlazada<Accion>(); // Nueva acción invalida el rehacer
        }
    }

    public void deshacer() {
        if (pilaDeshacer.size() > 0) {
            Accion ultima = pilaDeshacer.desapilar();
            
            if (ultima.getTipo() == Accion.TipoAccion.INSERTAR) {
                // Lo inverso a insertar es borrar
                documentoActual.borrarLinea(ultima.getNumeroLinea());
            } else {
                // Lo inverso a borrar es insertar
                documentoActual.insertarLinea(ultima.getNumeroLinea(), ultima.getTexto());
            }
            
            pilaRehacer.apilar(ultima);
        }
    }

    public void rehacer() {
        if (pilaRehacer.size() > 0) {
            Accion aRehacer = pilaRehacer.desapilar();
            
            if (aRehacer.getTipo() == Accion.TipoAccion.INSERTAR) {
                documentoActual.insertarLinea(aRehacer.getNumeroLinea(), aRehacer.getTexto());
            } else {
                documentoActual.borrarLinea(aRehacer.getNumeroLinea());
            }
            
            pilaDeshacer.apilar(aRehacer);
        }
    }

    public void mostrarEstadoActual() {
        documentoActual.mostrarDocumento();
    }
}
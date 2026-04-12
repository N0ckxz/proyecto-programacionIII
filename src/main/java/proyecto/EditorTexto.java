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
            // Al borrar, guardamos la acción para poder revertirla (re-insertar el texto)
            pilaDeshacer.apilar(new Accion(Accion.TipoAccion.BORRAR, linea, textoEliminado));
            // Una nueva acción siempre limpia la pila de rehacer
            pilaRehacer = new PilaEnlazada<Accion>();
        }
    }

    public void deshacer() {
        if (pilaDeshacer.size() > 0) {
            Accion ultima = pilaDeshacer.desapilar();
            if (ultima.getTipo() == Accion.TipoAccion.INSERTAR) {
                // Si insertamos, el deshacer lo borra
                documentoActual.borrarLinea(ultima.getNumeroLinea());
            } else {
                // Si borramos, el deshacer lo re-inserta
                documentoActual.insertarLinea(ultima.getNumeroLinea(), ultima.getTexto());
            }
            // Guardamos en rehacer por si el usuario cambia de opinión
            pilaRehacer.apilar(ultima);
        }
    }

    public void rehacer() {
        if (pilaRehacer.size() > 0) {
            Accion aRehacer = pilaRehacer.desapilar();
            if (aRehacer.getTipo() == Accion.TipoAccion.INSERTAR) {
                // Repetir la inserción
                documentoActual.insertarLinea(aRehacer.getNumeroLinea(), aRehacer.getTexto());
            } else {
                // Repetir el borrado
                documentoActual.borrarLinea(aRehacer.getNumeroLinea());
            }
            // Volvemos a ponerlo en la pila de deshacer
            pilaDeshacer.apilar(aRehacer);
        }
    }
}
package proyecto;

import com.murcia.utils.*;

public class EditorTexto {
    private Documento documentoActual;
    private PilaEnlazada<Accion> pilaDeshacer;
    private PilaEnlazada<Accion> pilaRehacer;
    private ColaEnlazada<String> versionesGuardadas; // Historial de archivos guardados

    public EditorTexto() { //Constructor por defecto
        this.documentoActual = new Documento();
        this.pilaDeshacer = new PilaEnlazada<Accion>();
        this.pilaRehacer = new PilaEnlazada<Accion>();
        this.versionesGuardadas = new ColaEnlazada<String>();
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

    public void guardarArchivo(String nombreArchivo) {
        documentoActual.guardarEnArchivo(nombreArchivo);
        
        // Agregar a la cola de versiones (máximo 5)
        versionesGuardadas.encolar(nombreArchivo);
        if (versionesGuardadas.size() > 5) {
            versionesGuardadas.desencolar(); // Eliminar versión antigua
        }
    }

    public void cargarArchivo(String nombreArchivo) {
        documentoActual.cargarDesdeArchivo(nombreArchivo);
        pilaDeshacer.clear(); // Limpiar historial de deshacer
        pilaRehacer.clear();  // Limpiar historial de rehacer
    }

    public void mostrarVersionesGuardadas() {
        System.out.println("\n--- Versiones Guardadas Recientemente ---");
        if (versionesGuardadas.size() == 0) {
            System.out.println("(Sin archivos guardados aún)");
        } else {
            ColaEnlazada<String> temp = new ColaEnlazada<String>();
            int contador = 1;
            while (versionesGuardadas.size() > 0) {
                String archivo = versionesGuardadas.desencolar();
                System.out.println(contador + ". " + archivo);
                temp.encolar(archivo);
                contador++;
            }
            // Restaurar la cola original
            while (temp.size() > 0) {
                versionesGuardadas.encolar(temp.desencolar());
            }
        }
        System.out.println("---------------------------------------\n");
    }

    public void limpiarDocumento() {
        documentoActual.limpiar();
        pilaDeshacer.clear();
        pilaRehacer.clear();
        System.out.println(">> Documento limpiado.");
    }

    public void mostrarEstadisticas() {
        System.out.println("\n--- Estadísticas del Documento ---");
        System.out.println("Total de líneas: " + documentoActual.obtenerTotalLineas());
        System.out.println("Acciones deshacibles: " + pilaDeshacer.size());
        System.out.println("Acciones rehacibles: " + pilaRehacer.size());
        System.out.println("----------------------------------\n");
    }
}
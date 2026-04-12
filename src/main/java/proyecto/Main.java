package proyecto;

import com.murcia.utils.*;

class ConcreteListaEnlazada<T> extends ListaEnlazada<T> { 
    
}

public class Main { 
    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();
        
        String[] opciones = {
            "1. Escribir línea",
            "2. Borrar línea",
            "3. Deshacer",
            "4. Rehacer",
            "5. Ver documento",
            "6. Guardar archivo",
            "7. Cargar archivo",
            "8. Estadísticas",
            "9. Salir"
        };

        // Creamos el menú: 'V' para disposición vertical
        Menu menuPrincipal = new Menu(opciones, 'V', "\n", "\n--- SIMULADOR DE EDITOR DE TEXTO ---");
        char opcion;

        do {
            opcion = menuPrincipal.select("Seleccione una opción: ");

            switch (opcion) {
                case '1':
                    int numL = Input.nextInt("\nNúmero de línea: ");
                    Input.nextLine(""); 
                    String texto = Input.nextLine("Contenido de la línea: ");
                    editor.escribir(numL, texto);
                    editor.mostrarEstadoActual();
                    break;
                case '2':
                    int numB = Input.nextInt("Línea a borrar: ");
                    editor.borrar(numB);
                    editor.mostrarEstadoActual();
                    break;
                case '3':
                    editor.deshacer();
                    System.out.println(">> Acción deshecha.");
                    editor.mostrarEstadoActual();
                    break;
                case '4':
                    editor.rehacer();
                    System.out.println(">> Acción rehecha.");
                    editor.mostrarEstadoActual();
                    break;
                case '5':
                    editor.mostrarEstadoActual();
                    break;
                case '6':
                    Input.nextLine("");
                    String nombreGuardar = Input.nextLine("Nombre del archivo a guardar: ");
                    editor.guardarArchivo(nombreGuardar);
                    break;
                case '7':
                    Input.nextLine("");
                    String nombreCargar = Input.nextLine("Nombre del archivo a cargar: ");
                    editor.cargarArchivo(nombreCargar);
                    editor.mostrarEstadoActual();
                    break;
                case '8':
                    editor.mostrarEstadisticas();
                    break;
            }
        } while (opcion != '9');
        
        System.out.println("Editor cerrado.");
    }
}

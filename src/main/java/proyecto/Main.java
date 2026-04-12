package proyecto;

import com.murcia.utils.ListaEnlazada;
import com.murcia.utils.Input;
import com.murcia.utils.Menu;

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
            "6. Salir"
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
                    break;
                case '2':
                    int numB = Input.nextInt("Línea a borrar: ");
                    editor.borrar(numB);
                    break;
                case '3':
                    editor.deshacer();
                    System.out.println(">> Acción deshecha.");
                    break;
                case '4':
                    editor.rehacer();
                    System.out.println(">> Acción rehecha.");
                    break;
                case '5':
                    editor.mostrarEstadoActual();
                    break;
            }
        } while (opcion != '6');
        
        System.out.println("Editor cerrado.");
    }
}

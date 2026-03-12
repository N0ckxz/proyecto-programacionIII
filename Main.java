public class Main { 
    public static void main(String[] args) {
        ColaEnlazada<String> cola = new ColaEnlazada<String>();
        System.out.println(cola);
        cola.encolar("¡Saludos!");
        System.err.println(cola);
    }
}

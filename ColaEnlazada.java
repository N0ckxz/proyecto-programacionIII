public class ColaEnlazada<T> extends ListaEnlazada<T> {
    public void encolar(T dato) {
        agregar(dato);
    }

    @Override
    public String toString() {
        if (this.cabeza == null) {
            return "Cola vacía";
        }

        String resultado = "";
        Nodo actual = this.cabeza;

        while (actual != null) {
            resultado += actual.dato;
            actual = actual.siguiente;
        }

        return resultado;
    }
}

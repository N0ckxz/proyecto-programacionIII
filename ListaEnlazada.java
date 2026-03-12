public abstract class ListaEnlazada<T> { //Comentario
    Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;

            return;
        }

        Nodo actual = this.cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevoNodo;
    }
}

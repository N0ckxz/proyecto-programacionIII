package proyecto;

import com.murcia.utils.*; 

public class PilaEnlazada<T> extends ListaEnlazada<T> { 
    public PilaEnlazada() {
    }

    @Override
    public PilaEnlazada<T> clone() {
        PilaEnlazada<T> copia = new PilaEnlazada<T>();

        for(Nodo<T> curr = this.head; curr != null; curr = curr.getNext()) {
            copia.add(curr.getData());
        }

        return copia;
    }

    public void apilar(T e) { //Ya que me da pereza llamar al metodo de ColaEnlazada
        this.add(e);
    }

    public T desapilar() {
        return this.removeLast(); 
    }
}

package proyecto;

import com.murcia.utils.*; //Como funciona import vs package?

public class PilaEnlazada<T> extends ListaEnlazada<T> { 
    public PilaEnlazada() { //Constructor por defecto
    }

    public Object clone() {
        ListaEnlazada<T> copia = new ColaEnlazada<T>();

        for(Nodo<T> curr = this.head; curr != null; curr = curr.getNext()) {
         copia.add(curr.getData());
        }

      return copia;
    }

    public void apilar(T e) { //Ya que me da pereza llamar al metodo de ColaEnlazada
        this.add(e);
    }

    public void desapilar() {
        this.removeLast(); //Este metodo no está muy optimizado, peeeeero ya esta hecho
    }
}

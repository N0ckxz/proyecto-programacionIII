package proyecto;

public class Accion { //Clase accion
    enum TipoAccion { //Enum para limitar acciones posibles
        INSERTAR,
        BORRAR
    }

    private TipoAccion tipo;
    private int numeroLinea;
    private String texto;

    public Accion(TipoAccion tipo, int numeroLinea, String texto) { //Constructor 
        this.tipo = tipo;
        this.numeroLinea = numeroLinea;
        this.texto = texto;
    }

    public TipoAccion getTipo() {return tipo; } //getters
    public int getNumeroLinea() {return numeroLinea; }
    public String getTexto() {return texto; }
}

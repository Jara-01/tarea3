package Tarea1;

/** Uno de los 2 tipos de dulces, extiende a Dulce. */
class Snickers extends Dulce{
    /**
     * Constructor que instancia un número de serie según la superclase Producto.
     *
     * @param serieIn Número de serie
     */
    public Snickers(int serieIn) {
        super(serieIn);
    }
    /**
     * Método que entrega un string con el nombre del producto
     *
     * @return Nombre del producto
     */
    public String consumir(){
        return "snickers";
    }
}

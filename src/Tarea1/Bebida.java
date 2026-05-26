package Tarea1;

/** Clase que hereda de Producto y hace de superclase para los 3 sabores de bebida. */
abstract class Bebida extends Producto{
    /**
     * Constructor que instancia un número de serie según la superclase Producto.
     *
     * @param serieIn Número de serie
    */
    public Bebida(int serieIn) {super(serieIn);}
}

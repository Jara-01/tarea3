package Tarea1;

/** Clase que hereda de Producto y hace de superclase para los 2 tipos de dulce. */
abstract class Dulce extends Producto {
    /**
     * Constructor que instancia un número de serie según la superclase Producto.
     *
     * @param serieIn Número de serie
     */
    public Dulce(int serieIn){super(serieIn);}
}

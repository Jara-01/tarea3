package Tarea1;

/** Uno de los 3 sabores de bebida, extiende a Bebida. */
class Sprite extends Bebida{
    /**
     * Constructor que instancia un número de serie según la superclase Producto.
     *
     * @param serieIn Número de serie
     */
    public Sprite(int serieIn) {
        super(serieIn);
    }
    /**
     * Método que entrega un string con el nombre del producto
     *
     * @return Nombre del producto
     */
    public String consumir(){
        return "sprite";
    }
}

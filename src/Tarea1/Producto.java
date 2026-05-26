package Tarea1;

/** Clase para hacer de superclase a todos los productos que expendedor va a vender */
abstract class Producto {
    /** Número de serie arbitrario para identificar a cada elemento */
    private int serie;

    /**
     * Constructor para asignar el número de serie
     *
     * @param serieIn Número de serie
    */
    public Producto(int serieIn) {
        this.serie = serieIn;
    }
    /** Método abstracto para que cada subclase deba retornar un string */
    public abstract String consumir();

    public int getSerie() {return this.serie;}
}


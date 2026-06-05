package Logica;

/** Clase para hacer de superclase a todos los productos que expendedor va a vender */
public abstract class Producto {
    /** Número de serie arbitrario para identificar a cada elemento */
    private int serie;

    /** El constructor asigna el número de serie según la posición en memoria */
    public Producto(){this.serie = this.hashCode();}

    /** Método abstracto para que cada subclase deba retornar un string */
    public abstract String consumir();

    /**
     * Metodo que retorna la el número de serie.
     * @return número de serie de la moneda.
     */
    public int getSerie() {return this.serie;}
}


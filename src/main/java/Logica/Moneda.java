package Logica;
/**
 * Clase abstracta para las monedas.
 * Permite obtener su valor, la serie y compararla con otras monedas.
 */
public abstract class Moneda implements Comparable<Moneda>{
    private int serie;

    /** El constructor asigna el número de serie según la posición en memoria */
    public Moneda(){this.serie = this.hashCode();}

    /**
     * Metodo que retorna la el número de serie.
     * @return número de serie de la moneda.
     */
    public int getSerie(){
        return serie;
    }

    /**
     * Metodo abstracto que se implementa en las monedas para
     * obtener su valor.
     * @return valor de la moneda.
     */
    public abstract int getValor();

    /**
     * Metodo que retorna los datos de la moneda como string.
     * @return string con valor y serie de la moneda.
     */
    @Override
    public String toString() {
        return "valor:"+this.getValor()+"; serie:"+this.getSerie();
    }

    /**
     * Compara esta moneda con otra según su valor.
     * @param o moneda a comparar.
     * @return valor (<0 esta es menor / 0 iguales / >0 esta es mayor)
     */
    @Override
    public int compareTo(Moneda o) {
        return this.getValor()-o.getValor();
    }
}
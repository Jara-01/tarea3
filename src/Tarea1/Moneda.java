package Tarea1;
/**
 * Clase abstracta para las monedas.
 * Permite obtener su valor, la serie y compararla con otras monedas.
 */
abstract class Moneda implements Comparable<Moneda>{
    public Moneda(){}

    /**
     * Metodo que retorna la direccion de memoria como serie.
     * @return número de serie de la moneda.
     */
    public int getSerie(){
        return this.hashCode();
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
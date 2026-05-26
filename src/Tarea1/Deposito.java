package Tarea1;
import java.util.ArrayList;

/**
 * Clase que almacena cierto tipo de objeto.
 * Es inicializado vacío y se puede llenar con elementos del tipo correcto o retirar en un sistema FIFO.
*/
class Deposito<T> {
    /** almacen es dónde se guardan y retiran los elementos del tipo determinado al ser instanciado */
    private ArrayList<T> almacen;

    /** Constructor que instancia un ArrayList para el almacén de objetos */
    public Deposito(){
        almacen = new ArrayList<T>();
    }
    /** Añade un elemento al final del almacén */
    public void addElemento(T toAdd){
        almacen.add(toAdd);
    }
    /** Retorna el primer elemento del almacén */
    public T getElemento(){
        if (almacen.size() != 0) return almacen.remove(0);
        else return null;
    }
}

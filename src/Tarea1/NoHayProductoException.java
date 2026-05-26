package Tarea1;

/** Exepción personalizada para los casos en que no queden productos en un depósito o se acceda a un depósito imposible. */
public class NoHayProductoException extends Exception {
    public NoHayProductoException(String message) {
        super(message);
    }
}

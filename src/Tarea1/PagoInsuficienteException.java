package Tarea1;

/** Exepción personalizada en cazo de que se intente comprar un producto con menos dinero que su precio. */
public class PagoInsuficienteException extends Exception {
    public PagoInsuficienteException(String message) {
        super(message);
    }
}

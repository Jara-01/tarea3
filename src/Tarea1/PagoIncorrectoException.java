package Tarea1;

/** Exepción personalizada por si se intenta comprar con una moneda inválida (null.) */
public class PagoIncorrectoException extends Exception {
    public PagoIncorrectoException(String message) {
        super(message);
    }
}

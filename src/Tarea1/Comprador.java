package Tarea1;

/**
 * La clase del cliente y usuario de Expendedor.
 * Al ser instanciado, instantáneamente compra un producto con una moneda.
 * Almacena el consumo del producto que compra y el vuelto que recibe moneda por moneda.
 */
class Comprador {
    /** sonido almacena el string de "consumir" de cada producto. */
    private String sonido;
    /** vuelto almaacena el valor total de todas las monedas recojidas del expendedor. */
    private int vuelto;

    /**
     * Método constructor que recibe el dónde, qué y cómo de la compra del consumidor, y maneja el recibo de vuelto
     *
     * @param m Moneda con la que se compra el producto
     * @param cualProducto El número identificador del producto a comprar
     * @param exp La instancia del expendedor al que le va a comprar
    */
    public Comprador(Moneda m, int cualProducto, Expendedor exp)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        /**
         * Al crearse, el comprador intenta comprar inmediatamente
         * el producto indicado en el expendedor usando la moneda recibida.
         * Si logra obtener un producto, guarda en sonido el texto
         * que entrega el metodo consumir de ese producto.
         */
        Producto b = exp.comprarProducto(m, cualProducto);
        if (b != null) {
            this.sonido = b.consumir();
        }

        /**
         * Despues de la compra, el comprador revisa el deposito de vuelto
         * del expendedor y saca las monedas una por una hasta que no queden mas.
         * Cada moneda retirada se suma al total almacenado en vuelto.
         */
        Moneda efectivo = exp.getVuelto();
        while (efectivo != null) {
            this.vuelto += efectivo.getValor();
            efectivo = exp.getVuelto();
        }
    }
    /** Getter de vuelto */
    public int cuantoVuelto(){
        return this.vuelto;
    }
    /** Getter del producto comprado*/
    public String queConsumiste(){
        return this.sonido;
    }
}

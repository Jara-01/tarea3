package Logica;

/**
 * La clase Expendedor maneja los procesos principales del programa.
 * Es el encargado de recibir el dinero de un comprador, y responder apropiadamente según el producto que se pida.
*/
public class Expendedor{
    /** Depósitos tipo Producto para cada producto disponible diferente */
    private Deposito<Producto> coca;
    private Deposito<Producto> sprite;
    private Deposito<Producto> fanta;
    private Deposito<Producto> snickers;
    private Deposito<Producto> super8;

    /** Depósitos para almacenar las monedas del vuelto, el producto otorgado y las monedas recibidas */
    private Deposito<Moneda> monVu;
    private Deposito<Moneda> ganancias;
    private Producto prodVu;

    /**
     * Método constructor que inicializa todos los depósitos
     * Los depósitos de productos son llenados con una cantidad dada de elementos por igual,
     * El depósito de monedas de vuelto comienza vacío.
     *
     * @param size El tamaño de cada depósito de productos.
     */
    public Expendedor(int size){
        coca = new Deposito<Producto>();
        sprite = new Deposito<Producto>();
        fanta = new Deposito<Producto>();
        snickers = new Deposito<Producto>();
        super8 = new Deposito<Producto>();

        for(int i = 0;i < size;i++){
            coca.addElemento(new CocaCola(i+100));
            sprite.addElemento(new Sprite(i+200));
            fanta.addElemento(new Fanta(i+300));
            snickers.addElemento(new Snickers(i));
            super8.addElemento(new Super8(i+50));
        }
        monVu = new Deposito<Moneda>();
        ganancias = new Deposito<Moneda>();
    }

    /**
     * Método principal, recibe una moneda y un tipo de producto y procesa si la moneda es suficiente para comprar el
     * producto elegido, almacena el producto si corresponde y entrega monedas al depósito del vuelto si el pago es
     * mayor al precio del producto.
     *
     * @param dinero Moneda que el usuario usa para comprar un producto.
     * @param type Identificador numérico del tipo de producto elegido por el usuario.
    */
    public void comprarProducto(Moneda dinero, int type)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        /**
         * Primero se revisa el caso en que no haya moneda.
         * Si el comprador intenta comprar con null, no se entrega producto y tampoco se deja vuelto en el deposito.
         */
        if (dinero == null) {
            throw new PagoIncorrectoException("No se ingresó ninguna moneda");
        }

        /**
         * Si hay moneda, se determina cual es el deposito correcto
         * también el producto del enum que corresponde al numero elegido.
         * Si el numero no corresponde a ningun producto, se devuelve la
         * misma moneda como vuelto y se lanza la excepcion.
         */
        Deposito<Producto> dep;

        Precios productoActual = Precios.producto(type);

        if(productoActual == null){
            monVu.addElemento(dinero);
            throw new NoHayProductoException("No existe el producto indicado");
        }

        switch (productoActual){
            case COCACOLA:
                dep = coca;
                break;
            case SPRITE:
                dep = sprite;
                break;
            case FANTA:
                dep = fanta;
                break;
            case SNICKERS:
                dep = snickers;
                break;
            case SUPER8:
                dep = super8;
                break;
            default:
                throw new NoHayProductoException("No existe el producto indicado");
        }

        /**
         * Una vez identificado el producto, se revisa si la moneda alcanza.
         * Si el valor de la moneda es menor al precio del producto,
         * no se entrega nada y la misma moneda queda como vuelto.
         */
        if (dinero.getValor() < productoActual.getPrecio()) {
            monVu.addElemento(dinero);
            throw new PagoInsuficienteException("Pago insuficiente");
        }

        /**
         * Si el pago es suficiente, se intenta sacar un producto desde el deposito.
         * Si el deposito ya estaba vacio, tampoco se entrega producto
         * y la misma moneda queda guardada en el deposito de vuelto.
         */
        Producto auxOut = dep.getElemento();

        if (auxOut == null) {
            monVu.addElemento(dinero);
            throw new NoHayProductoException("No quedan productos de ese tipo");
        }

        /** Si la compra es completamente exitosa, la moneda recibida se añade al depósito de ganancias */
        this.ganancias.addElemento(dinero);

        /**
         * Si todo sale bien, entonces se calcula la diferencia entre lo pagado
         * y el precio del producto. Ese vuelto se deja en monedas en el mayor valor
         * de moneda posible para completar el vuelto requerido.
         */
        int cantidadVuelto = (dinero.getValor() - productoActual.getPrecio());
        while(cantidadVuelto >= 1500) {
            monVu.addElemento(new Moneda1500());
            cantidadVuelto -= 1500;
        }
        while(cantidadVuelto >= 1000) {
            monVu.addElemento(new Moneda1000());
            cantidadVuelto -= 1000;
        }
        while(cantidadVuelto >= 500) {
            monVu.addElemento(new Moneda500());
            cantidadVuelto -= 500;
        }
        while(cantidadVuelto >= 100) {
            monVu.addElemento(new Moneda100());
            cantidadVuelto -= 100;
        }

        this.prodVu = auxOut;
    }

    /**
     * Getter de la primera moneda del vuelto
     * @return Última moneda del depósito de vuelto
     */
    public Moneda getVuelto() {
        return monVu.getElemento();
    }

    /**
     * Getter del producto elegido por el comprador, vaciando el depósito
     * @return Producto comprado
     */
    public Producto getProducto() {
        Producto auxOut = this.prodVu;
        this.prodVu = null;
        return auxOut;
    }

    /**
     * Getter de un depósito de productos según el número indicado al llamar al método
     * El número corresponde a los mismos que se usan internamente según el Enum Precios
     * @return deposito escogido
     */
    public Deposito<Producto> getDepProducto(int type) throws NoHayProductoException {
        Precios depositoActual = Precios.producto(type);

        if(depositoActual == null){
            throw new NoHayProductoException("No existe el depósito indicado");
        }

        return switch (depositoActual) {
            case COCACOLA -> coca;
            case SPRITE -> sprite;
            case FANTA -> fanta;
            case SNICKERS -> snickers;
            case SUPER8 -> super8;
            default -> throw new NoHayProductoException("No existe el depósito indicado");
        };
    }

    /** Getter del depósito del vuelto
     * @return depósito de vuelto
     */
    public Deposito<Moneda> getMonVu(){return monVu;}
}

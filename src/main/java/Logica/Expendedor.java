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
     * @throws PagoIncorrectoException si no se ingresó moneda
     * @throws PagoInsuficienteException si la moneda no alcanza
     * @throws NoHayProductoException si el producto no existe o está agotado
     */
    public void comprarProducto(Moneda dinero, int type)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        if (dinero == null) {
            throw new PagoIncorrectoException("No se ingresó ninguna moneda");
        }

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

        if (dinero.getValor() < productoActual.getPrecio()) {
            monVu.addElemento(dinero);
            throw new PagoInsuficienteException("Pago insuficiente");
        }

        Producto auxOut = dep.getElemento();

        if (auxOut == null) {
            monVu.addElemento(dinero);
            throw new NoHayProductoException("No quedan productos de ese tipo");
        }

        this.ganancias.addElemento(dinero);

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
     * Permite ver el producto que está en la salida sin retirarlo.
     *
     * @return producto disponible o null si no hay
     */
    public Producto verProducto() {
        return this.prodVu;
    }

    /**
     * Rellena hasta 5 unidades los depósitos que estén vacíos.
     */
    public void rellenarDepositosVacios() {
        if (coca.getAlmacen().isEmpty()) {
            while (coca.getAlmacen().size() < 5) {
                coca.addElemento(new CocaCola(1000 + coca.getAlmacen().size()));
            }
        }

        if (sprite.getAlmacen().isEmpty()) {
            while (sprite.getAlmacen().size() < 5) {
                sprite.addElemento(new Sprite(2000 + sprite.getAlmacen().size()));
            }
        }

        if (fanta.getAlmacen().isEmpty()) {
            while (fanta.getAlmacen().size() < 5) {
                fanta.addElemento(new Fanta(3000 + fanta.getAlmacen().size()));
            }
        }

        if (snickers.getAlmacen().isEmpty()) {
            while (snickers.getAlmacen().size() < 5) {
                snickers.addElemento(new Snickers(4000 + snickers.getAlmacen().size()));
            }
        }

        if (super8.getAlmacen().isEmpty()) {
            while (super8.getAlmacen().size() < 5) {
                super8.addElemento(new Super8(5000 + super8.getAlmacen().size()));
            }
        }
    }

    /**
     * Entrega el depósito de productos asociado al identificador indicado.
     *
     * @param type identificador del producto
     * @return depósito correspondiente
     * @throws NoHayProductoException si no existe un depósito asociado a ese identificador
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
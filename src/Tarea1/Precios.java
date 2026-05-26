package Tarea1;

/**
 * Enumeración de todos los productos disponibles con sus respectivos precios e identificadores numéricos.
 * Se utiliza para evitar el uso de constantes numéricas.
 */
public enum Precios {
    COCACOLA(800, 1),
    SPRITE(600, 2),
    FANTA(700, 3),
    SNICKERS(900, 4),
    SUPER8(200, 5);
    private int precio;
    private int id;

    /**
     * Constructor de enum Precios.
     * @param precio valor del producto
     * @param id identificador del producto
     */
    Precios(int precio, int id){
        this.precio=precio;
        this.id = id;
    }

    /**
     * Devuelve el precio del producto.
     * @return precio del producto
     */
    public int getPrecio(){
        return precio;
    }

    /**
     * Devuelve el identificador del producto.
     * @return ID del producto
     */
    public int getID() {
        return id;
    }

    /**
     * Busca y devuelve el producto según su ID.
     * @param ID identificador del producto
     * @return el producto o null si no existe
     */
    public static Precios producto(int ID) {
        for (Precios aux : Precios.values()) {
            if(aux.getID() == ID){
                return aux;
            }
        }
        return null;
    }
}

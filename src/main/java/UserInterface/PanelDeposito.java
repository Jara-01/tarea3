package UserInterface;

import Logica.Deposito;
import Logica.Moneda;
import Logica.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Representa visualmente un depósito de la máquina.
 * Su contenido puede ser monedas o productos.
 */
public class PanelDeposito extends JPanel {
    /** Depósito lógico asociado */
    private Deposito deposito;
    /** Coordenada horizontal del área del depósito */
    private int x;
    /** Coordenada vertical del área del depósito */
    private int y;
    /** Ancho del área disponible para dibujar */
    private int ancho;
    /** Alto del área disponible para dibujar */
    private int alto;

    /**
     * Crea un panel de depósito con tamaño por defecto.
     *
     * @param deposito depósito lógico asociado
     * @param x coordenada horizontal
     * @param y coordenada vertical
     */
    public PanelDeposito(Deposito deposito, int x, int y) {
        this(deposito, x, y, 350, 84);
    }

    /**
     * Crea un panel de depósito con tamaño personalizado.
     *
     * @param deposito depósito lógico asociado
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @param ancho ancho del área
     * @param alto alto del área
     */
    public PanelDeposito(Deposito deposito, int x, int y, int ancho, int alto) {
        this.deposito = deposito;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Dibuja el contenido del depósito según el tipo de elementos que almacene.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (deposito == null || deposito.getAlmacen().isEmpty()) {
            return;
        }

        ArrayList elementos = deposito.getAlmacen();
        Object primero = elementos.get(0);

        if (primero instanceof Moneda) {
            dibujarMonedas(g, elementos);
        } else if (primero instanceof Producto) {
            dibujarProductos(g, elementos);
        }
    }

    /**
     * Dibuja las monedas contenidas en el depósito.
     * Si el depósito es pequeño, las distribuye en dos filas.
     *
     * @param g contexto gráfico
     * @param elementos lista de elementos del depósito
     */
    private void dibujarMonedas(Graphics g, ArrayList elementos) {
        int cantidad = elementos.size();
        int diametro = 24;

        if (ancho <= 120) {
            int maxPorFila = 3;
            int separacionX = 10;

            int monedasFilaArriba = Math.min(cantidad, maxPorFila);
            int anchoFilaArriba = monedasFilaArriba * diametro + (monedasFilaArriba - 1) * separacionX;
            int inicioXArriba = x + (ancho - anchoFilaArriba) / 2;
            int posYArriba = y - 8;

            for (int i = 0; i < monedasFilaArriba; i++) {
                Moneda moneda = (Moneda) elementos.get(i);
                PanelMoneda pm = new PanelMoneda(inicioXArriba, posYArriba, moneda.getValor());
                pm.paintComponent(g);
                inicioXArriba += diametro + separacionX;
            }

            int restantes = cantidad - monedasFilaArriba;

            if (restantes > 0) {
                int monedasFilaAbajo = Math.min(restantes, maxPorFila);
                int anchoFilaAbajo = monedasFilaAbajo * diametro + (monedasFilaAbajo - 1) * separacionX;
                int inicioXAbajo = x + (ancho - anchoFilaAbajo) / 2;
                int posYAbajo = y + diametro - 7;

                for (int i = 0; i < monedasFilaAbajo; i++) {
                    Moneda moneda = (Moneda) elementos.get(monedasFilaArriba + i);
                    PanelMoneda pm = new PanelMoneda(inicioXAbajo, posYAbajo, moneda.getValor());
                    pm.paintComponent(g);
                    inicioXAbajo += diametro + separacionX;
                }
            }
        } else {
            int separacion = 12;
            int anchoTotal = cantidad * diametro + (cantidad - 1) * separacion;
            int inicioX = x + (ancho - anchoTotal) / 2;
            int posY = y + (alto - diametro) / 2;

            for (int i = 0; i < cantidad; i++) {
                Moneda moneda = (Moneda) elementos.get(i);
                PanelMoneda pm = new PanelMoneda(inicioX, posY, moneda.getValor());
                pm.paintComponent(g);
                inicioX += diametro + separacion;
            }
        }
    }

    /**
     * Dibuja los productos contenidos en el depósito.
     * Usa una distribución distinta para bebidas y barritas.
     *
     * @param g contexto gráfico
     * @param elementos lista de elementos del depósito
     */
    private void dibujarProductos(Graphics g, ArrayList elementos) {
        Producto productoBase = (Producto) elementos.get(0);
        String nombre = productoBase.getClass().getSimpleName();

        int cantidad = elementos.size();
        int inicioX;
        int posY;

        if (nombre.equals("Snickers") || nombre.equals("Super8")) {
            int espacioProducto = 72;
            int anchoTotal = cantidad * espacioProducto;

            inicioX = x + (ancho - anchoTotal) / 2 + 10;
            posY = y + 22;

            for (int i = 0; i < cantidad; i++) {
                Producto producto = (Producto) elementos.get(i);
                PanelProducto pp = new PanelProducto(inicioX, posY, producto);
                pp.paintComponent(g);
                inicioX += espacioProducto;
            }
        } else {
            int anchoProducto = 40;
            int separacion = 20;
            int anchoTotal = cantidad * anchoProducto + (cantidad - 1) * separacion;

            inicioX = x + (ancho - anchoTotal) / 2;

            if (nombre.equals("CocaCola")) {
                posY = y + 6;
            } else if (nombre.equals("Sprite") || nombre.equals("Fanta")) {
                posY = y + 3;
            } else {
                posY = y + 10;
            }

            for (int i = 0; i < cantidad; i++) {
                Producto producto = (Producto) elementos.get(i);
                PanelProducto pp = new PanelProducto(inicioX, posY, producto);
                pp.paintComponent(g);
                inicioX += anchoProducto + separacion;
            }
        }
    }
}
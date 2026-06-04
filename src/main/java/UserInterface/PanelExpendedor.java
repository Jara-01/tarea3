package UserInterface;

import Logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Representa visualmente al expendedor dentro de la interfaz.
 * Se encarga de dibujar la máquina, sus depósitos, el vuelto
 * y el producto disponible para retirar.
 */
public class PanelExpendedor extends JPanel {
    /** Lógica principal del expendedor. */
    private Expendedor expendedor;
    /** Imagen base de la máquina. */
    private Image base;
    /** Depósito visual de CocaCola. */
    private PanelDeposito coca;
    /** Depósito visual de Sprite. */
    private PanelDeposito sprite;
    /** Depósito visual de Fanta. */
    private PanelDeposito fanta;
    /** Depósito visual de Snickers. */
    private PanelDeposito snickers;
    /** Depósito visual de Super8. */
    private PanelDeposito super8;
    /** Depósito visual del vuelto. */
    private PanelDeposito monVu;

    /**
     * Crea el panel del expendedor, inicializa su lógica,
     * carga la imagen base y construye los depósitos visuales.
     */
    public PanelExpendedor() {
        expendedor = new Expendedor(5);

        try {
            base = new ImageIcon(getClass().getResource("/expendedora.png")).getImage();
        } catch (Exception e) {
            base = null;
        }

        try {
            coca = new PanelDeposito(expendedor.getDepProducto(Precios.COCACOLA.getID()), 80, 115, 350, 84);
            sprite = new PanelDeposito(expendedor.getDepProducto(Precios.SPRITE.getID()), 80, 220, 350, 84);
            fanta = new PanelDeposito(expendedor.getDepProducto(Precios.FANTA.getID()), 80, 325, 350, 84);
            snickers = new PanelDeposito(expendedor.getDepProducto(Precios.SNICKERS.getID()), 80, 430, 350, 84);
            super8 = new PanelDeposito(expendedor.getDepProducto(Precios.SUPER8.getID()), 80, 535, 350, 84);
        } catch (NoHayProductoException e) {
            coca = new PanelDeposito(null, 80, 115, 350, 84);
            sprite = new PanelDeposito(null, 80, 220, 350, 84);
            fanta = new PanelDeposito(null, 80, 325, 350, 84);
            snickers = new PanelDeposito(null, 80, 430, 350, 84);
            super8 = new PanelDeposito(null, 80, 535, 350, 84);
        }

        monVu = new PanelDeposito(expendedor.getMonVu(), 505, 545, 110, 50);
    }

    /**
     * Entrega el expendedor lógico asociado a este panel.
     *
     * @return expendedor usado por la interfaz
     */
    public Expendedor getExpendedor() {
        return expendedor;
    }

    /**
     * Procesa los clicks hechos sobre la zona del expendedor.
     * Permite retirar vuelto, retirar producto o rellenar
     * depósitos vacíos al hacer click sobre ellos.
     *
     * @param clickX coordenada horizontal del click
     * @param clickY coordenada vertical del click
     */
    public void manejarClick(int clickX, int clickY) {
        // Zona de vuelto
        if (enZona(clickX, clickY, 470, 500, 180, 140)) {
            Moneda moneda = expendedor.getVuelto();

            if (moneda != null) {
                System.out.println("Moneda retirada: " + moneda);
            }
            return;
        }

        // Zona de retiro de producto
        if (enZona(clickX, clickY, 170, 615, 190, 140)) {
            Producto producto = expendedor.getProducto();

            if (producto != null) {
                System.out.println("Producto retirado: " + producto.consumir() + " / serie: " + producto.getSerie());
            }
            return;
        }

        // Click en cualquiera de los depósitos -> rellenar vacíos
        if (enZona(clickX, clickY, 80, 115, 350, 84) ||
                enZona(clickX, clickY, 80, 220, 350, 84) ||
                enZona(clickX, clickY, 80, 325, 350, 84) ||
                enZona(clickX, clickY, 80, 430, 350, 84) ||
                enZona(clickX, clickY, 80, 535, 350, 84)) {
            expendedor.rellenarDepositosVacios();
        }
    }

    /**
     * Revisa si un click cayó dentro de una zona rectangular.
     *
     * @param clickX coordenada horizontal del click
     * @param clickY coordenada vertical del click
     * @param zonaX coordenada horizontal inicial de la zona
     * @param zonaY coordenada vertical inicial de la zona
     * @param zonaAncho ancho de la zona
     * @param zonaAlto alto de la zona
     * @return true si el click está dentro de la zona, false en caso contrario
     */
    private boolean enZona(int clickX, int clickY, int zonaX, int zonaY, int zonaAncho, int zonaAlto) {
        return clickX >= zonaX && clickX <= zonaX + zonaAncho
                && clickY >= zonaY && clickY <= zonaY + zonaAlto;
    }

    /**
     * Dibuja la máquina expendedora, sus depósitos,
     * el vuelto disponible y el producto en salida.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (base != null) {
            g.drawImage(base, 50, 50, 600, 700, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(50, 50, 600, 700);
        }

        coca.paintComponent(g);
        sprite.paintComponent(g);
        fanta.paintComponent(g);
        snickers.paintComponent(g);
        super8.paintComponent(g);
        monVu.paintComponent(g);

        if (expendedor.verProducto() != null) {
            PanelProducto producto = new PanelProducto(225, 640, expendedor.verProducto());
            producto.paintComponent(g);
        }
    }
}
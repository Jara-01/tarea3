package UserInterface;

import Logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Representa visualmente al comprador dentro de la interfaz.
 * Desde este panel se seleccionan monedas y productos mediante clicks.
 */
public class PanelComprador extends JPanel {
    /** Referencia al panel del expendedor para acceder a su lógica */
    private PanelExpendedor panelExpendedor;

    /** Coordenada horizontal del panel */
    private int x;
    /** Coordenada vertical del panel */
    private int y;
    /** Ancho del panel */
    private int ancho;
    /** Alto del panel */
    private int alto;

    /** Moneda actualmente seleccionada por el usuario */
    private Moneda monedaSeleccionada;
    /** Mensaje que se muestra en la parte inferior del panel */
    private String mensaje;

    /**
     * Crea el panel del comprador y lo asocia al panel del expendedor.
     *
     * @param panelExpendedor panel del expendedor con el que interactúa el comprador
     */
    public PanelComprador(PanelExpendedor panelExpendedor) {
        this.panelExpendedor = panelExpendedor;
        this.x = 700;
        this.y = 100;
        this.ancho = 220;
        this.alto = 500;
        this.mensaje = "Seleccione moneda y producto";
    }

    /**
     * Procesa los clicks realizados dentro del panel del comprador.
     * Permite seleccionar monedas y elegir el producto que se quiere comprar.
     *
     * @param clickX coordenada horizontal del click
     * @param clickY coordenada vertical del click
     */
    public void manejarClick(int clickX, int clickY) {
        if (clickX < x || clickX > x + ancho || clickY < y || clickY > y + alto) {
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 50, 80, 30)) {
            monedaSeleccionada = new Moneda100();
            mensaje = "Moneda seleccionada: 100";
            return;
        }

        if (enZona(clickX, clickY, x + 120, y + 50, 80, 30)) {
            monedaSeleccionada = new Moneda500();
            mensaje = "Moneda seleccionada: 500";
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 90, 80, 30)) {
            monedaSeleccionada = new Moneda1000();
            mensaje = "Moneda seleccionada: 1000";
            return;
        }

        if (enZona(clickX, clickY, x + 120, y + 90, 80, 30)) {
            monedaSeleccionada = new Moneda1500();
            mensaje = "Moneda seleccionada: 1500";
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 160, 180, 30)) {
            comprar(Precios.COCACOLA.getID());
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 200, 180, 30)) {
            comprar(Precios.SPRITE.getID());
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 240, 180, 30)) {
            comprar(Precios.FANTA.getID());
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 280, 180, 30)) {
            comprar(Precios.SNICKERS.getID());
            return;
        }

        if (enZona(clickX, clickY, x + 20, y + 320, 180, 30)) {
            comprar(Precios.SUPER8.getID());
        }
    }

    /**
     * Intenta comprar el producto indicado usando la moneda seleccionada.
     *
     * @param idProducto identificador del producto que se desea comprar
     */
    private void comprar(int idProducto) {
        Expendedor exp = panelExpendedor.getExpendedor();

        if (exp.verProducto() != null || !exp.getMonVu().getAlmacen().isEmpty()) {
            mensaje = "Retire producto y vuelto antes de comprar otro";
            return;
        }

        if (monedaSeleccionada == null) {
            mensaje = "Seleccione una moneda antes de comprar";
            return;
        }

        try {
            exp.comprarProducto(monedaSeleccionada, idProducto);
            mensaje = "Compra realizada, retire producto y vuelto";
            monedaSeleccionada = null;
        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            mensaje = e.getMessage();
            monedaSeleccionada = null;
        }
    }

    /**
     * Revisa si un click cae dentro de una zona rectangular.
     *
     * @param clickX coordenada horizontal del click
     * @param clickY coordenada vertical del click
     * @param zonaX coordenada horizontal de la zona
     * @param zonaY coordenada vertical de la zona
     * @param zonaAncho ancho de la zona
     * @param zonaAlto alto de la zona
     * @return true si el click está dentro de la zona, false en caso contrario
     */
    private boolean enZona(int clickX, int clickY, int zonaX, int zonaY, int zonaAncho, int zonaAlto) {
        return clickX >= zonaX && clickX <= zonaX + zonaAncho
                && clickY >= zonaY && clickY <= zonaY + zonaAlto;
    }

    /**
     * Dibuja un botón simple con color y texto.
     *
     * @param g contexto gráfico
     * @param bx coordenada horizontal del botón
     * @param by coordenada vertical del botón
     * @param bw ancho del botón
     * @param bh alto del botón
     * @param texto texto que se muestra en el botón
     * @param color color de relleno del botón
     */
    private void dibujarBoton(Graphics g, int bx, int by, int bw, int bh, String texto, Color color) {
        g.setColor(color);
        g.fillRect(bx, by, bw, bh);

        g.setColor(Color.BLACK);
        g.drawRect(bx, by, bw, bh);
        g.drawString(texto, bx + 10, by + 20);
    }

    /**
     * Dibuja el mensaje inferior en varias líneas para que no se salga del panel.
     *
     * @param g contexto gráfico
     * @param texto mensaje que se quiere mostrar
     * @param inicioX coordenada horizontal inicial
     * @param inicioY coordenada vertical inicial
     * @param anchoMax ancho máximo disponible para el texto
     */
    private void dibujarMensaje(Graphics g, String texto, int inicioX, int inicioY, int anchoMax) {
        FontMetrics fm = g.getFontMetrics();
        String[] palabras = texto.split(" ");
        String linea = "";
        int yActual = inicioY;

        for (String palabra : palabras) {
            String prueba = linea.isEmpty() ? palabra : linea + " " + palabra;

            if (fm.stringWidth(prueba) > anchoMax) {
                g.drawString(linea, inicioX, yActual);
                linea = palabra;
                yActual += 18;
            } else {
                linea = prueba;
            }
        }

        if (!linea.isEmpty()) {
            g.drawString(linea, inicioX, yActual);
        }
    }

    /**
     * Dibuja el panel completo del comprador, incluyendo botones y mensaje.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(220, 220, 220));
        g.fillRect(x, y, ancho, alto);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
        g.drawString("Comprador", x + 70, y + 25);

        g.drawString("Monedas", x + 75, y + 45);

        dibujarBoton(g, x + 20, y + 50, 80, 30, "100", Color.LIGHT_GRAY);
        dibujarBoton(g, x + 120, y + 50, 80, 30, "500", Color.YELLOW);
        dibujarBoton(g, x + 20, y + 90, 80, 30, "1000", Color.ORANGE);
        dibujarBoton(g, x + 120, y + 90, 80, 30, "1500", Color.RED);

        g.setColor(Color.BLACK);
        g.drawString("Productos", x + 70, y + 145);

        dibujarBoton(g, x + 20, y + 160, 180, 30, "CocaCola $" + Precios.COCACOLA.getPrecio(), Color.CYAN);
        dibujarBoton(g, x + 20, y + 200, 180, 30, "Sprite $" + Precios.SPRITE.getPrecio(), Color.GREEN);
        dibujarBoton(g, x + 20, y + 240, 180, 30, "Fanta $" + Precios.FANTA.getPrecio(), Color.ORANGE);
        dibujarBoton(g, x + 20, y + 280, 180, 30, "Snickers $" + Precios.SNICKERS.getPrecio(), Color.PINK);
        dibujarBoton(g, x + 20, y + 320, 180, 30, "Super8 $" + Precios.SUPER8.getPrecio(), Color.MAGENTA);

        g.setColor(Color.BLACK);
        dibujarMensaje(g, mensaje, x + 20, y + 390, 180);
    }
}
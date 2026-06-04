package UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Representa visualmente una moneda dentro de la interfaz.
 * El color cambia según el valor de la moneda.
 */
public class PanelMoneda extends JPanel {
    /** Coordenada horizontal de la moneda */
    private int x;
    /** Coordenada vertical de la moneda */
    private int y;
    /** Valor de la moneda */
    private int valor;

    /**
     * Crea una vista de moneda en una posición dada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @param valor valor de la moneda
     */
    public PanelMoneda(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    /**
     * Dibuja la moneda usando un color distinto según su valor.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (valor == 100) {
            g.setColor(Color.LIGHT_GRAY);
        } else if (valor == 500) {
            g.setColor(Color.YELLOW);
        } else if (valor == 1000) {
            g.setColor(Color.ORANGE);
        } else if (valor == 1500) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillOval(x, y, 20, 20);

        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(valor), x + 2, y + 15);
    }
}
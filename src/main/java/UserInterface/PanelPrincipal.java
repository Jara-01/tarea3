package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panel principal de la aplicación.
 * Desde aquí se dibujan el comprador y el expendedor,
 * y también se reciben los clicks del mouse.
 */
public class PanelPrincipal extends JPanel implements MouseListener {
    /** Panel del comprador */
    private PanelComprador pComp;
    /** Panel del expendedor */
    private PanelExpendedor pExp;

    /**
     * Crea el panel principal y sus componentes.
     */
    public PanelPrincipal() {
        pExp = new PanelExpendedor();
        pComp = new PanelComprador(pExp);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.addMouseListener(this);
    }

    /**
     * Dibuja los componentes principales de la interfaz.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pComp.paintComponent(g);
        pExp.paintComponent(g);
    }

    /**
     * Procesa el click del mouse apenas se presiona
     * y lo reenvía al comprador y al expendedor.
     *
     * @param e evento de mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        pComp.manejarClick(x, y);
        pExp.manejarClick(x, y);

        repaint();
    }

    /**
     * Método requerido por la interfaz MouseListener.
     *
     * @param e evento de mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Método requerido por la interfaz MouseListener.
     *
     * @param e evento de mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Método requerido por la interfaz MouseListener.
     *
     * @param e evento de mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Método requerido por la interfaz MouseListener.
     *
     * @param e evento de mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
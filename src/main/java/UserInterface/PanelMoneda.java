package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PanelMoneda extends JPanel {
    private int x;
    private int y;
    private int valor;

    public PanelMoneda(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.setBackground(Color.cyan);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(100,100,100,100);
    }
}

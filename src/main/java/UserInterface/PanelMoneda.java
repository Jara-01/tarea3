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

        this.valor = valor;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        switch (valor){
            case 100:
                g.setColor(new Color(159, 110, 44));
                break;
            case 500:
                g.setColor(new Color(213, 149, 41));
                break;
            case 1000:
                g.setColor(new Color(232, 184, 24));
                break;
            case 1500:
                g.setColor(new Color(255, 221,0));
                break;

        }
        g.fillOval(x, y,40,40);
    }
}

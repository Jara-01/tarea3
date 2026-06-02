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

        switch (valor){
            case 100:
                g.setColor(new Color(184,115,51));
                break;
            case 500:
                g.setColor(new Color(208,148,34));
                break;
            case 1000:
                g.setColor(new Color(231,182,17));
                break;
            case 1500:
                g.setColor(new Color(255,215,0));
                break;

        }
        g.fillOval(x + 15,y +15,100,100);
    }
}

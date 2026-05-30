package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PanelProducto extends JPanel {
    private int x;
    private int y;

    public PanelProducto (int x,int y) {
        this.x = x;
        this.y = y;
        this.setBackground(Color.white);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(x,y,10,30);
    }
}

package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PanelDeposito extends JPanel {
    private PanelProducto producto;

    public PanelDeposito () {
        this.producto = new PanelProducto();
        this.setBackground(Color.white);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        producto.paintComponent(g);
    }
}

package UserInterface;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelProducto extends JPanel {
    private int x;
    private int y;

    private Image imagenProducto;

    public PanelProducto (int x,int y, Producto producto) {
        this.x = x;
        this.y = y;

        if(producto == null) {
            imagenProducto = null;
            return;
        }

        URL url = getClass().getResource(producto.rutaImagen());
        if(url != null) {
            imagenProducto = new ImageIcon(url).getImage();
        }
        else {
            imagenProducto = null;
        }
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenProducto, x, y,45,65,this);
    }
}

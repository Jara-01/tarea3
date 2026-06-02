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

        String rutaImagen = "";
        if(producto instanceof CocaCola){
            rutaImagen = "/coca.png";
        } else if (producto instanceof Sprite) {
            rutaImagen = "/sprite.png";
        } else if (producto instanceof  Fanta) {
            rutaImagen = "/fanta.png";
        } else if (producto instanceof  Super8) {
            rutaImagen = "/super8.png";
        } else if (producto instanceof Snickers) {
            rutaImagen = "/snickers.png";
        }

        URL url = getClass().getResource(rutaImagen);
        if(url != null) {
            imagenProducto = new ImageIcon(url).getImage();
        }
        else {
            imagenProducto = null;
        }

    }

    @Override
    public void paintComponent (Graphics g) {

        g.drawImage(imagenProducto, x, y,50,50,null);
    }
}

package UserInterface;

import javax.swing.*;
import java.awt.*;

import Logica.Deposito;
import Logica.Moneda;
import Logica.Producto;


public class PanelDeposito extends JPanel {
    private Deposito<?> deposito;

    private int x;
    private int y;

    public PanelDeposito (Deposito<?> deposito, int x, int y) {
       this.deposito = deposito;

       this.x = x;
       this.y = y;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if(deposito == null){
            return;
        }

        int separacion = 20;
        int i = 0;
        for(Object obj : deposito.getAlmacen()) {
            if (obj instanceof Moneda) {
                int posX = x + (i % 3) * 45;
                int posY = y + 5 + (i / 3) * 45;
                PanelMoneda m = new PanelMoneda(posX, posY, ((Moneda) obj).getValor());
                m.paintComponent(g);
                i++;
            } else if (obj instanceof Producto){
                PanelProducto p = new PanelProducto(x + separacion, y + 14, (Producto) obj);
                p.paintComponent(g);
                separacion += 65;
            }

        }

    }
}

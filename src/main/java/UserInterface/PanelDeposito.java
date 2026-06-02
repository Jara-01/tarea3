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
        if(deposito == null){
            return;
        }

        int separacion = 5;

        for(Object obj : deposito.getAlmacen()) {
            if (obj instanceof Moneda) {
                PanelMoneda m = new PanelMoneda(x + 15, y + separacion, ((Moneda) obj).getValor());
                m.paintComponent(g);
            } else if (obj instanceof Producto){
                PanelProducto p = new PanelProducto(x + 15, y + separacion, (Producto) obj);
                p.paintComponent(g);
            }

            separacion += 30;
        }
    }
}

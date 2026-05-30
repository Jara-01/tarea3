package UserInterface;

import javax.swing.*;
import java.awt.*;

import Logica.Deposito;



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

        g.setColor(Color.darkGray);
        g.fillRect(x, y, 70, 40);

        if(deposito == null){
            return;
        }

        int separacion = 5;
        /*for(Object obj : deposito.getAlmacen()){
            if(obj instanceof Moneda){
                PanelMoneda m = new PanelMoneda(x + 15,y +separacion, ((Moneda) obj).getValor());
                m.paintComponent(g);
            }
            else {
                PanelProducto p = new PanelProducto(x + 15, y + separacion);
                p.paintComponent(g);
            }

            separacion += 30;
        }*/
    }
}

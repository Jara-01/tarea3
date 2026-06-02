package UserInterface;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;

import Logica.*;

public class PanelExpendedor extends JPanel {
    private final int coordX = 50;
    private final int coordY = 50;

    private Expendedor expendedor;

    private PanelDeposito coca;
    private PanelDeposito sprite;
    private PanelDeposito fanta;
    private PanelDeposito snickers;
    private PanelDeposito super8;

    private PanelDeposito monVu;
    private PanelProducto productoFinal;

    public PanelExpendedor() throws NoHayProductoException {
        setBackground(new Color(222, 228, 234));

        expendedor = new Expendedor(5);


        coca     = new PanelDeposito(expendedor.getDepProducto(1), coordX + 40,coordY + 40);
        sprite   = new PanelDeposito(expendedor.getDepProducto(2), coordX + 40,coordY + 140);
        fanta    = new PanelDeposito(expendedor.getDepProducto(3), coordX + 40,coordY + 240);
        snickers = new PanelDeposito(expendedor.getDepProducto(4), coordX + 40,coordY + 340);
        super8   = new PanelDeposito(expendedor.getDepProducto(5), coordX + 40,coordY + 440);

        monVu         = new PanelDeposito(expendedor.getMonVu(),coordX + 430,coordY + 405);
        productoFinal = new PanelProducto(coordX + 190,coordY + 600, expendedor.getProdFinal());
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        //fondo rojo
        g.setColor(new Color(16, 122, 133));
        g.fillRect(coordX,coordY,600,700);

        g.setColor(Color.black);
        //fonfo de contenedores
        g.fillRect(coordX + 25,coordY + 25,380,515);

        g.fillRect(coordX + 420, coordY + 400, 160, 110);

        //espacio de producto comprado
        g.fillRect(coordX + 130,coordY + 560,160,120);
        g.setColor(Color.white);
        g.fillRect(coordX + 140,coordY + 570,140,100);

        //zona vuelto
        g.setColor(new Color(236, 235, 235, 255));
        g.fillRect(coordX + 425, coordY + 405, 150, 100);

        //contenedores
        g.setColor(Color.gray);
        int posX  = coordX + 40;
        int posY  = coordY + 40;

        for(int i = 0;i < 5; i++){
        g.fillRect(posX, posY + i * 100, 350, 85);
        }

        for (PanelDeposito deposito : Arrays.asList(coca, sprite, fanta, snickers, super8, monVu)) {
            deposito.paintComponent(g);
        }
        productoFinal.paintComponent(g);
    }
}

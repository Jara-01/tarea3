package UserInterface;

import javax.swing.*;
import java.awt.*;

import Logica.Expendedor;

public class PanelExpendedor extends JPanel {
    final int coordX = 50;
    final private int coordY = 50;

    private Expendedor expendedor;

    private PanelDeposito coca;
    private PanelDeposito sprite;
    private PanelDeposito fanta;
    private PanelDeposito snickers;
    private PanelDeposito super8;

    private PanelDeposito monVu;
    private PanelProducto productoFinal;

    public PanelExpendedor(){
        setBackground(new Color(222, 228, 234));

        expendedor = new Expendedor(5);

        coca     = new PanelDeposito(null, coordX + 40,coordY + 40);
        sprite   = new PanelDeposito(null, coordX + 40,coordY + 140);
        fanta    = new PanelDeposito(null, coordX + 40,coordY + 240);
        snickers = new PanelDeposito(null, coordX + 40,coordY + 340);
        super8   = new PanelDeposito(null, coordX + 40,coordY + 440);

        monVu = new PanelDeposito(null,coordX + 430,coordY + 405);
        //productoFinal = new PanelProducto(coordX + 202,coordY + 605, null);
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
        //espacio de producto comprado
        g.fillRect(coordX + 140,coordY + 570,140,100);

        //contenedores
        g.setColor(Color.darkGray);
        g.fillRect(coordX + 40, coordY + 40, 350, 85);
        g.fillRect(coordX + 40, coordY + 140, 350, 85);
        g.fillRect(coordX + 40, coordY + 240, 350, 85);
        g.fillRect(coordX + 40, coordY + 340, 350, 85);
        g.fillRect(coordX + 40, coordY + 440, 350, 85);

        //zona vuelto
        g.fillRect(coordX + 425, coordY + 405, 150, 100);

        coca.paintComponent(g);
        sprite.paintComponent(g);
        fanta.paintComponent(g);
        snickers.paintComponent(g);
        super8.paintComponent(g);
        monVu.paintComponent(g);
        //productoFinal.paintComponent(g);
    }
}

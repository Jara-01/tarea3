package UserInterface;

import javax.swing.*;
import java.awt.*;

import Logica.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private Image base;

    private PanelDeposito coca;
    private PanelDeposito sprite;
    private PanelDeposito fanta;
    private PanelDeposito snickers;
    private PanelDeposito super8;

    private PanelDeposito monVu;
    private PanelProducto productoFinal;

    public PanelExpendedor(){
        expendedor = new Expendedor(5);
        base = new ImageIcon(getClass().getResource("/expendedora.png")).getImage();

        coca     = new PanelDeposito(null,100,120);
        sprite   = new PanelDeposito(null,100,220);
        fanta    = new PanelDeposito(null,100,320);
        snickers = new PanelDeposito(null,100,420);
        super8   = new PanelDeposito(null,100,520);

        monVu = new PanelDeposito(null,500,500);
        productoFinal = new PanelProducto(252,655);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(50,50,300,400);
        g.drawImage(base, 50, 50, 600, 700, null);

        coca.paintComponent(g);
        sprite.paintComponent(g);
        fanta.paintComponent(g);
        snickers.paintComponent(g);
        super8.paintComponent(g);
        monVu.paintComponent(g);
        productoFinal.paintComponent(g);
    }
}

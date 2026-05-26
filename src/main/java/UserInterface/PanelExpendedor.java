package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private PanelDeposito coca;
    private PanelDeposito sprite;
    private PanelDeposito fanta;
    private PanelDeposito snickers;
    private PanelDeposito super8;

    private PanelDeposito monVu;
    private PanelProducto productoFinal;

    public PanelExpendedor(){
        this.coca = new PanelDeposito();
        this.sprite = new PanelDeposito();
        this.fanta = new PanelDeposito();
        this.snickers = new PanelDeposito();
        this.super8 = new PanelDeposito();

        this.monVu = new PanelDeposito();
        this.productoFinal = new PanelProducto();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        coca.paintComponent(g);
        sprite.paintComponent(g);
        fanta.paintComponent(g);
        snickers.paintComponent(g);
        super8.paintComponent(g);
    }
}

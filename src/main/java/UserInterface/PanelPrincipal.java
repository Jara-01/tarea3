package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private PanelComprador pComp;
    private PanelExpendedor pExp;

    public PanelPrincipal () {
        pExp = new PanelExpendedor();
        pComp = new PanelComprador();
        this.setBackground(Color.white);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        pComp.paintComponent(g);
        pExp.paintComponent(g);
    }
}

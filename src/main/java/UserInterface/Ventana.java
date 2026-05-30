package UserInterface;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{

    public Ventana() throws HeadlessException {
        super();
        this.setLayout(new BorderLayout());

        this.setTitle("Expendedora");
        this.setSize(1000,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(new PanelExpendedor());

        this.setVisible(true);
    }
}

package UserInterface;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * Ventana principal de la aplicación.
 * Desde aquí se crea el panel central que contiene al comprador y al expendedor.
 */
public class Ventana extends JFrame {

    /**
     * Crea la ventana principal del sistema.
     *
     * @throws HeadlessException si no existe entorno gráfico disponible
     */
    public Ventana() throws HeadlessException {
        super();

        this.setLayout(new BorderLayout());
        this.setTitle("Expendedora");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new PanelPrincipal(), BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
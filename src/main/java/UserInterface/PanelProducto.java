package UserInterface;

import Logica.Producto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa visualmente un producto dentro de la interfaz.
 * Carga y reutiliza imágenes según el tipo de producto.
 */
public class PanelProducto extends JPanel {
    /** Caché de imágenes para no cargarlas cada vez que se dibuja */
    private static final Map<String, BufferedImage> cacheImagenes = new HashMap<>();

    /** Coordenada horizontal del producto */
    private int x;
    /** Coordenada vertical del producto */
    private int y;
    /** Producto lógico asociado */
    private Producto producto;

    /**
     * Crea una vista de producto en una posición dada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @param producto producto que se quiere mostrar
     */
    public PanelProducto(int x, int y, Producto producto) {
        this.x = x;
        this.y = y;
        this.producto = producto;
    }

    /**
     * Dibuja el producto usando su imagen correspondiente.
     *
     * @param g contexto gráfico usado para dibujar
     */
    @Override
    public void paintComponent(Graphics g) {
        if (producto == null) return;

        BufferedImage imagen = cargarImagen();
        if (imagen == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String nombre = producto.getClass().getSimpleName();

        int ancho;
        int alto;
        double angulo = 0;

        if (nombre.equals("Snickers") || nombre.equals("Super8")) {
            ancho = 52;
            alto = 16;
            angulo = Math.toRadians(-30);
        } else {
            ancho = 40;
            alto = 52;
        }

        int centroX = x + ancho / 2;
        int centroY = y + alto / 2;

        g2.rotate(angulo, centroX, centroY);
        g2.drawImage(imagen, x, y, ancho, alto, null);
        g2.dispose();
    }

    /**
     * Carga la imagen del producto usando caché para evitar recargas innecesarias.
     *
     * @return imagen del producto o null si no se pudo cargar
     */
    private BufferedImage cargarImagen() {
        String archivo = obtenerNombreArchivo();
        if (archivo.isEmpty()) return null;

        if (cacheImagenes.containsKey(archivo)) {
            return cacheImagenes.get(archivo);
        }

        try {
            InputStream is = getClass().getResourceAsStream(archivo);
            if (is != null) {
                BufferedImage imagen = ImageIO.read(is);
                cacheImagenes.put(archivo, imagen);
                return imagen;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Determina el nombre del archivo de imagen según el tipo de producto.
     *
     * @return ruta del archivo de imagen o cadena vacía si no corresponde a ninguno
     */
    private String obtenerNombreArchivo() {
        String nombre = producto.getClass().getSimpleName();

        if (nombre.equals("CocaCola")) return "/cocacola.png";
        if (nombre.equals("Sprite")) return "/sprite.png";
        if (nombre.equals("Fanta")) return "/fanta.png";
        if (nombre.equals("Snickers")) return "/snickers.png";
        if (nombre.equals("Super8")) return "/super8.png";

        return "";
    }
}
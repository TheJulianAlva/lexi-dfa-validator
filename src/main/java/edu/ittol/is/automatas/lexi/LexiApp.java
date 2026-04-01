package edu.ittol.is.automatas.lexi;

import com.formdev.flatlaf.FlatDarkLaf;
import edu.ittol.is.automatas.lexi.core.AutomataEngine;
import edu.ittol.is.automatas.lexi.gui.LexiUI;
import javax.swing.SwingUtilities;

/**
 * Clase principal de inicialización y punto de entrada de la aplicación.
 * <p>
 * Responsable de arrancar la configuración visual, e instanciar el motor de autómata, 
 * para finalmente lanzar la vista gráfica.
 * </p>
 */
public class LexiApp {
    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            System.err.println("Advertencia: Fallo al cargar el tema FlatLaf. Se continuará con el estilo nativo.");
        }
        AutomataEngine engine = new AutomataEngine();

        SwingUtilities.invokeLater(() -> {
            LexiUI mainWindow = new LexiUI(engine);
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setVisible(true);
        });
    }
}

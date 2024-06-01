package ar.edu.unlp.oo1.ejercicio1.app;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ar.edu.unlp.oo1.ejercicio1.ui.WallPostUI;

public class Ejercicio1Application {
    public static void main(String[] args) throws SecurityException, IOException {
        Logger.getLogger("app.model").addHandler(new FileHandler("log.txt"));

        Logger.getLogger("app.model").setLevel(Level.WARNING);
        Logger.getLogger("app.ui").setLevel(Level.INFO);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WallPostUI();
            }
        });
    }

}

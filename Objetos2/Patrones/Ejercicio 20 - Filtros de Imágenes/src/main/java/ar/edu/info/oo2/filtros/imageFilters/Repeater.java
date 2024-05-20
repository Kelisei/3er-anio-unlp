package ar.edu.info.oo2.filtros.imageFilters;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Repeater extends Filter {
  final public static int X_REPEAT = 3;
  final public static int Y_REPEAT = 3;

  public BufferedImage filter(BufferedImage image) {
    BufferedImage newImage = new BufferedImage(image.getWidth() * X_REPEAT, image.getHeight() * Y_REPEAT, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = newImage.createGraphics();

    for (int x = 0; x < X_REPEAT; x++) {
      for (int y = 0; y < Y_REPEAT; y++) {
        graphics.drawImage(image, image.getWidth() * x, image.getHeight() * y, null);
      }
    }
    graphics.dispose();
    return newImage;
  }
}

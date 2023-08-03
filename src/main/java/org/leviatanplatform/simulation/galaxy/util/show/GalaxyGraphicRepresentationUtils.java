package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.awt.*;
import java.util.List;

public class GalaxyGraphicRepresentationUtils {

    public static void paintCurrentListOfStars(Graphics g, List<Star> listOfStar, int width, int height, double initialMaxDistance) {

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        int pixelsReference = Math.min(width, height);

        for (Star star : listOfStar) {
            paintStar(g, star, initialMaxDistance, pixelsReference);
        }
    }

    private static void paintStar(Graphics g, Star star, double initialMaxDistance, int pixelsReference) {

        Vector position = star.position();
        double z = position.z();
        int scaledColor = scaleColorZ(initialMaxDistance/5, z);

        Color color = new Color(255, scaledColor, scaledColor);
        g.setColor(color);

        int x = scaleToScreen(initialMaxDistance, pixelsReference, position.x());
        int y = scaleToScreen(initialMaxDistance, pixelsReference, position.y());
        g.fillOval(x, y, 3, 3);
    }

    private static int scaleColorZ(double distanceToScale, double z) {

        int color = (int) (126 * z/distanceToScale + 128);

        if (color < 0) {
            return 0;
        } else if (color > 255) {
            return 255;
        } else {
            return color;
        }
    }

    private static int scaleToScreen(double initialMaxDistance, int pixelsReference, double coordinate) {
        return (int) (pixelsReference * coordinate / initialMaxDistance) + pixelsReference/2;
    }
}

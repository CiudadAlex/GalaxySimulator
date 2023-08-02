package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;

import java.awt.*;
import java.util.List;

public class GalaxyGraphicRepresentationUtils {

    public static void paintCurrentListOfStars(Graphics g, List<Star> listOfStar, int width, int height, double initialMaxDistance) {

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);

        int pixelsReference = Math.min(width, height);

        for (Star star : listOfStar) {
            paintStar(g, star, initialMaxDistance, pixelsReference);
        }
    }

    private static void paintStar(Graphics g, Star star, double initialMaxDistance, int pixelsReference) {
        Vector position = star.position();
        int x = scaleToScreen(initialMaxDistance, pixelsReference, position.x());
        int y = scaleToScreen(initialMaxDistance, pixelsReference, position.y());
        g.fillOval(x, y, 3, 3);
    }

    private static int scaleToScreen(double initialMaxDistance, int pixelsReference, double coordinate) {
        return (int) (pixelsReference * coordinate / initialMaxDistance) + pixelsReference/2;
    }
}

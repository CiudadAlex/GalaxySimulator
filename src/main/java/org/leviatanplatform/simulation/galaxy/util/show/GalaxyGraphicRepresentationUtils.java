package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;

import java.awt.*;
import java.util.List;

public class GalaxyGraphicRepresentationUtils {

    public static void paintCurrentListOfStars(Graphics g, List<Star> listOfStar, int width, int height) {

        double maxDistantStar = listOfStar.stream().map(star -> star.position().norm()).mapToDouble(d -> d).max()
                .orElse(RandomStarGenerator.MILKY_WAY_RADIUS);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);

        int pixelsReference = Math.min(width, height);

        for (Star star : listOfStar) {
            paintStar(g, star, maxDistantStar, pixelsReference);
        }
    }

    private static void paintStar(Graphics g, Star star, double maxDistantStar, int pixelsReference) {
        Vector position = star.position();
        int x = (int) (pixelsReference * position.x() / maxDistantStar);
        int y = (int) (pixelsReference * position.y() / maxDistantStar);
        g.fillOval(x, y, 1, 1);
    }
}

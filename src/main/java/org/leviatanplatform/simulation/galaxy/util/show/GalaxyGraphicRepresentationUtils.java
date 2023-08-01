package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GalaxyGraphicRepresentationUtils {

    // FIXME test

    public static JPanel createCanvas(Galaxy galaxy) {

        JPanel canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int pixelsReference = Math.min(getWidth(), getHeight());
                GalaxyGraphicRepresentationUtils.paintCurrentListOfStars(g, galaxy.listStar(), pixelsReference);
            }
        };

        return canvas;
    }

    public static void paintCurrentListOfStars(Graphics g, List<Star> listOfStar, int pixelsReference) {

        double maxDistantStar = listOfStar.stream().map(star -> star.position().norm()).mapToDouble(d -> d).max()
                .orElse(RandomStarGenerator.MILKY_WAY_RADIUS);

        g.setColor(Color.black);

        for (Star star : listOfStar) {
            paintStar(g, star, maxDistantStar, pixelsReference);
        }
    }

    private static void paintStar(Graphics g, Star star, double maxDistantStar, int pixelsReference) {
        Vector position = star.position();
        int x = (int) (pixelsReference * position.x() / maxDistantStar);
        int y = (int) (pixelsReference * position.y() / maxDistantStar);
        g.drawRect(x, y, 1, 1);
    }
}

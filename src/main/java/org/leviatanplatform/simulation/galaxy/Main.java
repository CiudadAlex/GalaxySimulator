package org.leviatanplatform.simulation.galaxy;

import org.leviatanplatform.simulation.galaxy.engine.DynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.NewtonianDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;
import org.leviatanplatform.simulation.galaxy.util.show.GalaxyGraphicRepresentation;

public class Main {

    public static void main(String[] args) {

        int numberOfStars = 100;
        Galaxy galaxy = RandomStarGenerator.generateGalaxy(numberOfStars);
        DynamicsCalculator dynamicsCalculator = new NewtonianDynamicsCalculator();

        double seconds = RandomStarGenerator.YEAR;
        int numberOfIterations = 10;
        GalaxyGraphicRepresentation galaxyGraphicRepresentation = new GalaxyGraphicRepresentation();

        for (int i = 0 ; i < numberOfIterations; i++) {
            galaxy = dynamicsCalculator.moveTimeForward(galaxy, seconds);
            galaxyGraphicRepresentation.show(galaxy);
        }
    }
}

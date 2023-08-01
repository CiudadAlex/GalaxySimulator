package org.leviatanplatform.simulation.galaxy;

import org.leviatanplatform.simulation.galaxy.engine.DynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.NewtonianDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;

public class Main {

    public static void main(String[] args) {

        int numberOfStars = 100;
        Galaxy galaxy = RandomStarGenerator.generateGalaxy(numberOfStars);
        DynamicsCalculator dynamicsCalculator = new NewtonianDynamicsCalculator();

        double seconds = RandomStarGenerator.YEAR;
        int numberOfIterations = 10;

        for (int i = 0 ; i < numberOfIterations; i++) {
            galaxy = dynamicsCalculator.moveTimeForward(galaxy, seconds);
            show(galaxy);
        }
    }

    private static void show(Galaxy galaxy) {
        // FIXME finish
    }
}

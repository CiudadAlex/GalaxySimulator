package org.leviatanplatform.simulation.galaxy;

import org.leviatanplatform.simulation.galaxy.engine.DynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.NewtonianDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.util.Chronometer;
import org.leviatanplatform.simulation.galaxy.util.RandomStarGenerator;
import org.leviatanplatform.simulation.galaxy.util.show.GalaxyGraphicRepresentation;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numberOfStars = 1000;
        Galaxy galaxy = RandomStarGenerator.generateGalaxy(numberOfStars);
        DynamicsCalculator dynamicsCalculator = new NewtonianDynamicsCalculator();

        double seconds = RandomStarGenerator.YEAR;
        int numberOfIterations = 10;
        GalaxyGraphicRepresentation galaxyGraphicRepresentation = new GalaxyGraphicRepresentation();
        Chronometer chronometer = new Chronometer();

        for (int i = 0 ; i < numberOfIterations; i++) {

            chronometer.tic();
            galaxy = dynamicsCalculator.moveTimeForward(galaxy, seconds);
            chronometer.toc();

            galaxyGraphicRepresentation.show(galaxy);

            Thread.sleep(1000);
        }
    }
}

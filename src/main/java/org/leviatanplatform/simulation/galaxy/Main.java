package org.leviatanplatform.simulation.galaxy;

import org.leviatanplatform.simulation.galaxy.engine.DynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.NewtonianDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.RelativisticDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.util.Chronometer;
import org.leviatanplatform.simulation.galaxy.util.generator.RandomGalaxyGenerator;
import org.leviatanplatform.simulation.galaxy.util.show.GalaxyGraphicRepresentation;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numberOfIterations = 10000;
        int numberOfStars = 1000;
        int millisToRepaint = 10;
        boolean relativistic = false;
        boolean flatUniverse = false;
        boolean hasBlackHole = false;
        double velocityFactor = 1e6;

        simulateGalaxy(numberOfIterations, numberOfStars, millisToRepaint, relativistic, flatUniverse, hasBlackHole,
                velocityFactor);
    }

    public static void simulateGalaxy(int numberOfIterations, int numberOfStars, int millisToRepaint,
                                      boolean relativistic, boolean flatUniverse, boolean hasBlackHole,
                                      double velocityFactor) throws InterruptedException {

        Galaxy galaxy = flatUniverse ? RandomGalaxyGenerator.generateGalaxyFixedMassZ0(numberOfStars, velocityFactor)
                : RandomGalaxyGenerator.generateGalaxy(numberOfStars, velocityFactor);

        if (hasBlackHole) {
            RandomGalaxyGenerator.addBlackHole(galaxy);
        }

        DynamicsCalculator dynamicsCalculator = relativistic ? new RelativisticDynamicsCalculator() : new NewtonianDynamicsCalculator();

        double seconds = 3 * 30 * RandomGalaxyGenerator.DAY;

        GalaxyGraphicRepresentation galaxyGraphicRepresentation = new GalaxyGraphicRepresentation();
        Chronometer chronometer = new Chronometer();

        paint(galaxyGraphicRepresentation, galaxy, millisToRepaint);

        for (int i = 0 ; i < numberOfIterations; i++) {

            chronometer.tic();
            galaxy = dynamicsCalculator.moveTimeForward(galaxy, seconds);
            chronometer.toc();

            inspect(0, galaxy);

            paint(galaxyGraphicRepresentation, galaxy, millisToRepaint);
        }

        System.exit(0);
    }

    private static void paint(GalaxyGraphicRepresentation galaxyGraphicRepresentation, Galaxy galaxy, int millisToRepaint) throws InterruptedException {
        galaxyGraphicRepresentation.show(galaxy);
        Thread.sleep(millisToRepaint);
    }

    private static void inspect(int starId, Galaxy galaxy) {
        galaxy.listStar().stream().filter(star -> star.id() == starId).forEach(System.out::println);
    }


}

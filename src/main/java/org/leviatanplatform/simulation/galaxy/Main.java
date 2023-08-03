package org.leviatanplatform.simulation.galaxy;

import org.leviatanplatform.simulation.galaxy.engine.DynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.NewtonianDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.RelativisticDynamicsCalculator;
import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.util.Chronometer;
import org.leviatanplatform.simulation.galaxy.util.generator.GalaxyType;
import org.leviatanplatform.simulation.galaxy.util.generator.RandomGalaxyGenerator;
import org.leviatanplatform.simulation.galaxy.util.show.GalaxyGraphicRepresentation;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numberOfIterations = 10000;
        int numberOfStars = 1000;
        int millisToRepaint = 10;
        boolean relativistic = false;
        GalaxyType galaxyType = GalaxyType.NO_RESTRICTION;
        boolean hasBlackHole = false;
        double velocityFactor = 1e6;
        double secondsJump = 3 * RandomGalaxyGenerator.MONTH;

        simulateGalaxy(numberOfIterations, numberOfStars, millisToRepaint, relativistic, galaxyType, hasBlackHole,
                velocityFactor, secondsJump);
    }

    public static void simulateGalaxy(int numberOfIterations, int numberOfStars, int millisToRepaint,
                                      boolean relativistic, GalaxyType galaxyType, boolean hasBlackHole,
                                      double velocityFactor, double secondsJump) throws InterruptedException {

        Galaxy galaxy = RandomGalaxyGenerator.generateGalaxy(galaxyType, numberOfStars, velocityFactor);

        if (hasBlackHole) {
            RandomGalaxyGenerator.addBlackHole(galaxy);
        }

        DynamicsCalculator dynamicsCalculator = relativistic ? new RelativisticDynamicsCalculator() : new NewtonianDynamicsCalculator();

        GalaxyGraphicRepresentation galaxyGraphicRepresentation = new GalaxyGraphicRepresentation();
        Chronometer chronometer = new Chronometer();

        paint(galaxyGraphicRepresentation, galaxy, millisToRepaint);

        for (int i = 0 ; i < numberOfIterations; i++) {

            chronometer.tic();
            galaxy = dynamicsCalculator.moveTimeForward(galaxy, secondsJump);
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

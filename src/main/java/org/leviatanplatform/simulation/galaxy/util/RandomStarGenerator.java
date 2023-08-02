package org.leviatanplatform.simulation.galaxy.util;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStarGenerator {

    public static final double SOLAR_MASS = 1.989e+30;

    public static final double BLACK_HOLE_MASS = 10000 * SOLAR_MASS;
    public static final double TYPICAL_STAR_VELOCITY = 100000;

    public static final double LIGHT_YEAR = 9.461e+15;
    public static final double MILKY_WAY_RADIUS = 27000 * LIGHT_YEAR;

    public static final double DAY = 24 * 3600;
    public static final double YEAR = 365.25 * DAY;
    public static final double MILLENNIUM = 1000 * YEAR;


    public static Galaxy generateGalaxy(int numberOfStars) {
        return generateGalaxy(numberOfStars, SOLAR_MASS, MILKY_WAY_RADIUS, TYPICAL_STAR_VELOCITY * 1e6);
    }

    public static Galaxy generateGalaxy(int numberOfStars, double meanMass, double lambdaDistance, double lambdaVelocity) {

        List<Star> listStar = new ArrayList<>(numberOfStars);

        for (int i=0; i < numberOfStars; i++) {
            listStar.add(generate(i, meanMass, lambdaDistance, lambdaVelocity));
        }

        return new Galaxy(listStar);
    }

    public static Star generate(int id, double meanMass, double lambdaDistance, double lambdaVelocity) {

        Random random = new Random();

        double mass = meanMass * (random.nextDouble() + 0.5);

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance,
                random.nextGaussian() * lambdaDistance);

        Vector velocity = new Vector(random.nextGaussian() * lambdaVelocity, random.nextGaussian() * lambdaVelocity,
                random.nextGaussian() * lambdaVelocity);

        return new Star(id, mass, position, velocity);
    }

    public static Galaxy generateGalaxyFixedMassZ0(int numberOfStars) {
        return generateGalaxyFixedMassZ0(numberOfStars, SOLAR_MASS, MILKY_WAY_RADIUS, TYPICAL_STAR_VELOCITY * 1e6);
    }

    public static Galaxy generateGalaxyFixedMassZ0(int numberOfStars, double mass, double lambdaDistance, double lambdaVelocity) {

        List<Star> listStar = new ArrayList<>(numberOfStars);

        for (int i=0; i < numberOfStars; i++) {
            listStar.add(generateFixedMassZ0(i, mass, lambdaDistance, lambdaVelocity));
        }

        return new Galaxy(listStar);
    }

    public static Star generateFixedMassZ0(int id, double mass, double lambdaDistance, double lambdaVelocity) {

        Random random = new Random();

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance,
                0);

        Vector velocity = new Vector(random.nextGaussian() * lambdaVelocity, random.nextGaussian() * lambdaVelocity,
                0);

        return new Star(id, mass, position, velocity);
    }

    public static void addBlackHole(Galaxy galaxy) {
        galaxy.listStar().add(generateBlackHole());
    }

    private static Star generateBlackHole() {
        return generate(-1, BLACK_HOLE_MASS, 0, 0);
    }
}

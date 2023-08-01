package org.leviatanplatform.simulation.galaxy.util;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStarGenerator {

    public static final double SOLAR_MASS = 1.989e+30;
    public static final double TYPICAL_STAR_VELOCITY = 100000;

    public static final double LIGHT_YEAR = 9.461e+15;
    public static final double MILKY_WAY_RADIUS = 27000 * LIGHT_YEAR;

    public static final double YEAR = 3.154e+7;
    public static final double MILLENNIUM = 1000 * YEAR;


    public static Galaxy generateGalaxy(int numberOfStars) {
        return generateGalaxy(numberOfStars, SOLAR_MASS, MILKY_WAY_RADIUS, TYPICAL_STAR_VELOCITY);
    }

    public static Galaxy generateGalaxy(int numberOfStars, double meanMass, double lambdaDistance, double lambdaVelocity) {

        List<Star> listStar = new ArrayList<>(numberOfStars);

        for (int i=0; i < numberOfStars; i++) {
            listStar.add(generate(meanMass, lambdaDistance, lambdaVelocity));
        }

        return new Galaxy(listStar);
    }

    public static Star generate() {
        return generate(SOLAR_MASS, MILKY_WAY_RADIUS, TYPICAL_STAR_VELOCITY);
    }

    public static Star generate(double meanMass, double lambdaDistance, double lambdaVelocity) {

        Random random = new Random();

        double mass = meanMass * (random.nextDouble() + 0.5);

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance,
                random.nextGaussian() * lambdaDistance);

        Vector velocity = new Vector(random.nextGaussian() * lambdaVelocity, random.nextGaussian() * lambdaVelocity,
                random.nextGaussian() * lambdaVelocity);

        return new Star(mass, position, velocity);
    }
}

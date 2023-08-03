package org.leviatanplatform.simulation.galaxy.util.generator;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGalaxyGenerator {

    public static final double SOLAR_MASS = 1.989e+30;

    public static final double BLACK_HOLE_MASS = 10000 * SOLAR_MASS;
    public static final double TYPICAL_STAR_VELOCITY = 100000;

    public static final double LIGHT_YEAR = 9.461e+15;
    public static final double MILKY_WAY_RADIUS = 27000 * LIGHT_YEAR;

    public static final double DAY = 24 * 3600;
    public static final double MONTH = 30 * DAY;
    public static final double YEAR = 365.25 * DAY;
    public static final double MILLENNIUM = 1000 * YEAR;

    public static Galaxy generateGalaxy(int numberOfStars, double velocityFactor) {

        StarGenerator starGenerator = new RandomStarGenerator(SOLAR_MASS, MILKY_WAY_RADIUS,
                TYPICAL_STAR_VELOCITY * velocityFactor);
        return generateGalaxy(numberOfStars, starGenerator);
    }

    public static Galaxy generateGalaxyRotatory(int numberOfStars, double velocityFactor) {

        StarGenerator starGenerator = new RandomRotatoryStarGenerator(SOLAR_MASS, MILKY_WAY_RADIUS,
                TYPICAL_STAR_VELOCITY * velocityFactor);
        return generateGalaxy(numberOfStars, starGenerator);
    }

    public static Galaxy generateGalaxyFixedMassZ0(int numberOfStars, double velocityFactor) {

        StarGenerator starGenerator = new RandomFixedMassZ0StarGenerator(SOLAR_MASS, MILKY_WAY_RADIUS,
                TYPICAL_STAR_VELOCITY * velocityFactor);
        return generateGalaxy(numberOfStars, starGenerator);
    }

    public static Galaxy generateGalaxy(int numberOfStars, StarGenerator starGenerator) {

        List<Star> listStar = new ArrayList<>(numberOfStars);

        for (int i=0; i < numberOfStars; i++) {
            listStar.add(starGenerator.generate(i));
        }

        return new Galaxy(listStar);
    }


    public static void addBlackHole(Galaxy galaxy) {
        galaxy.listStar().add(generateBlackHole());
    }

    private static Star generateBlackHole() {

        StarGenerator starGenerator = new RandomStarGenerator(BLACK_HOLE_MASS, 0, 0);
        return starGenerator.generate(-1);
    }
}

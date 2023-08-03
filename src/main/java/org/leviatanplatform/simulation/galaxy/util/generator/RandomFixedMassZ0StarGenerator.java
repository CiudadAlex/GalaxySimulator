package org.leviatanplatform.simulation.galaxy.util.generator;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.Random;

public record RandomFixedMassZ0StarGenerator(double mass, double lambdaDistance, double lambdaVelocity) implements StarGenerator {

    @Override
    public Star generate(int id) {

        Random random = new Random();

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance, 0);
        Vector velocity = new Vector(random.nextGaussian() * lambdaVelocity, random.nextGaussian() * lambdaVelocity, 0);

        return new Star(id, mass, position, velocity);
    }
}

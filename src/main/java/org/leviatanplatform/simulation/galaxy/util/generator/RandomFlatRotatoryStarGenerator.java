package org.leviatanplatform.simulation.galaxy.util.generator;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.Random;

public record RandomFlatRotatoryStarGenerator(double meanMass, double lambdaDistance, double lambdaVelocity) implements StarGenerator {

    @Override
    public Star generate(int id) {

        Random random = new Random();

        double mass = meanMass * (random.nextDouble() + 0.5);

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance, 0);
        Vector velocity = findOrthogonalUnitary(random, position).multiply(random.nextGaussian() * lambdaVelocity);

        return new Star(id, mass, position, velocity);
    }

    private Vector findOrthogonalUnitary(Random random, Vector vector) {

        double x = random.nextGaussian();
        double z = 0;
        double y = -(x * vector.x()) / vector.y();
        return new Vector(x, y, z).normalize();
    }
}
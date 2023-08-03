package org.leviatanplatform.simulation.galaxy.util.generator;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

import java.util.Random;

public record RandomRotatoryStarGenerator(double meanMass, double lambdaDistance, double lambdaVelocity) implements StarGenerator {

    @Override
    public Star generate(int id) {

        Random random = new Random();

        double mass = meanMass * (random.nextDouble() + 0.5);

        Vector position = new Vector(random.nextGaussian() * lambdaDistance, random.nextGaussian() * lambdaDistance,
                random.nextGaussian() * lambdaDistance);
        Vector velocity = findOrthogonalUnitary(random, position).multiply(random.nextGaussian() * lambdaVelocity);

        return new Star(id, mass, position, velocity);
    }

    private Vector findOrthogonalUnitary(Random random, Vector vector) {

        double x = random.nextGaussian();
        double y = random.nextGaussian();
        double z = -(x * vector.x() + y * vector.y()) / vector.z();
        return new Vector(x, y, z).normalize();
    }
}
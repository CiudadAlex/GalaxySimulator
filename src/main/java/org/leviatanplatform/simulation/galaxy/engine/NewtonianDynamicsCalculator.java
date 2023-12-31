package org.leviatanplatform.simulation.galaxy.engine;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

public class NewtonianDynamicsCalculator extends AbstractDynamicsCalculator {

    @Override
    public Vector calculateAccelerationOfStarInStar(Star starAccelerated, Star star) {

        Vector positionStarAccelerated = starAccelerated.position();

        Vector starPosition = star.position();
        double r = calculateDistance(positionStarAccelerated, starPosition);

        if (r == 0) {
            return new Vector(0, 0, 0);
        }

        double accelerationNorm = - G * star.mass() / Math.pow(r, 2);
        return positionStarAccelerated.subtract(starPosition).multiply(accelerationNorm);
    }
}

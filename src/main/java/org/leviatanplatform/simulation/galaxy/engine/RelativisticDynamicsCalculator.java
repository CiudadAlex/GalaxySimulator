package org.leviatanplatform.simulation.galaxy.engine;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;

public class RelativisticDynamicsCalculator extends AbstractDynamicsCalculator {

    @Override
    public Vector calculateAccelerationOfStarInStar(Star starAccelerated, Star star) {

        Vector positionStarAccelerated = starAccelerated.position();
        Vector velocityStarAccelerated = starAccelerated.velocity() ;

        Vector starPosition = star.position();
        double r = calculateDistance(positionStarAccelerated, starPosition);

        if (r == 0) {
            return new Vector(0, 0, 0);
        }

        double velocityStarAccelerated2 = Math.pow(velocityStarAccelerated.norm(), 2);
        double c2 = Math.pow(C, 2);
        double relativisticFactor = 1 - velocityStarAccelerated2 / c2;

        double accelerationNorm = - G * relativisticFactor * star.mass() / Math.pow(r, 2);
        return positionStarAccelerated.subtract(starPosition).multiply(accelerationNorm);
    }
}

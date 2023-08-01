package org.leviatanplatform.simulation.galaxy.engine;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;
import org.leviatanplatform.simulation.galaxy.engine.model.Vector;
import org.leviatanplatform.simulation.galaxy.engine.model.Star;

import java.util.ArrayList;
import java.util.List;

public class NewtonianDynamicsCalculator implements DynamicsCalculator {

    private static final double G = 6.67e-11;

    public Galaxy moveTimeForward(Galaxy galaxy, double seconds) {

        List<Star> initialListStar = galaxy.listStar();
        List<Star> clonedListStar = new ArrayList<>(initialListStar);

        List<Star> finalListStar = initialListStar.stream().map(star -> calculateNewStar(star, clonedListStar, seconds)).toList();

        return new Galaxy(finalListStar);
    }

    private Star calculateNewStar(Star star, List<Star> listStar, double seconds) {

        Vector position = star.position();
        Vector velocity = star.velocity();
        Vector newPosition = position.add(velocity.multiply(seconds));
        Vector acceleration = calculateAccelerationOfListOfStarsInPosition(star.position(), listStar);
        Vector newVelocity = velocity.add(acceleration.multiply(seconds));
        return new Star(star.mass(), newPosition, newVelocity);
    }

    private Vector calculateAccelerationOfListOfStarsInPosition(Vector position, List<Star> listStar) {

        List<Vector> listAccelerations = listStar.stream().map(star -> calculateAccelerationOfStarInPosition(position, star)).toList();

        Vector totalAcceleration = Vector.zero();

        for (Vector acceleration : listAccelerations) {
            totalAcceleration = totalAcceleration.add(acceleration);
        }

        return totalAcceleration;
    }

    private Vector calculateAccelerationOfStarInPosition(Vector position, Star star) {

        Vector starPosition = star.position();
        double r = calculateDistance(position, starPosition);
        double accelerationNorm = G * star.mass() / Math.pow(r, 2);
        return position.subtract(starPosition).multiply(accelerationNorm);
    }

    private double calculateDistance(Vector p1, Vector p2) {
        double squaredDistance = Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2) + Math.pow(p1.z() - p2.z(), 2);
        return Math.sqrt(squaredDistance);
    }
}

package org.leviatanplatform.simulation.galaxy.engine.model;

public record Vector(double x, double y, double z) {

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y, z + other.z);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y, z - other.z);
    }

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar, z * scalar);
    }

    public double norm() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector normalize() {
        double norm = norm();
        return new Vector(x/norm, y/norm, z/norm);
    }

    public static Vector zero() {
        return new Vector(0, 0, 0);
    }
}


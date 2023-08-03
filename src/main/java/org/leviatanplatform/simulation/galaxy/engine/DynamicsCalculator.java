package org.leviatanplatform.simulation.galaxy.engine;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;

public interface DynamicsCalculator {

    double G = 6.67e-11;

    double C = 2.998e+8;

    Galaxy moveTimeForward(Galaxy galaxy, double seconds);
}

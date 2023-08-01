package org.leviatanplatform.simulation.galaxy.engine;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;

public interface DynamicsCalculator {

    Galaxy moveTimeForward(Galaxy galaxy, double seconds);
}

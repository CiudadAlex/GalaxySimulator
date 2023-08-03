package org.leviatanplatform.simulation.galaxy.util.generator;

import org.leviatanplatform.simulation.galaxy.engine.model.Star;

public interface StarGenerator {

    Star generate(int id);
}

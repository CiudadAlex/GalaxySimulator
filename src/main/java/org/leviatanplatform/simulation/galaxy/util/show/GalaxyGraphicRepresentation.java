package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;

import javax.swing.*;

public class GalaxyGraphicRepresentation {

    private JFrame frame;

    public void show(Galaxy galaxy) {

        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame("GALAXY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(GalaxyGraphicRepresentationUtils.createCanvas(galaxy));
        frame.setSize(1600, 900);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}

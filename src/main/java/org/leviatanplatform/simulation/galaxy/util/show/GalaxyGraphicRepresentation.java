package org.leviatanplatform.simulation.galaxy.util.show;

import org.leviatanplatform.simulation.galaxy.engine.model.Galaxy;

import javax.swing.*;
import java.awt.*;

public class GalaxyGraphicRepresentation {

    private JFrame frame;
    private JPanel canvas;
    private Galaxy galaxy;

    public void show(Galaxy galaxy) {

        this.galaxy = galaxy;

        if (frame == null) {
            frame = new JFrame("GALAXY");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 900);
            frame.setVisible(true);
            frame.setResizable(false);

            canvas = createCanvas();
            frame.add(canvas);
        }

        SwingUtilities.invokeLater(() -> {
            canvas.invalidate();
            canvas.validate();
            canvas.repaint();
        });
    }

    private JPanel createCanvas() {

        return new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                GalaxyGraphicRepresentationUtils.paintCurrentListOfStars(g, galaxy.listStar(), getWidth(), getHeight());
            }
        };
    }

}

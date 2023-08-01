package org.leviatanplatform.simulation.galaxy.util;

import java.util.Date;

public class Chronometer {

    private Date init;

    public void tic() {
        this.init = new Date();
    }

    public void toc() {
        Date now = new Date();
        System.out.println("Milliseconds: " + (now.getTime() - init.getTime()));
    }
}

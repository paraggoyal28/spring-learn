package org.example.Structural.Adapter.peg.adapters;

import org.example.Structural.Adapter.peg.round.RoundPeg;
import org.example.Structural.Adapter.peg.square.SquarePeg;

public class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        // Calculate a minimum circle radius, which can fit this peg.
        result = (Math.sqrt(Math.pow((peg.getWidth()/2), 2)*2));
        return result;
    }
}

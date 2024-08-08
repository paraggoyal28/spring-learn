package org.example;

public class T20MatchType implements MatchType {
    @Override
    public int noOfOvers() {
        return 20;
    }

    @Override
    public int maxOverCountPerBowler() {
        return 5;
    }
}

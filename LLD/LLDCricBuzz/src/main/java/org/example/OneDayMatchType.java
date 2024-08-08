package org.example;

public class OneDayMatchType implements MatchType{

    @Override
    public int noOfOvers() {
        return 50;
    }

    @Override
    public int maxOverCountPerBowler() {
        return 10;
    }
}

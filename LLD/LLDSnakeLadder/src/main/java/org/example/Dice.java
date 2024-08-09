package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int diceCount;
    int mn = 1;
    int mx = 6;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int totalSum = 0;
        int diceUsed = 0;

        while(diceUsed < diceCount) {
            totalSum += ThreadLocalRandom.current().nextInt(mn, mx + 1);
            diceUsed++;
        }

        return totalSum;
    }
}

package org.example.ScoreUpdater;

import org.example.Inning.BallDetails;

public class BattingScoreUpdater implements  ScoreUpdaterObserver {

    @Override
    public void update(BallDetails ballDetails) {
        int run = 0;

        switch(ballDetails.runType) {
            case ONE: run = 1;
            break;
            case TWO: run = 2;
            break;
            case THREE: run = 3;
            break;
            case FOUR: run = 4;
            ballDetails.playedBy.battingScoreCard.totalFours++;
            break;
            case SIX: run = 6;
            ballDetails.playedBy.battingScoreCard.totalSix++;
        }

        ballDetails.playedBy.battingScoreCard.totalRuns += run;

        ballDetails.playedBy.battingScoreCard.totalBallsPlayed++;

        if (ballDetails.wicket != null) {
            ballDetails.playedBy.battingScoreCard.wicketDetails = ballDetails.wicket;
        }
    }
}

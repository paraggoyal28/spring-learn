package org.example.ScoreUpdater;

import org.example.Inning.BallDetails;
import org.example.Inning.BallType;

public class BowlingScoreUpdater implements ScoreUpdaterObserver {
    @Override
    public void update(BallDetails ballDetails) {
        if (ballDetails.ballNumber == 6 && ballDetails.ballType == BallType.NORMAL) {
            ballDetails.bowledBy.bowlingScoreCard.totalOversCount++;
        }

        switch (ballDetails.runType) {
            case ONE: ballDetails.bowledBy.bowlingScoreCard.runsGiven += 1;
            break;
            case TWO: ballDetails.bowledBy.bowlingScoreCard.runsGiven += 2;
            break;
            case THREE: ballDetails.bowledBy.bowlingScoreCard.runsGiven += 3;
            break;
            case FOUR: ballDetails.bowledBy.bowlingScoreCard.runsGiven += 4;
            break;
            case SIX: ballDetails.bowledBy.bowlingScoreCard.runsGiven += 6;
        }

        if(ballDetails.wicket!=null) {
            ballDetails.bowledBy.bowlingScoreCard.wicketsTaken++;
        }

        if(ballDetails.ballType == BallType.NOBALL) {
            ballDetails.bowledBy.bowlingScoreCard.noBallCount++;
        }

        if(ballDetails.ballType == BallType.WIDEBALL) {
            ballDetails.bowledBy.bowlingScoreCard.wideBallCount++;
        }
    }
}

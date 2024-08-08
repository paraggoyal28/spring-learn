package org.example.ScoreUpdater;

import org.example.Inning.BallDetails;

public interface ScoreUpdaterObserver {
    void update(BallDetails ballDetails);
}

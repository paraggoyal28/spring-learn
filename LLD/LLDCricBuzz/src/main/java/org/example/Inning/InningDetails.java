package org.example.Inning;

import org.example.MatchType;
import org.example.Team.Player.PlayerDetails;
import org.example.Team.Team;

import java.util.ArrayList;
import java.util.List;

public class InningDetails {
    Team battingTeam;
    Team bowlingTeam;
    MatchType matchType;
    List<OverDetails> overs;

    public InningDetails(Team battingTeam, Team bowlingTeam, MatchType matchType) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.matchType = matchType;
        this.overs = new ArrayList<>();
    }

    public void start(int runsToWin) {
        // set batting players
        try {
            battingTeam.chooseNextBatsMan();
        } catch (Exception ignored) {

        }

        int noOfOvers = matchType.noOfOvers();
        for(int overNumber = 1; overNumber <= noOfOvers; ++overNumber) {

            // choose bowler
            bowlingTeam.chooseNextBowler(matchType.maxOverCountPerBowler());

            OverDetails over = new OverDetails(overNumber, bowlingTeam.getCurrentBowler());
            overs.add(over);
            try {
                boolean won = over.startOver(battingTeam, bowlingTeam, runsToWin);
                if(won) {
                    break;
                }
            } catch (Exception e) {
                break;
            }

            // swap striker and nonstriker
            PlayerDetails temp = battingTeam.getStriker();
            battingTeam.setStriker(battingTeam.getNonStriker());
            battingTeam.setNonStriker(temp);


        }
    }
    public int getTotalRuns(){
        return battingTeam.getTotalRuns();
    }

}

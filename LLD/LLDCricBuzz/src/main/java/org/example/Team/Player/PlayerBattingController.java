package org.example.Team.Player;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerBattingController {
    Queue<PlayerDetails> yetToPlay;
    PlayerDetails striker;
    PlayerDetails nonStriker;

    public PlayerBattingController(Queue<PlayerDetails> playing11) {
        this.yetToPlay = new LinkedList<>();
        this.yetToPlay.addAll(playing11);
    }

    public void getNextPlayer() throws Exception {
        if(yetToPlay.isEmpty()) {
            throw new Exception();
        }
        if(striker == null) {
            striker = yetToPlay.poll();
        }
        if(nonStriker == null) {
            nonStriker = yetToPlay.poll();
        }
    }

    public PlayerDetails getStriker() {
        return striker;
    }

    public void setStriker(PlayerDetails striker) {
        this.striker = striker;
    }

    public PlayerDetails getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(PlayerDetails nonStriker) {
        this.nonStriker = nonStriker;
    }
}

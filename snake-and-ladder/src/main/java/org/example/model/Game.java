package org.example.model;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

@Getter
public class Game {
    private int numberOfSnakes;
    private int numberOfLadders;

    private Queue<Player> players;
    private HashMap<Integer, Snake> snakes;
    private HashMap<Integer, Ladder> ladders;

    private Board board;
    private Dice dice;

    public Game(int numberOfLadders, int numberOfSnakes,
                int boardSize) {
        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.players = new ArrayDeque<>();
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        board = new Board(boardSize);
        dice = new Dice(1, 6, 2);
        initBoard();
    }

    private void initBoard() {
        Random random = new Random();
        // generating snakes
        for(int i = 0; i < numberOfSnakes; ++i) {
            while(true) {
                int snakeStart = random.nextInt(board.getStart(), board.getSize());
                int snakeEnd = random.nextInt(board.getStart(), board.getSize());
                if(snakeEnd >= snakeStart)
                    continue;
                if(!snakes.containsKey(snakeStart)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.put(snakeStart, snake);
                    break;
                }
            }
        }
        // generating ladders
        for(int i = 0; i < numberOfLadders; ++i) {
            while(true) {
                int ladderStart = random.nextInt(board.getStart(), board.getSize());
                int ladderEnd = random.nextInt(board.getStart(), board.getSize());
                if(ladderEnd <= ladderStart)
                    continue;
                if(!snakes.containsKey(ladderStart) && !snakes.containsKey(ladderEnd)
                    && !ladders.containsKey(ladderStart)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.put(ladderStart, ladder);
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while (true) {
            Player player = players.poll();
            int val = dice.roll();
            assert player != null;
            int newPosition = player.getPosition() + val;
            if(newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.offer(player);
            } else {
                player.setPosition(getNewPosition(player, newPosition));
                if(player.getPosition() == board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    players.offer(player);
                }
            }
            if(players.size() < 2) {
                break;
            }
        }
    }

    private int getNewPosition(Player player, int newPosition) {
        if(snakes.containsKey(newPosition)) {
            int snakeTail = snakes.get(newPosition).getTail();
            System.out.println("Player " + player.getName() + " got bitten by snake ending at " + snakeTail);
            return snakeTail;
        }

        if(ladders.containsKey(newPosition)) {
            int ladderEnd = ladders.get(newPosition).getEnd();
            System.out.println("Player " + player.getName() + " climbed a ladder ending at " + ladderEnd);
            return ladderEnd;
        }
        return newPosition;
    }
}

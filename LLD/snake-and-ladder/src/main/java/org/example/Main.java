package org.example;

import org.example.model.Game;
import org.example.model.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter board size:");
        int boardSize = scanner.nextInt();
        System.out.println("Enter number of players:");
        int numberOfPlayers = scanner.nextInt();
        System.out.println("Enter number of snakes:");
        int numSnakes = scanner.nextInt();
        System.out.println("Enter number of ladders:");
        int numLadders = scanner.nextInt();

        Game game = new Game(numLadders, numSnakes, boardSize);
        for(int i = 0; i < numberOfPlayers; ++i) {
            System.out.println("Enter player name:");
            String pName = scanner.next();
            Player player = new Player(pName);
            game.addPlayer(player);
        }
        game.playGame();
    }
}
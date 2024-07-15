package org.example;

import org.example.Model.*;

import java.util.*;

public class Game {
    Deque<Player> players;
    Board gameBoard;
    HashMap<Player, HashMap<Integer, Integer>> markedRow;
    HashMap<Player, HashMap<Integer, Integer>> markedColumn;
    HashMap<Player, Integer> markedDiagonal;
    HashMap<Player, Integer> markedAntiDiagonal;


    public void initializeGame() {
        // creating 2 players

        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece);

        PlayingPieceO noughtsPiece = new PlayingPieceO();
        Player player2 = new Player("Player2", noughtsPiece);

        players.add(player1);
        players.add(player2);

        // initialize maps
        markedRow = new HashMap<>();
        markedColumn = new HashMap<>();
        markedDiagonal = new HashMap<>();
        markedAntiDiagonal = new HashMap<>();


        markedRow.put(player1, new HashMap<>());
        markedRow.put(player2, new HashMap<>());

        markedColumn.put(player1, new HashMap<>());
        markedColumn.put(player2, new HashMap<>());

        // initializeBoard
        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;

        while(noWinner) {

            Player currPlayer = players.removeFirst();

            gameBoard.printBoard();

            List<List<Integer>> freeSpaces = gameBoard.getFreeCells();
            if(freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.print("Player: " + currPlayer.name + " Enter row, column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);


            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputColumn, currPlayer.getPlayingPiece());

            if(!pieceAddedSuccessfully) {
                System.out.println("Incorrect position chosen, try again.");
                players.addFirst(currPlayer);
                continue;
            }

            players.addLast(currPlayer);

            boolean winner = isThereWinner(currPlayer, inputRow, inputColumn);

            if(winner) {
                return currPlayer.getName();
            }
        }
        return "Tie";
    }

    public boolean isThereWinner(Player currPlayer, int row, int column) {
        int rowCount = markedRow.get(currPlayer).getOrDefault(row, 0);
        if(rowCount == (gameBoard.size - 1)) return true;
        markedRow.get(currPlayer).put(row, rowCount + 1);

        int columnCount = markedColumn.get(currPlayer).getOrDefault(column, 0);
        if(columnCount == (gameBoard.size - 1)) return true;
        markedColumn.get(currPlayer).put(column, columnCount + 1);

        if(row == column) {
            int diagonalCount = markedDiagonal.getOrDefault(currPlayer, 0);
            if(diagonalCount == (gameBoard.size - 1)) return true;
            markedDiagonal.put(currPlayer, diagonalCount + 1);
        }

        if(row == gameBoard.size - 1 - column) {
            int antiDiagonalCount = markedAntiDiagonal.getOrDefault(currPlayer, 0);
            if(antiDiagonalCount == (gameBoard.size - 1)) return true;
            markedAntiDiagonal.put(currPlayer, antiDiagonalCount + 1);
        }
        return false;
    }
}

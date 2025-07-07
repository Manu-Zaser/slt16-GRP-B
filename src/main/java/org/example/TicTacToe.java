package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private final Board board = new Board();
    private final Player player1 = new Player('X');
    private final Player player2 = new Player('O');
    private Player currentPlayer = player1;

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) { // Äußere Schleife für mehrere Spiele
                runGameLoop(scanner);

                System.out.print("Nochmal spielen? (ja/nein): ");
                String response = scanner.next();
                if (!response.equalsIgnoreCase("ja")) {
                    System.out.println("Danke fürs Spielen!");
                    break;
                }
                resetGame();
            }
        }
    }

    private void runGameLoop(Scanner scanner) {
        boolean gameEnded = false;
        while (!gameEnded) {
            System.out.println(board.getBoardAsString());
            System.out.println("Aktueller Spieler: " + currentPlayer.getMarker());

            int row, col;
            while (true) {
                System.out.print("Gib die Zeile ein (0-2): ");
                row = scanner.nextInt();
                System.out.print("Gib die Spalte ein (0-2): ");
                col = scanner.nextInt();

                if (board.isCellEmpty(row, col)) {
                    board.place(row, col, currentPlayer.getMarker());
                    break;
                } else {
                    System.out.println("Ungültiger Zug! Versuche es erneut.");
                }
            }

            if (board.checkWin(currentPlayer.getMarker())) {
                System.out.println(board.getBoardAsString());
                System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                gameEnded = true;
            } else if (board.isFull()) {
                System.out.println(board.getBoardAsString());
                System.out.println("Das Spiel ist ein Unentschieden!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }

    private void resetGame() {
        board.clear();
        currentPlayer = player1;
    }
}
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
            boolean gameEnded = false;
            while (!gameEnded) {
                System.out.println(board.getBoardAsString());
                System.out.println("Aktueller Spieler: " + currentPlayer.getMarker());

                int row, col;
                while (true) {
                    row = readCoordinate("Zeile", scanner);
                    col = readCoordinate("Spalte", scanner);
                    if (board.isCellEmpty(row, col)) {
                        board.place(row, col, currentPlayer.getMarker());
                        break;
                    } else {
                        System.out.println("Dieses Feld ist belegt. Bitte wähle ein anderes.");
                    }
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;

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
    }

    private int readCoordinate(String coordinateName, Scanner scanner) {
        int coordinate;
        while (true) {
            System.out.print("Gib die " + coordinateName + " ein (0-2): ");
            try {
                coordinate = scanner.nextInt();
                if (coordinate >= 0 && coordinate <= 2) {
                    return coordinate;
                } else {
                    System.out.println("Fehler: Bitte eine Zahl zwischen 0 und 2 eingeben.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Das ist keine gültige Zahl. Bitte versuche es erneut.");
                scanner.next();
            }
        }
    }
}
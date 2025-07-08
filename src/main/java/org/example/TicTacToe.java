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

            gameLoop:
            while (true) {
                runGameLoop(scanner);

                while (true) {
                    System.out.print("Nochmal spielen? (ja/nein): ");
                    String response = scanner.next();

                    if (response.equalsIgnoreCase("ja")) {
                        resetGame();
                        break;
                    } else if (response.equalsIgnoreCase("nein")) {
                        System.out.println("Danke fürs Spielen!");
                        break gameLoop;
                    } else {
                        System.out.println("Ungültige Eingabe! Bitte 'ja' oder 'nein' eingeben.");
                    }
                }
            }
        }
    }

    private void runGameLoop(Scanner scanner) {
        boolean gameEnded = false;
        while (!gameEnded) {
            displayBoardState();
            handlePlayerTurn(scanner);
            gameEnded = checkGameStatus();
        }
    }

    private void displayBoardState() {
        System.out.println(board.getBoardAsString());
        System.out.println("Aktueller Spieler: " + currentPlayer.getMarker());
    }

    private void handlePlayerTurn(Scanner scanner) {
        while (true) {
            int row = readCoordinate("Zeile", scanner);
            int col = readCoordinate("Spalte", scanner);
            if (board.isCellEmpty(row, col)) {
                board.place(row, col, currentPlayer.getMarker());
                return;
            } else {
                System.out.println("Dieses Feld ist bereits belegt. Bitte wähle ein anderes.");
            }
        }
    }

    private boolean checkGameStatus() {
        if (board.checkWin(currentPlayer.getMarker())) {
            System.out.println(board.getBoardAsString());
            System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
            return true;
        }
        if (board.isFull()) {
            System.out.println(board.getBoardAsString());
            System.out.println("Das Spiel ist ein Unentschieden!");
            return true;
        }
        switchCurrentPlayer();
        return false;
    }

    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
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

    private void resetGame() {
        board.clear();
        currentPlayer = player1;
    }
}
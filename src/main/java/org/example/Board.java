package org.example;

public class Board {
    private final char[][] cells;
    private static final int SIZE = 3;

    public Board() {
        cells = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    // Gibt den Zustand des Bretts als String für die Anzeige zurück
    public String getBoardAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("▁▁▁▁▁▁▁\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("|");
            for (int j = 0; j < SIZE; j++) {
                sb.append(cells[i][j]).append("|");
            }
            sb.append("\n");
        }
        sb.append("▔▔▔▔▔▔▔\n");
        return sb.toString();
    }
}
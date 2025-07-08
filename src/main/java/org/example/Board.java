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
        sb.append("-------\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("|");
            for (int j = 0; j < SIZE; j++) {
                sb.append(cells[i][j]).append("|");
            }
            sb.append("\n");
        }
        sb.append("-------\n");
        return sb.toString();
    }

    public boolean isCellEmpty(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3 && cells[x][y] == ' ';
    }
    public void place(int x, int y, char marker) {
        if (isCellEmpty(x, y)) {
            cells[x][y] = marker;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public boolean checkWin(char marker) {
        // Reihen und Spalten
        for (int i = 0; i < 3; i++) {
            if ((cells[i][0] == marker && cells[i][1] == marker && cells[i][2] == marker) ||
                    (cells[0][i] == marker && cells[1][i] == marker && cells[2][i] == marker)) {
                return true;
            }
        }
        // Diagonalen
        return (cells[0][0] == marker && cells[1][1] == marker && cells[2][2] == marker) ||
                (cells[0][2] == marker && cells[1][1] == marker && cells[2][0] == marker);
    }

    public final void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

}
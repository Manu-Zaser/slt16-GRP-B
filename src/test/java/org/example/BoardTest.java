package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = new Board();

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void newBoardShouldBeEmptyAndRenderCorrectly() {
        // Arrange

        String expected =
                "\n" +
                        "-------\n" +
                        "| | | |\n" +
                        "| | | |\n" +
                        "| | | |\n" +
                        "-------\n";
        String actual = board.getBoardAsString();


        assertEquals(expected, actual);
    }

    @Test
    void isCellEmpty_ShouldReturnTrue_ForEmptyCell() {
        // Positiver Test 1
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    void isCellEmpty_ShouldReturnTrue_ForAnotherEmptyCell() {
        // Positiver Test 2
        assertTrue(board.isCellEmpty(2, 2));
    }

    @Test
    void isCellEmpty_ShouldReturnFalse_ForOccupiedCell() {
        // Negativer Test 1
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void isCellEmpty_ShouldReturnFalse_ForOutOfBounds() {
        // Negativer Test 2
        assertFalse(board.isCellEmpty(3, 0), "Row out of bounds should be false");
        assertFalse(board.isCellEmpty(0, -1), "Column out of bounds should be false");
    }

    // Tests für place()
    @Test
    void place_ShouldMarkCellCorrectly_WhenCellIsEmpty() {
        // Positiver Test 1
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void place_ShouldMarkAnotherCellCorrectly_WhenCellIsEmpty() {
        // Positiver Test 2
        board.place(2, 1, 'O');
        assertFalse(board.isCellEmpty(2, 1));
    }

    @Test
    void place_ShouldNotChangeMarker_WhenCellIsOccupied() {
        // Negativer Test 1
        board.place(0, 0, 'X');
        String boardStateBefore = board.getBoardAsString();

        board.place(0, 0, 'O'); // Versuch zu überschreiben
        String boardStateAfter = board.getBoardAsString();

        assertEquals(boardStateBefore, boardStateAfter, "Board state should not change when placing on an occupied cell.");
    }

    @Test
    void place_ShouldDoNothing_WhenCoordinatesAreOutOfBounds() {
        // Negativer Test 2
        String boardStateBefore = board.getBoardAsString();
        board.place(3, 3, 'X');
        String boardStateAfter = board.getBoardAsString();

        assertEquals(boardStateBefore, boardStateAfter, "Board state should not change when placing out of bounds.");
    }

    @Test
    void checkWinShouldReturnTrueForHorizontalWin() {
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        board.place(0, 2, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void checkWinShouldReturnTrueForVerticalWin() {
        board.place(0, 1, 'O');
        board.place(1, 1, 'O');
        board.place(2, 1, 'O');
        assertTrue(board.checkWin('O'));
    }

    @Test
    void checkWinShouldReturnTrueForDiagonalWin() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'X');
        board.place(2, 2, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void checkWinShouldReturnFalseWhenNoWinner() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        assertFalse(board.checkWin('X'));
        assertFalse(board.checkWin('O'));
    }

    @Test
    void isFullShouldReturnTrueWhenBoardIsFull() {
        board.place(0,0,'X'); board.place(0,1,'O'); board.place(0,2,'X');
        board.place(1,0,'O'); board.place(1,1,'X'); board.place(1,2,'O');
        board.place(2,0,'X'); board.place(2,1,'O'); board.place(2,2,'X');
        assertTrue(board.isFull());
    }

    @Test
    void isFullShouldReturnFalseWhenBoardIsNotFull() {
        board.place(0,0,'X');
        assertFalse(board.isFull());
    }

    @Test
    void clear_ShouldResetBoard_FromPartialFill() {
        // Positiver Test 1
        // Arrange
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');

        // Act
        board.clear();

        // Assert
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
    }

    @Test
    void clear_ShouldResetBoard_FromFullFill() {
        // Positiver Test 2
        // Arrange
        Board board = new Board();
        board.place(0,0,'X'); board.place(0,1,'O'); board.place(0,2,'X');
        board.place(1,0,'O'); board.place(1,1,'X'); board.place(1,2,'O');
        board.place(2,0,'X'); board.place(2,1,'O'); board.place(2,2,'X');

        // Act
        board.clear();

        // Assert
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
        assertTrue(board.isCellEmpty(2, 2));
    }

}
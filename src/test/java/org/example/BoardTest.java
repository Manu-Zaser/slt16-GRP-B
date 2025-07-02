package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void newBoardShouldBeEmptyAndRenderCorrectly() {
        // Arrange
        Board board = new Board();
        String expected =
                "\n" +
                        "▁▁▁▁▁▁▁\n" +
                        "| | | |\n" +
                        "| | | |\n" +
                        "| | | |\n" +
                        "▔▔▔▔▔▔▔\n";

        // Act
        String actual = board.getBoardAsString();

        // Assert
        assertEquals(expected, actual);
    }
}
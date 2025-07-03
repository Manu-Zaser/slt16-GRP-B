package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PlayerTest {
    @Test
    void playerXShouldHaveCorrectMarker() {

        assertEquals('X', new Player('X').getMarker());
    }

    @Test
    void playerOShouldHaveCorrectMarker() {

        assertEquals('O', new Player('O').getMarker());
    }
}
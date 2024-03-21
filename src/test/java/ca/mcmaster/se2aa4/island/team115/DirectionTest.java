package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    public void testLeftDirFromNorth() {
        assertEquals(Direction.W, Direction.N.leftDir());
    }

    @Test
    public void testLeftDirFromWest() {
        assertEquals(Direction.S, Direction.W.leftDir());
    }

    @Test
    public void testLeftDirFromSouth() {
        assertEquals(Direction.E, Direction.S.leftDir());
    }

    @Test
    public void testLeftDirFromEast() {
        assertEquals(Direction.N, Direction.E.leftDir());
    }

    @Test
    public void testRightDirFromNorth() {
        assertEquals(Direction.E, Direction.N.rightDir());
    }

    @Test
    public void testRightDirFromWest() {
        assertEquals(Direction.N, Direction.W.rightDir());
    }

    @Test
    public void testRightDirFromSouth() {
        assertEquals(Direction.W, Direction.S.rightDir());
    }

    @Test
    public void testRightDirFromEast() {
        assertEquals(Direction.S, Direction.E.rightDir());
    }
}

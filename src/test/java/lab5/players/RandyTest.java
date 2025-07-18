package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import org.junit.jupiter.api.Test;
import lab5.game.Row;
import lab5.game.Col;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandyTest {

    @Test
    void testReturnsNullIfNoEmptyCells() {
        Board board = new Board() {
            @Override
            public List<Position> getEmptyCells() {
                return Collections.emptyList();
            }
        };

        Randy randy = new Randy("Randy");
        assertNull(randy.pickNextMove(board));
    }

    @Test
    void testReturnsOnlyAvailableMove() {
        Position p = new Position(Row.Middle, Col.Middle);
        Board board = new Board() {
            @Override
            public List<Position> getEmptyCells() {
                return Collections.singletonList(p);
            }
        };

        Randy randy = new Randy("Randy");
        assertEquals(p, randy.pickNextMove(board));
    }

    @Test
    void testReturnsOneOfAvailableMoves() {
        List<Position> available = Arrays.asList(
                new Position(Row.Top, Col.Left),
                new Position(Row.Bottom, Col.Right)
        );

        Board board = new Board() {
            @Override
            public List<Position> getEmptyCells() {
                return available;
            }
        };

        Randy randy = new Randy("Randy");
        Position move = randy.pickNextMove(board);
        assertTrue(available.contains(move));
    }
}
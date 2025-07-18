package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {

    @Test
    void testOmolaWinningMove() {
        Board board = new Board("XX-\nO--\n-O-");
        Omola omola = new Omola("Omola");
        Position pickedMove =  omola.pickNextMove(board);
        assertEquals(new Position(Row.Top, Col.Right), pickedMove, "Omola should've picked Top-Right Cell");
    }

    @Test
    void testOmolaBlockingOpponent() {
        Board board = new Board("-XO\nX-O\nX--");
        Omola omola = new Omola("Omola");
        Position pickedMove =  omola.pickNextMove(board);
        assertEquals(new Position(Row.Bottom, Col.Right), pickedMove, "Omola should've picked Bottom-Right Cell");
    }

    @Test
    void testOmolaReturnNull() {
        Board board = new Board("XOX\nOXX\nOXO");
        Omola omola = new Omola("Omola");
        Position pickedMove =  omola.pickNextMove(board);
        assertNull(pickedMove, "Omola should've returned null");
    }
}
package lab4.game;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {


    private Board board;


    private final Position topLeft = new Position(Row.Top, Col.Left);
    private final Position topMiddle = new Position(Row.Top, Col.Middle);
    private final Position topRight = new Position(Row.Top, Col.Right);

    private final Position middleLeft = new Position(Row.Middle, Col.Left);
    private final Position middleMiddle = new Position(Row.Middle, Col.Middle);
    private final Position middleRight = new Position(Row.Middle, Col.Right);

    private final Position bottomLeft = new Position(Row.Bottom, Col.Left);
    private final Position bottomMiddle = new Position(Row.Bottom, Col.Middle);
    private final Position bottomRight = new Position(Row.Bottom, Col.Right);

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @org.junit.jupiter.api.Test

    public void testInitialBoardState(){
        assertEquals(Board.Status.InProgress, board.getStatus());
        assertFalse(board.isFull());

    }

    @org.junit.jupiter.api.Test

    public void testPlacingTokens(){

        board.placeO(topLeft);
        assertTrue(board.isOccupiedAt(topLeft));

        board.placeX(bottomRight);
        assertTrue(board.isOccupiedAt(bottomRight));

    }

    @org.junit.jupiter.api.Test
    public void testRowWin(){
        board.placeX(topLeft);
        board.placeX(topMiddle);
        board.placeX(topRight);
        assertEquals(Board.Status.XWins, board.getStatus());

    }

    @org.junit.jupiter.api.Test
    public void testColWin(){
        board.placeX(topLeft);
        board.placeX(middleLeft);
        board.placeX(bottomLeft);
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @org.junit.jupiter.api.Test
    public void testDiagonalWin() {
        board.placeX(topLeft);
        board.placeX(middleMiddle);
        board.placeX(bottomRight);
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @org.junit.jupiter.api.Test
    public void testAntiDiagonalWin() {
        board.placeX(topRight);
        board.placeX(middleMiddle);
        board.placeX(bottomLeft);
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @org.junit.jupiter.api.Test

    public void testDrawWithBoardFull(){

        board.placeX(topLeft);
        board.placeO(topMiddle);
        board.placeX(topRight);
        board.placeX(middleLeft);
        board.placeO(middleMiddle);
        board.placeX(middleRight);
        board.placeO(bottomLeft);
        board.placeX(bottomMiddle);
        board.placeO(bottomRight);

        assertTrue(board.isFull());
        assertEquals(Board.Status.Draw, board.getStatus());

    }

    @org.junit.jupiter.api.Test

    public void testBoardLayout() {
        board.placeX(topLeft);
        board.placeX(middleMiddle);
        board.placeX(bottomRight);

        String expected = """
                X..
                .X.
                ..X""";
        assertEquals(expected, board.toString());
    }



}

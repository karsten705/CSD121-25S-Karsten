package lab5.players;

import lab5.game.Board;
import lab5.game.PlayerToken;
import lab5.game.Position;

import java.util.List;

public class Omola extends Player{


    public Omola(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board currentBoard) {
        List<Position> emptyCells = currentBoard.getEmptyCells();

        if(emptyCells.isEmpty()) {
            return null;
        }

        PlayerToken myToken = currentBoard.getNextTurnToken();
        PlayerToken opponentToken = (myToken == PlayerToken.X) ? PlayerToken.O: PlayerToken.X;

        for(Position pos: emptyCells) {
            Board newBoard = new Board(currentBoard);
            newBoard.place(pos, myToken);
            if(newBoard.getWinner() == myToken) {
                return pos;
            }
        }

        for(Position pos: emptyCells) {
            Board newBoard = new Board(currentBoard);
            newBoard.place(pos, opponentToken);
            if(newBoard.getWinner() == opponentToken) {
                return pos;
            }
        }

        return emptyCells.getFirst();
    }
}

package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

public class Randy extends Player{


    public Randy(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board currentBoard) {
        List<Position> emptyCells = currentBoard.getEmptyCells();
        Random rand = new Random();

        if(emptyCells.isEmpty()) {
            return null;
        }

        Position[] preferredOrder = {
                new Position(Row.Top, Col.Left),
                new Position(Row.Top, Col.Middle),
                new Position(Row.Top, Col.Right),
                new Position(Row.Middle, Col.Left),
                new Position(Row.Middle, Col.Middle),
                new Position(Row.Middle, Col.Right),
                new Position(Row.Bottom, Col.Left),
                new Position(Row.Bottom, Col.Middle),
                new Position(Row.Bottom, Col.Right),
            };

        // Filter the preferred positions that are also empty
        List<Position> preferredAvailable = new java.util.ArrayList<>();
        for (Position pos : preferredOrder) {
            if (emptyCells.contains(pos)) {
                preferredAvailable.add(pos);
            }
        }

        // Return a random one among the preferred and available
        return preferredAvailable.get(rand.nextInt(preferredAvailable.size()));
    }
}

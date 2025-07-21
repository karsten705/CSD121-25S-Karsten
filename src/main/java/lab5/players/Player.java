package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.ui.Console;

/**
 * Represents a player in the game.
 *
 * TODO: Make this an abstract class with various subclasses for different types of players
 */
public abstract class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    /**
     * Prompts the player to pick their next move.
     * @param currentBoard The current state of the game board
     * @return The position on the board where the player wants to place their token
     *
     * TODO: Make this an abstract method in an abstract class,
     *          and use this implementation in a HumanPlayer subclass
     */
    public abstract Position pickNextMove(Board currentBoard);
//    public abstract Position pickNextMove(Board currentBoard) {
//        while (true) {
//            var move = Console.promptForPosition(getName() + " pick your next move: ");
//            if (currentBoard.isEmptyAt(move)) {
//                return move;
//            } else {
//                Console.printAlert("That position is already taken.");
//            }
//        }
//    }
}

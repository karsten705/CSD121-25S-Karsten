package Lab3;

import Lab3.game.Board;
import Lab3.game.Player;
import Lab3.game.Position;
import Lab3.ui.Console;

import java.util.Scanner;

/**
 * Main class to run the Tic Tac Toe game.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        int playerChoice;

        boolean playAgain = true; // boolean to end or play again

        while (playAgain) {
            Board board = new Board();
            Player currentPlayer = null;
            System.out.println("Tic Tac Toe Time!");
            System.out.println("1 for X, 2 for O!  ");
            playerChoice = scanner.nextInt();

            if (playerChoice == 1) {
                currentPlayer = Player.X;
            }
            else if (playerChoice == 2) {
                currentPlayer = Player.O;
            }
            else {
                System.out.println("Invalid Input!");
            }
            // Main game loop
            while (true) {
                board.printBoard();
                System.out.println("Player " + currentPlayer.getSymbol() + ", it's your turn.");
                Position move = console.promptForPosition();

                if (board.placeMove(move, currentPlayer)) {
                    char winner = board.checkWinner();
                    if (winner == currentPlayer.getSymbol()) {
                        board.printBoard();
                        console.showWinner(currentPlayer);
                        break;
                    } else if (board.checkDraw()) {
                        board.printBoard();
                        console.showDraw();
                        break;
                    }
                    currentPlayer = currentPlayer.next(); // Switch player
                } else {
                    System.out.println("That spot is already taken. Try again.");
                }
            }

            // Ask user to play again
            System.out.println("Play again? Enter 1 for Yes or 0 for No:");
            int input = scanner.nextInt();
            scanner.nextLine();
            playAgain = (input == 1);
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}

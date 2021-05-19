package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.dice.Dice;
import com.assignment.game.dice.DiceType;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DiceFactory;
import com.assignment.game.utils.DisplayUtil;

import java.util.Scanner;

public class PlaySnakeLadderGame {
    public static void main(String... args) {
        Scanner inputObj = new Scanner(System.in);

        DisplayUtil.displayMessage("Enter number of rows: ");
        int row = getIntegerInput(inputObj);
        DisplayUtil.displayMessage("Enter number of columns: ");
        int column = getIntegerInput(inputObj);

        // Create the board for the game.
        GameBoard board = new GameBoard(row, column);
        DisplayUtil.displayMessage("Game board created with " + row + " rows and " + column + " columns");

        addBoardElements(inputObj, board, true,
                "Add Snakes in format start1-end1, start2-end2, ....",
                "Constraint: start should be an integer which is greater than corresponding end",
                "Constraint: start cannot be the last tile for any snake");

        addBoardElements(inputObj, board, false,
                "Add Ladders in format start1-end1, start2-end2, ....",
                "Constraint: start should be an integer which is lesser than corresponding end");

        // Choose the dice type
        DisplayUtil.displayMessage("Enter " + DiceType.NORMAL.toString() + " to play with normal dice or "
                + DiceType.CROOKED.toString() + " to play with crooked dice");
        Dice dice = null;
        while (dice == null) {
            String type = inputObj.nextLine();
            dice = getDiceInstance(type);
        }

        // Start playing the game
        DisplayUtil.displayMessage("Starting the game, you will be provided 10 moves. Let's see if you can win by reaching the end.");
        DisplayUtil.displayMessage("Currently you are at " + (board.getCurrentPos() + 1) + " position");
        int moves;
        for (moves = 0; moves < 10; ++moves) {
            DisplayUtil.displayMessage("Press <enter> to roll the dice");
            inputObj.nextLine();
            final int diceRoll = dice.roll();

            board.play(diceRoll);
            DisplayUtil.displayMessage("Your roll was " + diceRoll + " and went at " + (board.getCurrentPos() + 1) + " position");

            // If won break from loop
            if (board.hasWon())
                break;
        }

        String resultBanner = "You have " + (board.hasWon() ? "won" : "lost") + " the game in " + (moves + 1) + " moves.";
        DisplayUtil.displayMessage(resultBanner);
    }

    private static Dice getDiceInstance(final String type) {
        try {
            return DiceFactory.getDiceOfType(type);
        } catch (IllegalArgumentException e) {
            DisplayUtil.displayMessage("Invalid option entered, please enter a valid choice ["
                    + DiceType.NORMAL.toString()
                    + " or "
                    + DiceType.CROOKED.toString() + "]");
            return null;
        }
    }

    // Take input from users related to snake (start and end) and add them to board.
    private static void addBoardElements(final Scanner inputObj,
                                         final GameBoard board,
                                         final boolean isSnake,
                                         final String... banners) {
        for (String banner: banners)
            DisplayUtil.displayMessage(banner);

        final String elements = inputObj.nextLine();
        for (String elementConfig: elements.split(",")) {
            elementConfig = elementConfig.trim();
            String[] elementStartEnd = elementConfig.split("-");
            if (elementStartEnd.length == 2) {
                try {
                    if (isSnake) {
                        board.addSnake(getPositiveIntegerFromString(elementStartEnd[0]),
                                getPositiveIntegerFromString(elementStartEnd[1]));
                    } else {
                        board.addLadder(getPositiveIntegerFromString(elementStartEnd[0]),
                                getPositiveIntegerFromString(elementStartEnd[1]));
                    }
                } catch (InvalidSnakeConfigException | InvalidLadderConfigException | NumberFormatException e) {
                    DisplayUtil.displayMessage(e.getMessage());
                    addBoardElements(inputObj, board, isSnake, banners);
                } catch (ElementExistsException e) {
                    DisplayUtil.displayMessage(e.getMessage());
                }
            } else {
                DisplayUtil.displayMessage("Invalid configuration entered. Please try again");
                addBoardElements(inputObj, board, isSnake, banners);
            }
        }
    }

    private static int getIntegerInput(final Scanner inputObj) {
        String input = inputObj.nextLine();
        try {
            return getPositiveIntegerFromString(input);
        } catch (NumberFormatException ex) {
            DisplayUtil.displayMessage("This is not a valid positive number, please enter a valid number");
            return getIntegerInput(inputObj);
        }
    }

    private static int getPositiveIntegerFromString(final String input) throws NumberFormatException {
        int x = Integer.parseInt(input.trim());
        if (x < 0)
            throw new NumberFormatException("Negative number is meaningless");
        return x;
    }
}

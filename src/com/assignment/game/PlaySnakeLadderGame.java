package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.dice.Dice;
import com.assignment.game.dice.DiceType;
import com.assignment.game.dice.impl.CrookedDice;
import com.assignment.game.dice.impl.NormalDice;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DisplayUtil;

import java.util.Scanner;

public class PlaySnakeLadderGame {
    public static void main(String... args) throws Exception {
        Scanner inputObj = new Scanner(System.in);

        DisplayUtil.displayMessage("Enter number of rows: ");
        int row = Integer.parseInt(inputObj.nextLine());
        DisplayUtil.displayMessage("Enter number of columns: ");
        int column = Integer.parseInt(inputObj.nextLine());

        // Create the board for the game.
        GameBoard board = new GameBoard(row, column);
        DisplayUtil.displayMessage("Game booard created with " + row + " rows and " + column + " columns");

        // Take input from users related to snake (start and end) and add them to board.
        DisplayUtil.displayMessage("Add Snakes in format start1-end1, start2-end2, ....");
        DisplayUtil.displayMessage("Constraint: start should be an integer which is greater than corresponding end");
        DisplayUtil.displayMessage("Constraint: start cannot be the last tile");
        final String snakes = inputObj.nextLine();
        for (String snakeConfig: snakes.split(",")) {
            String[] snakeStartEnd = snakeConfig.split("-");
            if (snakeStartEnd.length == 2) {
                board.addSnake(Integer.parseInt(snakeStartEnd[0]), Integer.parseInt(snakeStartEnd[1]));
            }
        }

        // Take input from users related to ladders (start and end) and add them to board.
        DisplayUtil.displayMessage("Add Ladders in format start1-end1, start2-end2, ....");
        DisplayUtil.displayMessage("Constraint: start should be an integer which is lesser than corresponding end");
        final String ladders = inputObj.nextLine();
        for (String ladderConfig: ladders.split(",")) {
            String[] ladderStartEnd = ladderConfig.split("-");
            if (ladderStartEnd.length == 2) {
                board.addLadder(Integer.parseInt(ladderStartEnd[0]), Integer.parseInt(ladderStartEnd[1]));
            }
        }

        Dice dice;
        DisplayUtil.displayMessage("Enter " + DiceType.DICE_TYPE_NORMAL.toString() + " to play with normal dice or "
                + DiceType.DICE_TYPE_CROOKED.toString() + " to play with crooked dice");
        final String diceChoice = inputObj.nextLine();
        switch (DiceType.valueOf(diceChoice)) {
            case DICE_TYPE_CROOKED:
                dice = new CrookedDice();
                break;
            default:
                dice = new NormalDice();
        }

        DisplayUtil.displayMessage("Starting the game, you will be provided 10 moves. Let's see if you can win by reaching the end.");
        DisplayUtil.displayMessage("Currently you are at " + board.getCurrentPos() + " position");
        for (int i = 0; i < 10; ++i) {
            DisplayUtil.displayMessage("Press <enter> to roll the dice");
            final int diceRoll = dice.roll();

            board.play(diceRoll);
            DisplayUtil.displayMessage("Your roll was " + diceRoll + " and went at " + board.getCurrentPos() + " position");
        }

        String resultBanner = "You have " + (board.hasWon() ? "won" : "lost") + " the game.";
        DisplayUtil.displayMessage(resultBanner);
    }
}

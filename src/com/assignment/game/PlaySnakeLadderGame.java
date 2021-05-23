package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.board.Player;
import com.assignment.game.board.strategy.GameStrategy;
import com.assignment.game.board.strategy.impl.SinglePlayerGameStrategy;
import com.assignment.game.constants.CommonConstants;
import com.assignment.game.constants.Message;
import com.assignment.game.dice.Dice;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DiceFactory;
import com.assignment.game.utils.DisplayUtil;

import java.util.Scanner;

public class PlaySnakeLadderGame {
    public static void main(String... args) {
        Scanner inputObj = new Scanner(System.in);

        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_NUMBER_OF_ROWS);
        int row = getIntegerInput(inputObj);
        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_NUMBER_OF_COLUMN);
        int column = getIntegerInput(inputObj);

        // Choose the dice type
        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_DICE_CHOICE);
        Dice dice = null;
        while (dice == null) {
            String type = inputObj.nextLine();
            dice = getDiceInstance(type);
        }
        Player singlePlayer = new Player(CommonConstants.SNAKE_LADDER_GAME_DEFAULT_PLAYER_MARKER);
        GameStrategy singlePlayerStrategy = new SinglePlayerGameStrategy(singlePlayer, dice);

        // Create the board for the game.
        GameBoard board = new GameBoard(row, column, singlePlayerStrategy);
        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_BOARD_CREATED
                + row + Message.SNAKE_LADDER_GAME_BOARD_ROWS
                + column + Message.SNAKE_LADDER_GAME_BOARD_COLUMN);

        addBoardElements(inputObj, board, true,
                Message.SNAKE_LADDER_GAME_BOARD_SNAKE_FORMAT,
                Message.SNAKE_LADDER_GAME_BOARD_SNAKE_INTEGER_FORMAT,
                Message.SNAKE_LADDER_GAME_BOARD_SNAKE_LAST_TILE);

        addBoardElements(inputObj, board, false,
                Message.SNAKE_LADDER_GAME_BOARD_LADDER_FORMAT,
                Message.SNAKE_LADDER_GAME_BOARD_LADDER_INTEGER_FORMAT);

        // Start playing the game
        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_START_MESSAGE);
        DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_PLAY_CURRENT_STATUS
                + (board.getCurrentPos()) + Message.SNAKE_LADDER_GAME_PLAY_POSITION);
        int moves;
        for (moves = 0; moves < 10; ++moves) {
            DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_PLAY_DICE_ROLL_INFO);
            inputObj.nextLine();

            board.play();
            DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_PLAY_YOUR_ROLL_WAS_INFO + board.lastRecordedMove()
                    + Message.SNAKE_LADDER_GAME_PLAY_WENT_AT_INFO + board.getCurrentPos() + Message.SNAKE_LADDER_GAME_PLAY_POSITION);
            board.displayBoard();

            // If won break from loop
            if (board.hasWon())
                break;
        }

        String resultBanner = Message.SNAKE_LADDER_GAME_END_YOU_HAVE
                + (board.hasWon() ? Message.SNAKE_LADDER_GAME_END_WON : Message.SNAKE_LADDER_GAME_END_LOST)
                + Message.SNAKE_LADDER_GAME_END_THE_GAME + (moves + 1) + Message.SNAKE_LADDER_GAME_END_MOVE;
        DisplayUtil.displayMessage(resultBanner);
    }

    private static Dice getDiceInstance(final String type) {
        try {
            return DiceFactory.getDiceOfType(type);
        } catch (IllegalArgumentException e) {
            DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_DICE_EXCEPTION_MSG);
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
        for (String elementConfig: elements.split(CommonConstants.SNAKE_LADDER_GAME_COMMA_SPLITTER)) {
            elementConfig = elementConfig.trim();
            String[] elementStartEnd = elementConfig.split(CommonConstants.SNAKE_LADDER_GAME_HYPHEN_SPLITTER);
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
                DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_ELEMENT_EXCEPTION_MSG);
                addBoardElements(inputObj, board, isSnake, banners);
            }
        }
    }

    private static int getIntegerInput(final Scanner inputObj) {
        String input = inputObj.nextLine();
        try {
            return getPositiveIntegerFromString(input);
        } catch (NumberFormatException ex) {
            DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_POSITIVE_NUMBER_FORMAT_EXCEPTION_MSG);
            return getIntegerInput(inputObj);
        }
    }

    private static int getPositiveIntegerFromString(final String input) throws NumberFormatException {
        int x = Integer.parseInt(input.trim());
        if (x < 0)
            throw new NumberFormatException(Message.SNAKE_LADDER_GAME_NEGATIVE_NUMBER_ENTERED_EXCEPTION_MSG);
        return x;
    }
}

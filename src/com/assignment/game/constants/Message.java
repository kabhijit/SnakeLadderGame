package com.assignment.game.constants;

import com.assignment.game.dice.DiceType;

public class Message {
    private Message() {}

    public static final String SNAKE_LADDER_GAME_NUMBER_OF_ROWS = "Enter number of rows: ";
    public static final String SNAKE_LADDER_GAME_NUMBER_OF_COLUMN = "Enter number of columns: ";
    public static final String SNAKE_LADDER_GAME_BOARD_CREATED = "Game board created with ";
    public static final String SNAKE_LADDER_GAME_BOARD_ROWS = " rows and ";
    public static final String SNAKE_LADDER_GAME_BOARD_COLUMN = " columns";
    public static final String SNAKE_LADDER_GAME_BOARD_SNAKE_FORMAT = "Add Snakes in format start1-end1, start2-end2, ....";
    public static final String SNAKE_LADDER_GAME_BOARD_SNAKE_INTEGER_FORMAT = "Constraint: start should be an integer which is greater than corresponding end";
    public static final String SNAKE_LADDER_GAME_BOARD_SNAKE_LAST_TILE = "Constraint: start cannot be the last tile for any snake";
    public static final String SNAKE_LADDER_GAME_BOARD_LADDER_FORMAT = "Add Ladders in format start1-end1, start2-end2, ....";
    public static final String SNAKE_LADDER_GAME_BOARD_LADDER_INTEGER_FORMAT = "Constraint: start should be an integer which is lesser than corresponding end";

    public static final String SNAKE_LADDER_GAME_DICE_CHOICE = "Enter " + DiceType.NORMAL.toString() + " to play with normal dice or "
            + DiceType.CROOKED.toString() + " to play with crooked dice";

    public static final String SNAKE_LADDER_GAME_START_MESSAGE = "Starting the game, you will be provided 10 moves. Let's see if you can win by reaching the end.";

    public static final String SNAKE_LADDER_GAME_PLAY_CURRENT_STATUS = "Currently you are at ";
    public static final String SNAKE_LADDER_GAME_PLAY_POSITION = " position";
    public static final String SNAKE_LADDER_GAME_PLAY_DICE_ROLL_INFO = "Press <enter> to roll the dice";
    public static final String SNAKE_LADDER_GAME_PLAY_YOUR_ROLL_WAS_INFO = "Your roll was ";
    public static final String SNAKE_LADDER_GAME_PLAY_WENT_AT_INFO = " and went at ";
    public static final String SNAKE_LADDER_GAME_PLAY_ROLL_VALIDATION_MOVE_EXCEEDED_PREFIX = "Dice roll value was ";
    public static final String SNAKE_LADDER_GAME_PLAY_ROLL_VALIDATION_MOVE_EXCEEDED_SUFFIX = " but cannot move forward since enough tiles are not present.";

    public static final String SNAKE_LADDER_GAME_END_YOU_HAVE = "You have ";
    public static final String SNAKE_LADDER_GAME_END_WON = "won";
    public static final String SNAKE_LADDER_GAME_END_LOST = "lost";
    public static final String SNAKE_LADDER_GAME_END_THE_GAME = " the game in ";
    public static final String SNAKE_LADDER_GAME_END_MOVE = "th move.";

    public static final String SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_START = "You have automatically moved from ";
    public static final String SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_TO = " to ";
    public static final String SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_BETWEEN = " because there was a ";
    public static final String SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_SNAKE_AT = "snake at ";
    public static final String SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_LADDER_AT = "ladder at ";

    public static final String SNAKE_LADDER_GAME_DICE_EXCEPTION_MSG = "Invalid option entered, please enter a valid choice [" + DiceType.NORMAL.toString() + " or " + DiceType.CROOKED.toString() + "]";
    public static final String SNAKE_LADDER_GAME_ELEMENT_EXCEPTION_MSG = "Invalid configuration entered. Please try again";
    public static final String SNAKE_LADDER_GAME_POSITIVE_NUMBER_FORMAT_EXCEPTION_MSG = "This is not a valid positive number, please enter a valid number";
    public static final String SNAKE_LADDER_GAME_NEGATIVE_NUMBER_ENTERED_EXCEPTION_MSG = "Negative number is meaningless";
    public static final String SNAKE_LADDER_GAME_ELEMENT_EXITS_EXCEPTION = "A snake or a ladder already exists at ";
    public static final String SNAKE_LADDER_GAME_INVALID_SNAKE_EXCEPTION_PREFIX = "Snake start ";
    public static final String SNAKE_LADDER_GAME_INVALID_SNAKE_EXCEPTION_SUFFIX = " is lower than end ";
    public static final String SNAKE_LADDER_GAME_INVALID_LADDER_EXCEPTION_PREFIX = "Ladder start ";
    public static final String SNAKE_LADDER_GAME_INVALID_LADDER_EXCEPTION_SUFFIX = " is higher than end ";

}

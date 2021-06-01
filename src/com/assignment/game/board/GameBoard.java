package com.assignment.game.board;

import com.assignment.game.board.elements.impl.GreenSnake;
import com.assignment.game.board.elements.impl.Ladder;
import com.assignment.game.board.elements.impl.Snake;
import com.assignment.game.board.strategy.GameStrategy;
import com.assignment.game.board.strategy.impl.SinglePlayerGameStrategy;
import com.assignment.game.constants.CommonConstants;
import com.assignment.game.constants.Message;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DisplayUtil;
import com.assignment.game.utils.ValidationUtil;

import java.util.*;

public class GameBoard {
    private final int row;
    private final int column;
    private final int limit;
    private final Map<Integer, Snake> snakeMap;
    private final Map<Integer, Ladder> ladderMap;
    private final Set<Integer> takenTiles;
    private final GameStrategy gameStrategy;

    // This creates a default board of 10x10
    public GameBoard() {
        this.row = 10;
        this.column = 10;
        this.limit = row * column;
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        this.takenTiles = new HashSet<>();
        this.gameStrategy = new SinglePlayerGameStrategy();
    }

    public GameBoard(int row, int column, GameStrategy gameStrategy) {
        this.row = row;
        this.column = column;
        this.limit = row * column;
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        this.takenTiles = new HashSet<>();
        this.gameStrategy = gameStrategy;
    }

    public int getCurrentPos() {
        return this.gameStrategy.getCurrentPlayer().getCurrentPosition();
    }

    public GameBoard(int size, GameStrategy gameStrategy) {
        this(size, size, gameStrategy);
    }

    public void addSnake(final Snake snake) throws InvalidSnakeConfigException, ElementExistsException {
        validateAndAddSnake(snake.getStart(), snake.getEnd(), snake);
    }

    private void validateAndAddSnake(final int start, final int end, final Snake snake) throws InvalidSnakeConfigException, ElementExistsException {
        if (!ValidationUtil.isValidSnake(start, end, this.limit)) {
            throw new InvalidSnakeConfigException(Message.SNAKE_LADDER_GAME_INVALID_SNAKE_EXCEPTION_PREFIX + start
                    + Message.SNAKE_LADDER_GAME_INVALID_SNAKE_EXCEPTION_SUFFIX + end);
        }
        if (checkElementAvailability(start, end)) {

            this.snakeMap.put(start, snake);
            addTakenTileEntry(start, end);
        }
    }

    public void addLadder(final int start, final int end) throws InvalidLadderConfigException, ElementExistsException {
        if (!ValidationUtil.isValidLadder(start, end, this.limit)) {
            throw new InvalidLadderConfigException(Message.SNAKE_LADDER_GAME_INVALID_LADDER_EXCEPTION_PREFIX + start
                    + Message.SNAKE_LADDER_GAME_INVALID_LADDER_EXCEPTION_SUFFIX + end);
        }
        if (checkElementAvailability(start, end)) {
            Ladder ladder = new Ladder(start, end);
            this.ladderMap.put(start, ladder);
            addTakenTileEntry(start, end);
        }
    }

    public int play() {
        return updateMarkerForPlayer(this.gameStrategy, gameStrategy.play());
    }

    public int play(int move) {
        return updateMarkerForPlayer(this.gameStrategy, move);
    }

    public int lastRecordedMove() {
        return this.gameStrategy.getCurrentPlayer().getLastMove();
    }

    public boolean hasWon() {
        return this.gameStrategy.getCurrentPlayer().getCurrentPosition() == this.limit;
    }

    public void displayBoard() {
        StringBuilder horizontalLine = new StringBuilder();
        for (int i = 0; i < this.column; ++i)
            horizontalLine.append(CommonConstants.SNAKE_LADDER_GAME_FORMATTER_HORIZONTAL_LINE);

        DisplayUtil.displayMessage(horizontalLine.toString());
        int counter = this.limit;
        for(int i = this.row; i > 0 ; --i) {
            StringBuilder sb = new StringBuilder(CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE);
            StringBuilder sbNo = new StringBuilder(CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE);
            for(int j = this.column; j > 0 ; --j) {
                if (i % 2 != 0) {
                    sbNo.insert(0, CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE + counter + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE);
                    sb.insert(0, counter == this.gameStrategy.getCurrentPlayer().getCurrentPosition()
                            ? CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE + this.gameStrategy.getCurrentPlayer().getMarker() + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE
                            : CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE);
                } else {
                    sbNo.append(counter).append(CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE);
                    sb.append(counter == this.gameStrategy.getCurrentPlayer().getCurrentPosition()
                            ? this.gameStrategy.getCurrentPlayer().getMarker() + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE
                            : CommonConstants.SNAKE_LADDER_GAME_FORMATTER_SPACE + CommonConstants.SNAKE_LADDER_GAME_FORMATTER_VERTICAL_LINE);
                }
                --counter;
            }
            DisplayUtil.displayMessage(sbNo.toString());
            DisplayUtil.displayMessage(sb.toString());
            DisplayUtil.displayMessage(horizontalLine.toString());
        }
    }

    private void addTakenTileEntry(int start, int end) {
        this.takenTiles.add(start);
        this.takenTiles.add(end);
    }

    public void forceToPosition() {

    }

    private int updateMarkerForPlayer(final GameStrategy strategy, final int move) {
        if (this.limit >= move + strategy.getCurrentPlayer().getCurrentPosition()) {
            strategy.getCurrentPlayer().setCurrentPosition(strategy.getCurrentPlayer().getCurrentPosition() + move);
        } else {
            DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_PLAY_ROLL_VALIDATION_MOVE_EXCEEDED_PREFIX
                    + move + Message.SNAKE_LADDER_GAME_PLAY_ROLL_VALIDATION_MOVE_EXCEEDED_SUFFIX);
            return strategy.getCurrentPlayer().getCurrentPosition();
        }
        if (this.snakeMap.containsKey(strategy.getCurrentPlayer().getCurrentPosition())
                && this.ladderMap.containsKey(strategy.getCurrentPlayer().getCurrentPosition())) {
            Snake snake = this.snakeMap.get(strategy.getCurrentPlayer().getCurrentPosition());
            if (snake instanceof GreenSnake && ((GreenSnake)snake).shouldProcessed()) {
                ((GreenSnake) snake).visit();
            } else {
                DisplayUtil.displayMessage("Green snake was already processed");
                return strategy.getCurrentPlayer().getCurrentPosition();
            }

            int oldPosition = strategy.getCurrentPlayer().getCurrentPosition();
            int newSnakePosition = getSnakeEndIndexIfPresent(oldPosition).orElse(oldPosition);
            int newLadderPosition = getLadderEndIndexIfPresent(oldPosition).orElse(oldPosition);
            if (newSnakePosition != oldPosition) {
                strategy.getCurrentPlayer().setCurrentPosition(newSnakePosition);
                DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_START
                        + oldPosition + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_TO
                        + strategy.getCurrentPlayer().getCurrentPosition()
                        + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_BETWEEN
                        + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_SNAKE_AT
                        + oldPosition);
            } else if (oldPosition != newLadderPosition) {
                strategy.getCurrentPlayer().setCurrentPosition(newLadderPosition);
                DisplayUtil.displayMessage(Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_START
                        + oldPosition + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_TO
                        + strategy.getCurrentPlayer().getCurrentPosition()
                        + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_MSG_BETWEEN
                        + Message.SNAKE_LADDER_GAME_ELEMENT_ENCOUNTER_LADDER_AT
                        + oldPosition);
            }
        }
        return strategy.getCurrentPlayer().getCurrentPosition();
    }

    private void moveOnBoard() {

    }

    private Optional<Integer> getSnakeEndIndexIfPresent(int start) {
        if (this.snakeMap.containsKey(start)) {
            return Optional.of(this.snakeMap.get(start).getEnd());
        }
        return Optional.empty();
    }

    private Optional<Integer> getLadderEndIndexIfPresent(int start) {
        if (this.ladderMap.containsKey(start)) {
            return Optional.of(this.ladderMap.get(start).getEnd());
        }
        return Optional.empty();
    }

    private boolean checkElementAvailability(int start, int end) throws ElementExistsException {
        if (this.takenTiles.contains(start) || this.takenTiles.contains(end)) {
            throw new ElementExistsException(Message.SNAKE_LADDER_GAME_ELEMENT_EXITS_EXCEPTION + start);
        }
        return true;
    }
}

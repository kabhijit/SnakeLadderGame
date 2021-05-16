package com.assignment.game.board;

import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.ValidationUtil;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private final int row;
    private final int column;
    private final Map<Point2D, Point2D> snakeMap;
    private final Map<Point2D, Point2D> ladderMap;

    // This wont be called
    private GameBoard() {
        this.row = 0;
        this.column = 0;
        this.snakeMap = null;
        this.ladderMap = null;
    }

    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
    }

    public GameBoard(int size) {
        this(size, size);
    }

    public void addSnake(Point2D start, Point2D end) throws InvalidSnakeConfigException {
        if (!ValidationUtil.isValidSnake(start, end, this.row)) {
            throw new InvalidSnakeConfigException("Snake start " + start + " is lower than end " + end);
        }
        this.snakeMap.putIfAbsent(start, end);
    }

    public void addLadder(Point2D start, Point2D end) throws InvalidLadderConfigException {
        if (!ValidationUtil.isValidLadder(start, end, this.row)) {
            throw new InvalidLadderConfigException("Ladder start " + start + " is higher than end " + end);
        }
        this.ladderMap.putIfAbsent(start, end);
    }
}

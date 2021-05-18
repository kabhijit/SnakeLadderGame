package com.assignment.game.board;

import com.assignment.game.board.elements.Element;
import com.assignment.game.board.elements.impl.Ladder;
import com.assignment.game.board.elements.impl.Snake;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DisplayUtil;
import com.assignment.game.utils.ValidationUtil;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private final int row;
    private final int column;
    private final int limit;
    private int currentPos;
    private final Map<Integer, Element> elementsMap;

    // This wont be called
    private GameBoard() {
        this.row = 0;
        this.column = 0;
        this.limit = 0;
        this.currentPos = 0;
        this.elementsMap = null;
    }

    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        this.limit = row * column;
        this.currentPos = 0;
        this.elementsMap = new HashMap<>();
    }

    public int getCurrentPos() {
        return this.currentPos;
    }

    public GameBoard(int size) {
        this(size, size);
    }

    public void addSnake(final int start, final int end) throws InvalidSnakeConfigException, ElementExistsException {
        if (!ValidationUtil.isValidSnake(start, end, this.row * this.column)) {
            throw new InvalidSnakeConfigException("Snake start " + start + " is lower than end " + end);
        }
        if (this.elementsMap.containsKey(start)) {
            throw new ElementExistsException("A snake or a ladder already exists at " + start);
        }
        this.elementsMap.putIfAbsent(start, new Snake(start, end));
    }

    public void addLadder(final int start, final int end) throws InvalidLadderConfigException, ElementExistsException {
        if (!ValidationUtil.isValidLadder(start, end, this.limit)) {
            throw new InvalidLadderConfigException("Ladder start " + start + " is higher than end " + end);
        }
        if (this.elementsMap.containsKey(start)) {
            throw new ElementExistsException("A snake or a ladder already exists at " + start);
        }
        this.elementsMap.putIfAbsent(start, new Ladder(start, end));
    }

    public int play(final int move) {
        if (this.limit - this.currentPos >= move) {
            this.currentPos += move;
        }
        if (this.elementsMap.containsKey(this.currentPos)) {
            int oldPosition = this.currentPos;
            this.currentPos = this.elementsMap.get(this.currentPos).getEnd();
            DisplayUtil.displayMessage("You have automatically moved from " + oldPosition + " to " + this.currentPos);
        }
        return currentPos;
    }

    public boolean hasWon() {
        return this.currentPos == this.limit;
    }
}

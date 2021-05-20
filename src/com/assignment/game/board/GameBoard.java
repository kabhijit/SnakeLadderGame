package com.assignment.game.board;

import com.assignment.game.board.elements.Element;
import com.assignment.game.board.elements.impl.Ladder;
import com.assignment.game.board.elements.impl.Snake;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DisplayUtil;
import com.assignment.game.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {
    private final int row;
    private final int column;
    private final int limit;
    private final Map<Integer, Element> elementsMap;
    private final List<Player> players;

    // This creates a default board of 10x10
    public GameBoard() {
        this.row = 10;
        this.column = 10;
        this.limit = row * column;
        this.elementsMap = new HashMap<>();
        this.players = new ArrayList<>();
    }

    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        this.limit = row * column;
        this.elementsMap = new HashMap<>();
        this.players = new ArrayList<>();
    }

    public void registerPlayer(Player p) {
        this.players.add(p);
    }

    public int getCurrentPos() {
        return this.players.get(0).getCurrentPosition();
    }

    public GameBoard(int size) {
        this(size, size);
    }

    public void addSnake(final int start, final int end) throws InvalidSnakeConfigException, ElementExistsException {
        if (!ValidationUtil.isValidSnake(start, end, this.row * this.column)) {
            throw new InvalidSnakeConfigException("Snake start " + start + " is lower than end " + end);
        }
        if (checkElementAvailability(start, end)) {
            Snake snake = new Snake(start, end);
            this.elementsMap.put(start, snake);
            this.elementsMap.put(end, snake);
        }
    }

    public void addLadder(final int start, final int end) throws InvalidLadderConfigException, ElementExistsException {
        if (!ValidationUtil.isValidLadder(start, end, this.limit)) {
            throw new InvalidLadderConfigException("Ladder start " + start + " is higher than end " + end);
        }
        if (checkElementAvailability(start, end)) {
            Ladder ladder = new Ladder(start, end);
            this.elementsMap.put(start, ladder);
            this.elementsMap.put(end, ladder);
        }
    }

    public int play() {
        return updateMarkerForPlayer(this.players.get(0));
    }

    public int lastRecordedMove() {
        return this.players.get(0).getLastMove();
    }

    public boolean hasWon() {
        return this.players.get(0).getCurrentPosition() == this.limit;
    }

    public void displayBoard() {
        StringBuilder horizontalLine = new StringBuilder();
        for (int i = 0; i < this.column; ++i)
            horizontalLine.append("----");

        DisplayUtil.displayMessage(horizontalLine.toString());
        int counter = this.limit;
        for(int i = this.row; i > 0 ; --i) {
            StringBuilder sb = new StringBuilder("|");
            StringBuilder sbNo = new StringBuilder("|");
            for(int j = this.column; j > 0 ; --j) {
                if (i % 2 != 0) {
                    sbNo.insert(0, "|" + counter + "\t");
                    sb.insert(0, counter == this.players.get(0).getCurrentPosition()
                            ? "|" + this.players.get(0).getMarker() + "\t"
                            : "|\t");
                } else {
                    sbNo.append(counter).append("\t|");
                    sb.append(counter == this.players.get(0).getCurrentPosition()
                            ? this.players.get(0).getMarker() + "\t|"
                            : "\t|");
                }
                --counter;
            }
            DisplayUtil.displayMessage(sbNo.toString());
            DisplayUtil.displayMessage(sb.toString());
            DisplayUtil.displayMessage(horizontalLine.toString());
        }
    }

    private int updateMarkerForPlayer(final Player player) {
        final int move = player.play();
        if (this.limit >= move + player.getCurrentPosition()) {
            player.setCurrentPosition(player.getCurrentPosition() + move);
        }
        if (this.elementsMap.containsKey(player.getCurrentPosition())) {
            int oldPosition = player.getCurrentPosition();
            player.setCurrentPosition(this.elementsMap.get(oldPosition).getEnd());
            DisplayUtil.displayMessage("You have automatically moved from "
                    + oldPosition + " to "
                    + player.getCurrentPosition()
                    + "beacuse there was a "
                    + (oldPosition > player.getCurrentPosition() ? "snake at" : "ladder at ")
                    + oldPosition);
        }
        return player.getCurrentPosition();
    }

    private boolean checkElementAvailability(int start, int end) throws ElementExistsException {
        if (this.elementsMap.containsKey(start) || this.elementsMap.containsKey(end)) {
            throw new ElementExistsException("A snake or a ladder already exists at " + start);
        }
        return true;
    }
}

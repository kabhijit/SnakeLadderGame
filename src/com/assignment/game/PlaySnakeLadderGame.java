package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;

import java.awt.geom.Point2D;

public class PlaySnakeLadderGame {
    public static void main(String... args) throws InvalidSnakeConfigException, InvalidLadderConfigException {

        GameBoard board = new GameBoard(10);

        board.addSnake(new Point2D.Double(1.0,2.0),
                new Point2D.Double(0.0,4.0));

        board.addLadder(new Point2D.Double(3.0,8.0),
                new Point2D.Double(5.0,2.0));
    }
}

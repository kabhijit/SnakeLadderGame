package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;

public class PlaySnakeLadderGame {
    public static void main(String... args) throws InvalidSnakeConfigException, InvalidLadderConfigException {

        GameBoard board = new GameBoard(10);

        try {
            board.addSnake(7, 14);
            board.addLadder(20, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

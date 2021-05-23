package com.assignment.game;

import com.assignment.game.board.GameBoard;
import org.junit.Assert;

public abstract class GamePlayTest {
    protected GameBoard gameBoard;

    protected void playGameTill10Moves() {
        int count = 0, roll;
        while (this.gameBoard.getCurrentPos() < 25 && count < 10) {
            roll = this.gameBoard.play();
            System.out.println("Played dice roll " + roll + " current position " + this.gameBoard.getCurrentPos());
            ++count;
        }
    }
}

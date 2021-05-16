package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.dice.Dice;
import com.assignment.game.dice.impl.NormalDice;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameBoard gameBoard;
    Dice dice;

    @Before
    public void setup() throws ElementExistsException,
            InvalidSnakeConfigException,
            InvalidLadderConfigException {

        /**
         * --------------------------
         * | 21 | 22 | 23 | 24 | 25 |
         * --------------------------
         * | 20 | 19 | 18 | 17 | 16 |
         * --------------------------
         * | 11 | 12 | 13 | 14 | 15 |
         * --------------------------
         * | 10 |  9 |  8 |  7 |  6 |
         * --------------------------
         * |  1 |  2 |  3 |  4 |  5 |
         * --------------------------
         */
        this.dice = new NormalDice();
        this.gameBoard = new GameBoard(5);
        // Adding snakes on board
        this.gameBoard.addSnake(12, 3);
        this.gameBoard.addSnake(17, 9);
        this.gameBoard.addSnake(22, 15);
        // Adding ladders on board
        this.gameBoard.addLadder(5, 16);
        this.gameBoard.addLadder(10, 23);
        this.gameBoard.addLadder(14, 24);
    }

    @Test
    public void test_snakeLadderGamePlay1() {
        while (this.gameBoard.getCurrentPos() < 25) {
            int diceRoll = dice.roll();
            this.gameBoard.play(diceRoll);
            System.out.println("Played dice roll " + dice.roll() + " current position " + this.gameBoard.getCurrentPos());
        }
        Assert.assertEquals(25, this.gameBoard.getCurrentPos());
    }
}

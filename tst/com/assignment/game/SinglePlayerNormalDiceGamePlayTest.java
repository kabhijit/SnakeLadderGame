package com.assignment.game;

import com.assignment.game.board.GameBoard;
import com.assignment.game.board.Player;
import com.assignment.game.board.elements.impl.GreenSnake;
import com.assignment.game.board.elements.impl.Snake;
import com.assignment.game.board.strategy.GameStrategy;
import com.assignment.game.board.strategy.impl.SinglePlayerGameStrategy;
import com.assignment.game.dice.DiceType;
import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;
import com.assignment.game.utils.DiceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SinglePlayerNormalDiceGamePlayTest extends GamePlayTest {
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

        Player player = new Player('X');
        GameStrategy singlePlayerGameStrategy = new SinglePlayerGameStrategy(player,
                                                    DiceFactory.getDiceOfType(DiceType.NORMAL.toString()));
        this.gameBoard = new GameBoard(5, singlePlayerGameStrategy);
        // Adding snakes on board
        this.gameBoard.addSnake(new Snake(12, 3));
        this.gameBoard.addSnake(new Snake(17, 9));
        this.gameBoard.addSnake(new Snake(22, 15));
        // Adding ladders on board
        this.gameBoard.addLadder(5, 16);
        this.gameBoard.addLadder(10, 23);
        this.gameBoard.addLadder(14, 24);
    }

    @Test
    public void test_snakeLadderGamePlayForSinglePlayerWithNormalDice() {
        playGameTill10Moves();
    }

    @Test
    public void test_snakeLadderGamePlayForSinglePlayerWithCrookedDice() {
        playGameTill10Moves();
        Assert.assertEquals("You have lost the game", 25, this.gameBoard.getCurrentPos());
    }

    @Test
    public void test_snakeLadderGameWithgreenSnakePlayForSinglePlayerWithNormalDice() {
        try {
            addGreenSnake(this.gameBoard);
        } catch (Exception e) {}

        this.gameBoard.play(19);
        this.gameBoard.play(6);

        Assert.assertEquals("You have lost the game", 25, this.gameBoard.getCurrentPos());
    }

    private void addGreenSnake(final GameBoard board) throws ElementExistsException, InvalidSnakeConfigException {
        // assuminng it is a 10x10 board
        GreenSnake greenSnake1 = new GreenSnake(20, 13, false);
        board.addSnake(greenSnake1);
    }
}

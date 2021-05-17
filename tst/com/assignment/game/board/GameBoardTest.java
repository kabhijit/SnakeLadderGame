package com.assignment.game.board;

import com.assignment.game.exception.ElementExistsException;
import com.assignment.game.exception.InvalidLadderConfigException;
import com.assignment.game.exception.InvalidSnakeConfigException;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class GameBoardTest extends TestCase {
    GameBoard testGameBoard;

    @Before
    public void setup() {
        this.testGameBoard = new GameBoard(10);
    }

    @Test
    public void test_addValidSnake_shouldAddSnake() {
        boolean success;
        try {
            this.testGameBoard.addSnake(8, 2);
            success = true;
        } catch (InvalidSnakeConfigException | ElementExistsException e) {
            e.printStackTrace();
            success = false;
        }

        Assert.assertTrue(success);
    }

    @Test
    public void test_addInvalidSnake_shouldThrowException() {
        boolean exceptionThrown;
        try {
            this.testGameBoard.addSnake(8, 23);
            exceptionThrown = false;
        } catch (InvalidSnakeConfigException | ElementExistsException e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void test_addInvalidSnakeWithStartExceedingLimit_shouldThrowException() {
        boolean exceptionThrown;
        try {
            this.testGameBoard.addSnake(100, 10);
            exceptionThrown = false;
        } catch (InvalidSnakeConfigException | ElementExistsException e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void test_addValidSnakeTwice_shouldThrowException() {
        boolean exceptionThrown;
        try {
            this.testGameBoard.addSnake(8, 2);
            this.testGameBoard.addSnake(8, 5);
            exceptionThrown = false;
        } catch (InvalidSnakeConfigException | ElementExistsException e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void test_addValidLadder_shouldAddLadder() {
        boolean success;
        try {
            this.testGameBoard.addLadder(4, 14);
            success = true;
        } catch (InvalidLadderConfigException | ElementExistsException e) {
            e.printStackTrace();
            success = false;
        }

        Assert.assertTrue(success);
    }

    @Test
    public void test_addInvalidLadder_shouldThrowException() {
        boolean exceptionThrown;
        try {
            this.testGameBoard.addLadder(18, 2);
            exceptionThrown = false;
        } catch (InvalidLadderConfigException | ElementExistsException e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void test_addValidLadderTwice_shouldThrowException() {
        boolean exceptionThrown;
        try {
            this.testGameBoard.addLadder(8, 22);
            this.testGameBoard.addLadder(8, 15);
            exceptionThrown = false;
        } catch (InvalidLadderConfigException | ElementExistsException e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }
}
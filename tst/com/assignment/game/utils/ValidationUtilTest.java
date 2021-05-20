package com.assignment.game.utils;

import junit.framework.TestCase;
import org.junit.Assert;

public class ValidationUtilTest extends TestCase {

    public void test_isValidSnake_shouldReturnFalseWhenStartSmaller() {
        Assert.assertFalse(ValidationUtil.isValidSnake(2,3, 10));
    }

    public void test_isValidSnake_shouldReturnFalseWhenStartIsAtEnd() {
        Assert.assertFalse(ValidationUtil.isValidSnake(10,3, 10));
    }

    public void test_isValidSnake_shouldReturnFalseWhenStartIsOutOfBound() {
        Assert.assertFalse(ValidationUtil.isValidSnake(100,3, 10));
    }

    public void test_isValidSnake_shouldReturnTrueWhenStartBiggerWithinBounds() {
        Assert.assertTrue(ValidationUtil.isValidSnake(9,3, 10));
    }

    public void test_isValidLadder_shouldReturnFalseWhenStartBigger() {
        Assert.assertFalse(ValidationUtil.isValidLadder(5,3, 10));
    }

    public void test_isValidLadder_shouldReturnFalseWhenStartIsOutOfBound() {
        Assert.assertFalse(ValidationUtil.isValidLadder(5,300, 10));
    }

    public void test_isValidLadder_shouldReturnTrueWhenStartBiggerWithinBounds() {
        Assert.assertTrue(ValidationUtil.isValidLadder(2,9, 10));
    }

}